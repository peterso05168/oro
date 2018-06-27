package hk.oro.iefas.ws.dividend.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjIntTrAdj;
import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.casemgt.entity.CaseCred;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.dividend.dto.ApprovedAdjucationResultItemDTO;
import hk.oro.iefas.domain.dividend.dto.CreateDividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleCreditorDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDistDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleItemDTO;
import hk.oro.iefas.domain.dividend.dto.ScheduleTypeDTO;
import hk.oro.iefas.domain.dividend.dto.SearchDividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.SysApprovalWfDTO;
import hk.oro.iefas.domain.dividend.dto.WithheldReasonDTO;
import hk.oro.iefas.domain.dividend.entity.DivCalCred;
import hk.oro.iefas.domain.dividend.entity.DivCalCredDist;
import hk.oro.iefas.domain.dividend.entity.DivSchedule;
import hk.oro.iefas.domain.dividend.entity.DivScheduleCred;
import hk.oro.iefas.domain.dividend.entity.DivScheduleDist;
import hk.oro.iefas.domain.dividend.entity.DivScheduleItem;
import hk.oro.iefas.domain.dividend.entity.DivWithheldReasonType;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.adjudication.repository.AdjResultRepository;
import hk.oro.iefas.ws.adjudication.repository.AppAdjResultItemRepository;
import hk.oro.iefas.ws.adjudication.repository.predicate.AdjResultPredicate;
import hk.oro.iefas.ws.casemgt.repository.CaseRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CreditorDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.predicate.CasePredicate;
import hk.oro.iefas.ws.common.service.CommonService;
import hk.oro.iefas.ws.dividend.repository.AdjIntTrAdjRepository;
import hk.oro.iefas.ws.dividend.repository.DivCalCredDistRepository;
import hk.oro.iefas.ws.dividend.repository.DivCalCredRepository;
import hk.oro.iefas.ws.dividend.repository.DivScheduleDistRepository;
import hk.oro.iefas.ws.dividend.repository.DivScheduleItemRepository;
import hk.oro.iefas.ws.dividend.repository.DivScheduleRepository;
import hk.oro.iefas.ws.dividend.repository.WithheldReasonRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.ApproveHistoryDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.ApprovedAdjucationResultItemDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.CreditorTypeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.DividendScheduleDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.DividendScheduleDistDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.DividendScheduleItemDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.AdjIntTrAdjPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.AppAdjResultItemPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivCalCredDistPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivCalCredPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleCredRepository;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleDistPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivSchedulePredicate;
import hk.oro.iefas.ws.dividend.service.DividendScheduleService;
import hk.oro.iefas.ws.system.repository.SysApprovalWFRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@Service
public class DividendScheduleServiceImpl implements DividendScheduleService {

    @Inject
    private CommonService commonService;

    @Autowired
    private DivScheduleRepository divScheduleRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private DivScheduleDistRepository divScheduleDistRepository;

    @Autowired
    private AppAdjResultItemRepository appAdjResultItemRepository;

    @Autowired
    private AdjIntTrAdjRepository adjIntTrAdjRepository;

    @Autowired
    private DivScheduleItemRepository divScheduleItemRepository;

    @Autowired
    private DivScheduleCredRepository divScheduleCredRepository;

    @Autowired
    private SysApprovalWFRepository sysApprovalWFRepository;

    @Autowired
    private AdjResultRepository adjResultRepository;

    @Autowired
    private WithheldReasonRepository withheldReasonRepository;

    @Autowired
    private CreditorTypeDTOAssembler creditorTypeDTOAssembler;

    @Autowired
    private DividendScheduleDTOAssembler dividendScheduleDTOAssembler;

    @Autowired
    private DividendScheduleDistDTOAssembler dividendScheduleDistDTOAssembler;

    @Autowired
    private DividendScheduleItemDTOAssembler dividendScheduleItemDTOAssembler;

    @Autowired
    private ApprovedAdjucationResultItemDTOAssembler appAdjResultItemDTOAssembler;

    @Autowired
    private CreditorDTOAssembler creditorDTOAssembler;

    @Autowired
    private ApproveHistoryDTOAssembler approveHistoryDTOAssembler;

    @Autowired
    private DivCalCredRepository divCalCredRepository;

