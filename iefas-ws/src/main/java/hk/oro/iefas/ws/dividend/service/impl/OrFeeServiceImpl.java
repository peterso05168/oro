package hk.oro.iefas.ws.dividend.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.CoreConstant.CalculationMethod;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.casemgt.entity.CaseType;
import hk.oro.iefas.domain.dividend.dto.CalculationInfoDTO;
import hk.oro.iefas.domain.dividend.dto.CalculationMaintenanceDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeMaintenanceDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeToBeChargedDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeTypeDTO;
import hk.oro.iefas.domain.dividend.entity.CaseFeeMain;
import hk.oro.iefas.domain.dividend.entity.CaseFeeType;
import hk.oro.iefas.domain.dividend.entity.FeeCharg;
import hk.oro.iefas.ws.casemgt.repository.CaseTypeRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseTypeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.AnalysisCodeRepository;
import hk.oro.iefas.ws.dividend.repository.CaseFeeMainRepository;
import hk.oro.iefas.ws.dividend.repository.CaseFeeTypeRepository;
import hk.oro.iefas.ws.dividend.repository.FeeChargRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.AnalysisCodeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.CaseFeeMaintenanceDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.CaseFeeTypeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.CaseFeeMainPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.CaseFeeTypePredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.FeeChargPredicate;
import hk.oro.iefas.ws.dividend.service.OrFeeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2830 $ $Date: 2018-06-02 12:07:55 +0800 (週六, 02 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class OrFeeServiceImpl implements OrFeeService {

    @Autowired
    private CaseFeeTypeRepository caseFeeTypeRepository;

    @Autowired
    private CaseFeeMainRepository caseFeeMainRepository;

    @Autowired
    private CaseTypeRepository caseTypeRepository;

    @Autowired
    private CaseFeeTypeDTOAssembler caseFeeTypeDTOAssembler;

    @Autowired
    private CaseFeeMaintenanceDTOAssembler caseFeeMaintenanceDTOAssembler;

    @Autowired
    private CaseTypeDTOAssembler caseTypeDTOAssembler;

    @Autowired
    private AnalysisCodeRepository analysisCodeRepository;

    @Autowired
    private FeeChargRepository feeChargRepository;

    @Autowired
    private AnalysisCodeDTOAssembler analysisCodeDTOAssembler;

    @Transactional(readOnly = true)
    @Override
    public CaseFeeTypeDTO searchORFeeItemWithCalculationMethod(Integer caseFeeTypeId) {
        log.info("searchORFeeItemWithCalculationMethod start - param: " + caseFeeTypeId);
        CaseFeeTypeDTO caseFeeTypeDTO = null;
        if (caseFeeTypeId != null && caseFeeTypeId.intValue() != 0) {
            CaseFeeType caseFeeType = caseFeeTypeRepository.findOne(CaseFeeTypePredicate.findOne(caseFeeTypeId));

            if (caseFeeType != null) {
                caseFeeTypeDTO = caseFeeTypeDTOAssembler.toDTO(caseFeeType);
                if (caseFeeType.getCaseType() != null) {
                    CaseType caseType = caseTypeRepository.findOne(caseFeeType.getCaseType().getCaseTypeId());
                    caseFeeTypeDTO.setCaseType(caseTypeDTOAssembler.toDTO(caseType));
                }

                Iterable<CaseFeeMain> caseFeeMains = caseFeeMainRepository
                    .findAll(CaseFeeMainPredicate.findByCaseFeeTypeId(caseFeeTypeId), CaseFeeMainPredicate.order());
                if (caseFeeMains != null) {
                    caseFeeTypeDTO.setCaseFeeMaintenances(caseFeeMaintenanceDTOAssembler.toDTOList(caseFeeMains));
                }
            }
        }
        log.info("searchORFeeItemWithCalculationMethod end - return: " + caseFeeTypeDTO);
        return caseFeeTypeDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveORFeeItemWithCalculationMethod(CaseFeeTypeDTO caseFeeTypeDTO) {
        if (caseFeeTypeDTO != null) {
            String status = caseFeeTypeDTO.getStatus();
            Integer caseFeeTypeId = caseFeeTypeDTO.getCaseFeeTypeId();
            CaseFeeType caseFeeType = caseFeeTypeRepository.findOne(CaseFeeTypePredicate.findOne(caseFeeTypeId));
            if (caseFeeType != null) {
                caseFeeType.setStatus(caseFeeTypeDTO.getStatus());
                caseFeeType.setVersionNo(caseFeeTypeDTO.getVersionNo());
                caseFeeTypeRepository.save(caseFeeType);

                Iterable<CaseFeeMain> iterable
                    = caseFeeMainRepository.findAll(CaseFeeMainPredicate.findByCaseFeeTypeId(caseFeeTypeId));
                CaseFeeMain caseFeeMain = null;
                if (CoreConstant.STATUS_ACTIVE.equals(status)) {
                    List<CaseFeeMaintenanceDTO> caseFeeMaintenances = caseFeeTypeDTO.getCaseFeeMaintenances();
                    if (CommonUtils.isNotEmpty(caseFeeMaintenances)) {
                        Map<Integer, CaseFeeMain> caseFeeMainMap = new HashMap<>();
                        if (!IterableUtils.isEmpty(iterable)) {
                            for (Iterator<CaseFeeMain> iterator = iterable.iterator(); iterator.hasNext();) {
                                caseFeeMain = iterator.next();
                                caseFeeMainMap.put(caseFeeMain.getCaseFeeMainId(), caseFeeMain);
                            }
                        }

                        for (CaseFeeMaintenanceDTO caseFeeMaintenanceDTO : caseFeeMaintenances) {
                            Integer caseFeeMaintenanceId = caseFeeMaintenanceDTO.getCaseFeeMaintenanceId();
                            if (caseFeeMaintenanceId != null && caseFeeMaintenanceId != 0) {
                                caseFeeMainMap.remove(caseFeeMaintenanceId);
                            }

                            caseFeeMain = DataUtils.copyProperties(caseFeeMaintenanceDTO, CaseFeeMain.class);
                            caseFeeMain.setCaseFeeMainId(caseFeeMaintenanceDTO.getCaseFeeMaintenanceId());
                            caseFeeMain.setCalType(caseFeeMaintenanceDTO.getCalculationType());
                            caseFeeMain.setCaseFeeType(caseFeeType);
                            caseFeeMain.setStatus(CoreConstant.STATUS_ACTIVE);
                            caseFeeMain.setVersionNo(caseFeeMaintenanceDTO.getVersionNo());

                            caseFeeMainRepository.save(caseFeeMain);
                        }

                        Collection<CaseFeeMain> values = caseFeeMainMap.values();
                        if (CommonUtils.isNotEmpty(values)) {
                            for (CaseFeeMain inactiveCaseFeeMain : values) {
                                inactiveCaseFeeMain.setStatus(CoreConstant.STATUS_INACTIVE);
                                caseFeeMainRepository.save(inactiveCaseFeeMain);
                            }
                        }
                    }
                } else if (CoreConstant.STATUS_INACTIVE.equals(status)) {
                    for (Iterator<CaseFeeMain> iterator = iterable.iterator(); iterator.hasNext();) {
                        caseFeeMain = iterator.next();
                        caseFeeMain.setStatus(CoreConstant.STATUS_INACTIVE);
                        caseFeeMainRepository.save(caseFeeMain);
                    }
                }

                return caseFeeTypeId;
            }

        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean validateSaveORFeeItemWithCalculationMethod(List<CalculationMaintenanceDTO> calculationMaintenances) {
        if (CommonUtils.isEmpty(calculationMaintenances)) {
            return true;
        } else {
            calculationMaintenances.sort((a, b) -> {
                if (a == null && b == null) {
                    return 0;
                }
                if (a == null) {
                    return 1;
                }
                if (b == null) {
                    return -1;
                }

                return a.getCalculationInfo().getPeriodFrom().compareTo(b.getCalculationInfo().getPeriodFrom());
            });

            int size = calculationMaintenances.size();
            CalculationMaintenanceDTO current = null;
            CaseFeeMaintenanceDTO currentCaseFeeMaintenance = null;

            Date periodTo = null;
            Date periodFrom = null;
            Date nextDate = null;
            int k = 0;
            for (int i = 0; i < size; i++) {
                current = calculationMaintenances.get(i);

                CalculationInfoDTO calculationInfo = current.getCalculationInfo();
                periodTo = calculationInfo.getPeriodTo();
                if (periodTo == null) {
                    return false;
                }
                nextDate = DateUtils.addDays(periodTo, 1);

                k = i + 1;
                if (k < size) {
                    periodFrom = calculationMaintenances.get(k).getCalculationInfo().getPeriodFrom();
                    if (periodFrom == null) {
                        return false;
                    }
                    boolean isSameDay = DateUtils.isSameDay(nextDate, periodFrom);
                    if (!isSameDay) {
                        return false;
                    }
                }

                if (CalculationMethod.SLI.getDesc().equals(calculationInfo.getCalculationType())) {
                    List<CaseFeeMaintenanceDTO> caseFeeMaintenances = current.getCaseFeeMaintenances();
                    if (CommonUtils.isNotEmpty(caseFeeMaintenances)) {
                        caseFeeMaintenances.sort((a, b) -> {
                            if (a == null && b == null) {
                                return 0;
                            }

                            if (a == null) {
                                return 1;
                            }

                            if (b == null) {
                                return -1;
                            }

                            return a.getPeriodTo().compareTo(b.getPeriodTo());
                        });

                        int size2 = caseFeeMaintenances.size();
                        for (int j = 0; j < size2; j++) {
                            currentCaseFeeMaintenance = caseFeeMaintenances.get(j);
                            if (currentCaseFeeMaintenance.getValueFrom()
                                .compareTo(currentCaseFeeMaintenance.getValueTo()) >= 0) {
                                return false;
                            }
                        }
                    }

                }
            }
            return true;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CaseFeeTypeDTO searchORFeeItemWithAnalysisCodeCharged(Integer caseFeeTypeId) {
        log.info("searchORFeeItemWithAnalysisCodeCharged - start and caseFeeTypeId=" + caseFeeTypeId);
        CaseFeeTypeDTO caseFeeTypeDTO = null;
        if (caseFeeTypeId != null && caseFeeTypeId.intValue() > 0) {
            CaseFeeType caseFeeType = caseFeeTypeRepository.findOne(caseFeeTypeId);
            if (caseFeeType != null) {
                caseFeeTypeDTO = caseFeeTypeDTOAssembler.toDTO(caseFeeType);
                if (caseFeeType.getCaseType() != null) {
                    caseFeeTypeDTO.setCaseType(caseTypeDTOAssembler.toDTO(caseFeeType.getCaseType()));
                }
                Iterable<FeeCharg> feeChargs
                    = feeChargRepository.findAll(FeeChargPredicate.findByCaseFeeTypeId(caseFeeType.getCaseFeeTypeId()));
                if (feeChargs != null) {
                    List<CaseFeeToBeChargedDTO> caseFeeToBeChargedDTOList = new ArrayList<>();
                    feeChargs.forEach(t -> {
                        CaseFeeToBeChargedDTO caseFeeToBeChargedDTO = new CaseFeeToBeChargedDTO();
                        caseFeeToBeChargedDTO.setCaseFeeToBeChargedId(t.getCaseFeeToBeChargedId());
                        caseFeeToBeChargedDTO.setStatus(t.getStatus());
                        caseFeeToBeChargedDTO.setAnalysisCode(analysisCodeDTOAssembler.toDTO(t.getAnalysisCode()));
                        caseFeeToBeChargedDTOList.add(caseFeeToBeChargedDTO);
                    });
                    caseFeeTypeDTO.setCaseFeeToBeChargeds(caseFeeToBeChargedDTOList);
                }
            }
        }
        log.info("searchORFeeItemWithAnalysisCodeCharged - end and caseFeeTypeDTO=" + caseFeeTypeDTO);
        return caseFeeTypeDTO;
    }

    @Override
    public boolean saveORFeeItemWithAnalysisCodeCharged(CaseFeeTypeDTO caseFeeTypeDTO) {
        log.info("saveORFeeItemWithAnalysisCodeCharged - start and caseFeeTypeDTO=" + caseFeeTypeDTO);
        boolean flag = true;
        if (caseFeeTypeDTO != null && caseFeeTypeDTO.getCaseFeeToBeChargeds() != null) {
            for (CaseFeeToBeChargedDTO c : caseFeeTypeDTO.getCaseFeeToBeChargeds()) {
                FeeCharg feeCharg = null;
                if (c.getCaseFeeToBeChargedId() == null) {
                    feeCharg = new FeeCharg();
                    feeCharg.setCaseFeeType(caseFeeTypeRepository.findOne(caseFeeTypeDTO.getCaseFeeTypeId()));
                } else {
                    feeCharg = feeChargRepository.findOne(c.getCaseFeeToBeChargedId());
                }
                feeCharg.setAnalysisCode(analysisCodeRepository.findOne(c.getAnalysisCode().getAnalysisCodeId()));
                feeCharg.setStatus(c.getStatus());
                Integer result = feeChargRepository.save(feeCharg).getCaseFeeToBeChargedId();
                flag = flag && result > 0 && result != null;
            }
            return flag;
        }
        return false;
    }

}
