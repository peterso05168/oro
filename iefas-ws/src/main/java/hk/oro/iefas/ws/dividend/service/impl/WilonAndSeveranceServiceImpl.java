package hk.oro.iefas.ws.dividend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.casemgt.entity.CaseCred;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.dividend.dto.CreateWilonAndSeveranceDTO;
import hk.oro.iefas.domain.dividend.dto.SearchWilonAndSeverancePayCriteriaDTO;
import hk.oro.iefas.domain.dividend.dto.WilonAndSeverancePayDTO;
import hk.oro.iefas.domain.dividend.entity.DivWilonAndSever;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.casemgt.repository.CaseCredRepository;
import hk.oro.iefas.ws.casemgt.repository.CaseRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CreditorDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.predicate.CasePredicate;
import hk.oro.iefas.ws.dividend.repository.WilonAndSeverRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.WilonAndSeveranceDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.WilonAndSeverPredicate;
import hk.oro.iefas.ws.dividend.service.WilonAndSeveranceService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3087 $ $Date: 2018-06-12 18:02:20 +0800 (週二, 12 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@Service
public class WilonAndSeveranceServiceImpl implements WilonAndSeveranceService {

    @Autowired
    private WilonAndSeverRepository wilonAndSeverRepository;

    @Autowired
    private WilonAndSeveranceDTOAssembler wILONAndSeveranceDTOAssembler;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private CreditorDTOAssembler creditorDTOAssembler;

    @Autowired
    private CaseCredRepository caseCredRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<WilonAndSeverancePayDTO>
        searchWILONAndSeveranceList(SearchDTO<SearchWilonAndSeverancePayCriteriaDTO> criteria) {
        log.info("searchWILONAndSeveranceList - start and criteria =" + criteria);
        Page<WilonAndSeverancePayDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                String sortField = criteria.getPage().getSortField();
                if (sortField == null) {
                    criteria.getPage().setSortField("wilonAndSevrnPayId");
                }
                pageable = criteria.getPage().toPageable();
            }
            CaseNumberDTO caseNumberDTO = criteria.getCriteria().getCaseNumber();
            if (caseNumberDTO != null) {
                String caseType = caseNumberDTO.getCaseType();
                String caseSequence = caseNumberDTO.getCaseSequence();
                String caseYear = caseNumberDTO.getCaseYear();
                if (caseType != null && caseSequence != null && caseYear != null) {
                    Page<DivWilonAndSever> divWilonAndSevers = wilonAndSeverRepository.findAll(
                        WilonAndSeverPredicate.findByCaseNumber(criteria.getCriteria().getCaseNumber()), pageable);
                    if (divWilonAndSevers != null) {
                        result = wILONAndSeveranceDTOAssembler.toResultDTO(divWilonAndSevers);
                    }
                }
            }

        }
        log.info("searchWILONAndSeveranceList - end and return =" + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveWILONAndSeverancePay(WilonAndSeverancePayDTO wilonAndSeverancePay) {
        log.info("saveWILONAndSeverancePay - start wilonAndSeverancePay = " + wilonAndSeverancePay);
        Integer result = null;
        if (wilonAndSeverancePay != null) {
            DivWilonAndSever wilonAndSever = null;
            Integer wilonAndSeverancePayId = wilonAndSeverancePay.getWilonAndSevrnPayId();
            wilonAndSever = wILONAndSeveranceDTOAssembler.toEntity(wilonAndSeverancePay);
            if (wilonAndSeverancePayId == null) {
                CreditorDTO creditorDTO = wilonAndSeverancePay.getCaseCred();
                if (creditorDTO != null) {
                    CaseCred caseCred = caseCredRepository.findOne(creditorDTO.getCreditorId());
                    wilonAndSever.setCaseCred(caseCred);
                }
                CaseDTO caseDTO = wilonAndSeverancePay.getCaseInfo();
                if (caseDTO != null) {
                    Case caseInfo = caseRepository.findOne(caseDTO.getCaseId());
                    wilonAndSever.setCaseInfo(caseInfo);
                }
                wilonAndSever.setStatus(CoreConstant.STATUS_ACTIVE);
            }
            result = wilonAndSeverRepository.save(wilonAndSever).getWilonAndSevrnPayId();

        }
        log.info("saveWILONAndSeverancePay - end and result =" + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditorDTO> searchCreditorByCaseNumber(CaseNumberDTO caseNumberDTO) {
        log.info("searchCreditorByCaseNumber - start caseNumberDTO = " + caseNumberDTO);
        List<CreditorDTO> creditorDTOs = null;
        if (caseNumberDTO != null) {
            String caseType = caseNumberDTO.getCaseType();
            String caseSequence = caseNumberDTO.getCaseSequence();
            String caseYear = caseNumberDTO.getCaseYear();
            if (caseSequence != null && caseType != null && caseYear != null) {
                Case caseInfo = caseRepository.findOne(CasePredicate.findByCaseNo(caseType, caseSequence, caseYear));
                if (caseInfo != null) {
                    creditorDTOs = creditorDTOAssembler.toDTOList(caseInfo.getCaseCreds());
                }
            }
        }
        log.info("searchCreditorByCaseNumber - end and return = " + creditorDTOs);
        return creditorDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public WilonAndSeverancePayDTO searchWILONAndSeverancePayById(Integer wilonAndSeverancePayId) {
        log.info("searchWILONAndSeverancePayById - start and return = " + wilonAndSeverancePayId);
        WilonAndSeverancePayDTO reuslt = null;
        if (wilonAndSeverancePayId != null && wilonAndSeverancePayId.intValue() > 0) {
            DivWilonAndSever divWilonAndSever = wilonAndSeverRepository.findOne(wilonAndSeverancePayId);
            if (divWilonAndSever != null) {
                reuslt = wILONAndSeveranceDTOAssembler.toDTO(divWilonAndSever);
            }
        }
        log.info("searchWILONAndSeverancePayById - end and return:reuslt= " + reuslt);
        return reuslt;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean createWILONAndSeveranceValidate(CreateWilonAndSeveranceDTO createWILONAndSeverance) {
        log.info("createWILONAndSeveranceValidate - start and CreateWILONAndSeveranceDTO = " + createWILONAndSeverance);
        boolean result = false;
        if (createWILONAndSeverance != null) {
            Integer creditorId = createWILONAndSeverance.getCreditorId();
            CaseNumberDTO caseNumber = createWILONAndSeverance.getCaseNumber();
            if (creditorId != null && caseNumber != null) {
                List<DivWilonAndSever> wilonAndSeverSearchResults = (List<DivWilonAndSever>)wilonAndSeverRepository
                    .findAll(WilonAndSeverPredicate.findByCaseNumber(createWILONAndSeverance.getCaseNumber()));
                if (wilonAndSeverSearchResults.size() > 0) {
                    for (DivWilonAndSever wilonAndSever : wilonAndSeverSearchResults) {
                        if (wilonAndSever.getCaseCred().getCreditorId().equals(creditorId)) {
                            result = true;
                            break;
                        }
                    }
                }
            }
        }
        log.info("searchWILONAndSeverancePayById - end and return:reuslt= " + result);
        return result;
    }
}