    @Autowired
    private DivCalCredDistRepository divCalCredDistRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<DividendScheduleDTO> searchDividendScheduleList(SearchDTO<SearchDividendScheduleDTO> searchDTO) {
        log.info("searchDividendScheduleList() start - and param: " + searchDTO);
        Page<DividendScheduleDTO> results = null;
        if (searchDTO != null) {
            Pageable pageable = null;
            if (searchDTO.getPage() != null) {
                pageable = searchDTO.getPage().toPageable();
            }
            if (searchDTO.getCriteria() != null) {
                Page<DivSchedule> page = divScheduleRepository
                    .findAll(DivSchedulePredicate.findByCriteria(searchDTO.getCriteria()), pageable);
                if (page != null) {
                    results = dividendScheduleDTOAssembler.toResultDTO(page);
                }
            }
        }

        log.info("searchDividendScheduleList() end - return : " + results);
        return results;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean validateCreateDividendSchedule(CreateDividendScheduleDTO dto) {
        log.info("createDividendScheduleValidate() start - and param: " + dto);
        boolean result = true;
        if (dto != null) {
            CaseNumberDTO caseNumber = dto.getCaseNumber();
            ScheduleTypeDTO scheduleTypeDTO = dto.getScheduleType();
            if (scheduleTypeDTO == null || scheduleTypeDTO.getScheduleTypeId() == null) {
                return false;
            }
            if (caseNumber != null) {
                Case caseInfo = caseRepository.findOne(CasePredicate.findByCaseNo(caseNumber.getCaseType(),
                    caseNumber.getCaseSequence(), caseNumber.getCaseYear()));
                if (caseInfo != null) {
                    if (dto.getPaymentEffectiveDate() != null) {
                        Date payDate = DateUtils.getEndDate(dto.getPaymentEffectiveDate());
                        Date endDate = DateUtils.getEndDate(new Date());
                        if (payDate.getTime() >= endDate.getTime()) {
                            return false;
                        }
                        List<DivSchedule> divScheduleList = (List<DivSchedule>)divScheduleRepository
                            .findAll(DivSchedulePredicate.findByCaseId(caseInfo.getCaseId()));
                        if (result && divScheduleList != null && !divScheduleList.isEmpty()) {
                            for (DivSchedule divSchedule : divScheduleList) {
                                Date paymentDate = DateUtils.getEndDate(divSchedule.getPaymentDate());
                                if (paymentDate.getTime() == payDate.getTime()) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }

        }
        log.info("createDividendScheduleValidate() end - and return: " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DividendScheduleDistDTO> findByDivScheduleItemId(Integer divScheduleItemId) {
        log.info("findByDivScheduleItemId() start - and divScheduleItemId: " + divScheduleItemId);
        List<DividendScheduleDistDTO> result = null;
        if (divScheduleItemId != null && divScheduleItemId.intValue() > 0) {
            List<DivScheduleDist> divScheduleDistList = (List<DivScheduleDist>)divScheduleDistRepository
                .findAll(DivScheduleDistPredicate.findByDivScheduleItemId(divScheduleItemId));
            if (divScheduleDistList != null && !divScheduleDistList.isEmpty()) {
                result = dividendScheduleDistDTOAssembler.toDTOList(divScheduleDistList);
            }
        }
        log.info("findByDivScheduleItemId() end - and return: " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApprovedAdjucationResultItemDTO> findByCreditor(Integer creditorId) {
        log.info("findByDivScheduleItemId() start - and creditorId " + creditorId);
        List<ApprovedAdjucationResultItemDTO> result = null;
        if (creditorId != null && creditorId.intValue() > 0) {
            AdjResult adjResult = adjResultRepository.findOne(AdjResultPredicate.findByCreditorId(creditorId));
            if (adjResult != null) {
                List<AppAdjResultItem> appAdjResultItemList = (List<AppAdjResultItem>)appAdjResultItemRepository
                    .findAll(AppAdjResultItemPredicate.findByAdjResultId(adjResult.getAdjResultId()));
                result = appAdjResultItemDTOAssembler.toDTOList(appAdjResultItemList);
            }
        }
        log.info("findByDivScheduleItemId() end - and return: " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditorDTO> searchCreditorByCaseId(Integer caseId) {
        log.info("searchCreditorByCaseId - start caseId = " + caseId);
        List<CreditorDTO> creditorDTOs = null;
        if (caseId != null && caseId.intValue() > 0) {
            Case caseInfo = caseRepository.findOne(caseId);
            if (caseInfo != null) {
                List<CaseCred> caseCredList = caseInfo.getCaseCreds();
                creditorDTOs = creditorDTOAssembler.toDTOList(caseCredList);
            }
        }
        log.info("searchCreditorByCaseId - end and return = " + creditorDTOs);
        return creditorDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal searchTotalInterestAmount(Integer creditorId) {
        BigDecimal result = null;
        if (creditorId != null && creditorId.intValue() > 0) {
            AdjResult adjResult = adjResultRepository.findOne(AdjResultPredicate.findByCreditorId(creditorId));
            if (adjResult != null) {
                AdjIntTrAdj adjIntTrAdj
                    = adjIntTrAdjRepository.findOne(AdjIntTrAdjPredicate.findByAdjResultId(adjResult.getAdjResultId()));
                if (adjIntTrAdj != null) {
                    result = adjIntTrAdj.getTotalIntAmount();
                }
            }
        }
        log.info("findByDivScheduleItemId() end - and return: " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveDividendSchedule(DividendScheduleDTO dividendScheduleDTO) {
        log.info("saveDividendSchedule() start - and dividendScheduleDTO: " + dividendScheduleDTO);
        Integer result = null;
        if (dividendScheduleDTO != null) {
            // save dividendSchedule
            Integer divScheduleId = dividendScheduleDTO.getDividendScheduleId();
            DivSchedule divSchedule = null;
            SysApprovalWfDTO sysApprovalWfDTO = dividendScheduleDTO.getSysApprovalWf();
            Integer workflowId = null;
            if (sysApprovalWfDTO != null) {
                workflowId = commonService.saveSysApprovalWf(dividendScheduleDTO.getWorkFlowId(), sysApprovalWfDTO);
                dividendScheduleDTO.setWorkFlowId(workflowId);
            }
            if (divScheduleId != null && divScheduleId.intValue() > 0) {
                divSchedule = divScheduleRepository.findOne(divScheduleId);
                if (divSchedule != null) {
                    divSchedule.setPaymentDate(dividendScheduleDTO.getPaymentEffectiveDate());
                    divSchedule.setPlegeType(dividendScheduleDTO.getPledgeType());
                    divSchedule.setScheduleType(dividendScheduleDTO.getScheduleType());
                    divSchedule.setStatus(dividendScheduleDTO.getStatus());
                }
            } else {
                divSchedule = dividendScheduleDTOAssembler.toEntity(dividendScheduleDTO);
            }
            divSchedule.setWorkFlowId(workflowId);
            divSchedule = divScheduleRepository.save(divSchedule);
            result = divSchedule.getDivSchdId();
            if (result != null) {
                // save dividendScheduleCreditor
                List<DividendScheduleCreditorDTO> dividendScheduleCreditorList
                    = dividendScheduleDTO.getDividendScheduleCreditorList();
                if (dividendScheduleCreditorList != null && !dividendScheduleCreditorList.isEmpty()) {
                    for (DividendScheduleCreditorDTO diviScheCred : dividendScheduleCreditorList) {
                        Integer diviScheCredId = diviScheCred.getScheduleCredId();
                        DivScheduleCred divScheduleCred = null;
                        if (diviScheCredId != null && diviScheCredId.intValue() > 0) {
                            divScheduleCred = divScheduleCredRepository.findOne(diviScheCredId);
                        } else {
                            divScheduleCred = new DivScheduleCred();
                        }
                        divScheduleCred
                            .setAdjCredType(creditorTypeDTOAssembler.toEntity(diviScheCred.getAdjCredType()));
                        divScheduleCred.setRemark(diviScheCred.getRemark());
                        divScheduleCred.setStatus(diviScheCred.getStatus());
                        divScheduleCred.setDivSchdId(BigDecimal.valueOf(result));
                        divScheduleCredRepository.save(divScheduleCred);
                    }
                }
                // save divscheduleitem
                List<DividendScheduleItemDTO> dividendScheduleItemList = dividendScheduleDTO.getDividendScheduleItems();
                if (dividendScheduleItemList != null && !dividendScheduleItemList.isEmpty()) {
                    for (DividendScheduleItemDTO dividendScheduleItemDTO : dividendScheduleItemList) {
                        Integer divScheduleItemId = dividendScheduleItemDTO.getDividendScheduleItemId();
                        DivScheduleItem divScheduleItem = null;
                        if (divScheduleItemId != null && divScheduleItemId.intValue() > 0) {
                            divScheduleItem = divScheduleItemRepository.findOne(divScheduleItemId);
                        } else {
                            divScheduleItem = new DivScheduleItem();
                        }
                        divScheduleItem = DataUtils.copyProperties(dividendScheduleItemDTO, DivScheduleItem.class);
                        divScheduleItem.setDivScheduleItemId(dividendScheduleItemDTO.getDividendScheduleItemId());
                        divScheduleItem.setDistAmount(dividendScheduleItemDTO.getDistributionAmount());
                        divScheduleItem.setDistPercent(dividendScheduleItemDTO.getDistributionPercentage());
                        divScheduleItem.setVoucherPart(dividendScheduleItemDTO.getVoucherPart());
                        divScheduleItem.setStatus(dividendScheduleItemDTO.getStatus());
                        WithheldReasonDTO withheldReasonType = dividendScheduleItemDTO.getWithheldReason();
                        if (withheldReasonType != null) {
                            Integer withheldReasonTypeId = withheldReasonType.getWithheldReasonId();
                            if (withheldReasonTypeId != null && withheldReasonTypeId.intValue() > 0) {
                                DivWithheldReasonType divWithheldReasonType
                                    = withheldReasonRepository.findOne(withheldReasonTypeId);
                                divScheduleItem.setDivWithheldReasonType(divWithheldReasonType);
                            }
                        }
                        BigDecimal withheldAmount = dividendScheduleItemDTO.getWithheldAmount();
                        if (withheldAmount == null) {
                            withheldAmount = BigDecimal.ZERO;
                        }
                        divScheduleItem.setWithheldAmount(withheldAmount);
                        AdjResult adjResult = null;
                        Integer adjResultId = null;
                        Integer creditorId = dividendScheduleItemDTO.getCreditor().getCreditorId();
                        CreditorDTO creditorDTO = dividendScheduleItemDTO.getCreditor();
                        if (creditorDTO != null) {
                            if (creditorId != null && creditorId.intValue() > 0) {
                                adjResult
                                    = adjResultRepository.findOne(AdjResultPredicate.findByCreditorId(creditorId));
                                adjResultId = adjResult.getAdjResultId();
                                divScheduleItem.setAdjResultId(adjResultId);
                            }
                        }
                        divScheduleItem.setDivSchedule(divSchedule);
                        Integer divScheItemId = divScheduleItemRepository.save(divScheduleItem).getDivScheduleItemId();
                        // save DivScheDist and update appAdjResultitem
                        if (divScheItemId != null && divScheItemId.intValue() > 0) {
                            List<DividendScheduleDistDTO> dividendScheduleDistDTOList
                                = dividendScheduleItemDTO.getDividendScheduleDistList();
                            if (dividendScheduleDistDTOList != null && !dividendScheduleDistDTOList.isEmpty()) {
                                for (DividendScheduleDistDTO divScheDist : dividendScheduleDistDTOList) {
                                    Integer divScheDistId = divScheDist.getScheduleDistId();
                                    DivScheduleDist divScheduleDist = null;
                                    if (divScheDistId != null && divScheDistId.intValue() > 0) {
                                        divScheduleDist = divScheduleDistRepository.findOne(divScheDistId);
                                    } else {
                                        divScheduleDist = new DivScheduleDist();
                                    }
                                    if (divScheduleItem.getStatus().equals(CoreConstant.STATUS_INACTIVE)) {
                                        divScheduleDist.setStatus(CoreConstant.STATUS_INACTIVE);
                                    } else {
                                        divScheduleDist.setStatus(divScheDist.getStatus());
                                    }
                                    divScheduleDist.setDivScheduleItem(divScheduleItem);
                                    divScheduleDist.setAmountPaid(divScheDist.getAmountPaid());
                                    divScheduleDist.setPercentPaid(divScheDist.getPercentPaid());
                                    String appType = divScheDist.getAppAdjResultItem().getAdjudicationType()
                                        .getAdjudicationTypeName();
                                    AppAdjResultItem appAdjResultItem = appAdjResultItemRepository.findOne(
                                        AppAdjResultItemPredicate.findByAdjResultIdAndAdjType(adjResultId, appType));
                                    divScheduleDist.setAppAdjResultItem(appAdjResultItem);
                                    divScheduleDistRepository.save(divScheduleDist);
                                    List<DivScheduleDist> divScheduleDistList = (List<
                                        DivScheduleDist>)divScheduleDistRepository.findAll(DivScheduleDistPredicate
                                            .findByAppAdjResultItemId(appAdjResultItem.getAppResultItemId()));
                                    if (divScheduleDistList != null && !divScheduleDistList.isEmpty()) {
                                        BigDecimal totalAmountPaid = BigDecimal.ZERO;
                                        for (DivScheduleDist dist : divScheduleDistList) {
                                            if (dist.getAmountPaid() != null) {
                                                totalAmountPaid = totalAmountPaid.add(dist.getAmountPaid());
                                            }
                                        }
                                        appAdjResultItem.setAmountPaid(totalAmountPaid);
                                        BigDecimal percentPaid
                                            = CommonUtils.getBigDecimal(appAdjResultItem.getAmountPaid()).divide(
                                                CommonUtils.getBigDecimal(appAdjResultItem.getAdmAmount()), 2,
                                                RoundingMode.HALF_UP);
                                        appAdjResultItem.setPercentPaid(percentPaid);
                                        appAdjResultItemRepository.save(appAdjResultItem);

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("saveDividendSchedule() end - and return: result =" + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public DividendScheduleItemDTO searchDividendScheduleItemById(Integer divScheduleItemId) {
        log.info("searchDividendScheduleItemById() start - and divScheduleId =" + divScheduleItemId);
        DividendScheduleItemDTO result = null;
        if (divScheduleItemId != null && divScheduleItemId.intValue() > 0) {
            DivScheduleItem divScheduleItem = divScheduleItemRepository.findOne(divScheduleItemId);
            if (divScheduleItem != null) {
                result = dividendScheduleItemDTOAssembler.toDTO(divScheduleItem);
            }
        }
        log.info("searchDividendScheduleItemById() end - and return: result =" + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public DividendScheduleDTO searchDividendSchedule(Integer divScheduleId) {
        log.info("searchDividendScheduleItemById() start - and divScheduleId =" + divScheduleId);
        DividendScheduleDTO result = null;
        if (divScheduleId != null && divScheduleId.intValue() > 0) {
            DivSchedule divSchedule = divScheduleRepository.findOne(divScheduleId);
            if (divSchedule != null) {
                result = dividendScheduleDTOAssembler.toDTO(divSchedule);
                Integer workFlowId = divSchedule.getWorkFlowId();
                if (workFlowId != null && workFlowId.intValue() > 0) {
                    List<SysApprovalWf> sysApprovalWfs
                        = sysApprovalWFRepository.findByWorkflowIdAndStatusIgnoreCaseOrderByApprovalWorkflowId(
                            workFlowId, CoreConstant.STATUS_ACTIVE);
                    result.setApproveHistory(approveHistoryDTOAssembler.toDTOList(sysApprovalWfs));
                }
            }
        }
        log.info("searchDividendScheduleItemById() end - and return: result =" + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal findCredTypePercentageByCredTypeId(Integer credTypeId) {
        log.info("findCredTypePercentageByCredTypeId() start - and credTypeId =" + credTypeId);
        BigDecimal result = BigDecimal.ZERO;
        if (credTypeId != null && credTypeId.intValue() > 0) {
            DivCalCred divCalCred = divCalCredRepository.findOne(DivCalCredPredicate.findByCredTypeId(credTypeId));
            if (divCalCred != null) {
                Integer divCalculationId = divCalCred.getDivCalId();
                if (divCalculationId != null && divCalculationId.intValue() > 0) {
                    List<DivCalCredDist> divCalCredDistList = (List<DivCalCredDist>)divCalCredDistRepository
                        .findAll(DivCalCredDistPredicate.findBydivCalId(divCalculationId));
                    if (divCalCredDistList != null && !divCalCredDistList.isEmpty()) {
                        if (divCalCredDistList.size() > 1) {
                            for (DivCalCredDist divCalCredDist : divCalCredDistList) {
                                result = divCalCredDist.getPercent();
                                if (result.compareTo(BigDecimal.valueOf(100)) != 0) {
                                    break;
                                }
                            }
                        } else {
                            result = divCalCredDistList.get(0).getPercent();
                        }
                    }
                }
            }
        }
        log.info("findCredTypePercentageByCredTypeId() end - and return: result =" + result);
        return result;
    }

}
