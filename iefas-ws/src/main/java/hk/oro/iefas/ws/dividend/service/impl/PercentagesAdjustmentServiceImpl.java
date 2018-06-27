package hk.oro.iefas.ws.dividend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.dividend.dto.ApprovedAdjucationResultItemDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDistDTO;
import hk.oro.iefas.domain.dividend.dto.PercentagesAdjustmentDTO;
import hk.oro.iefas.domain.dividend.dto.SearchPercentagesAdjustmentCriteriaDTO;
import hk.oro.iefas.domain.dividend.entity.DivScheduleDist;
import hk.oro.iefas.domain.search.dto.PageDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.adjudication.repository.AdjResultRepository;
import hk.oro.iefas.ws.adjudication.repository.AppAdjResultItemRepository;
import hk.oro.iefas.ws.casemgt.repository.CaseRepository;
import hk.oro.iefas.ws.casemgt.repository.predicate.CasePredicate;
import hk.oro.iefas.ws.dividend.repository.DivScheduleDistRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.DividendScheduleDistDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.PercentagesAdjustmentDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleDistPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.PercentagesAdjustmentPredicate;
import hk.oro.iefas.ws.dividend.service.PercentagesAdjustmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3112 $ $Date: 2018-06-13 15:48:09 +0800 (週三, 13 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@Service
public class PercentagesAdjustmentServiceImpl implements PercentagesAdjustmentService {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private AdjResultRepository adjResultRepository;

    @Autowired
    private AppAdjResultItemRepository appAdjResultItemRepository;

    @Autowired
    private PercentagesAdjustmentDTOAssembler percentagesAdjustmentDTOAssembler;

    @Autowired
    private DividendScheduleDistDTOAssembler divScheduleDistAssembler;

    @Autowired
    private DivScheduleDistRepository divScheduleDistRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<PercentagesAdjustmentDTO>
        searchPercentagesAdjustmentList(SearchDTO<SearchPercentagesAdjustmentCriteriaDTO> criteria) {
        log.info("searchPercentagesAdjustmentList - start and SearchPercentagesAdjustmentCriteriaDTO =" + criteria);
        Page<PercentagesAdjustmentDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            PageDTO page = criteria.getPage();
            if (page != null) {
                pageable = page.toPageable();
            }
            CaseNumberDTO caseNumber = criteria.getCriteria().getCaseNumber();
            if (caseNumber != null && caseNumber.getCaseSequence() != null && caseNumber.getCaseType() != null
                && caseNumber.getCaseYear() != null) {
                Case caseInfo = caseRepository.findOne(CasePredicate.findByCaseNo(caseNumber.getCaseType(),
                    caseNumber.getCaseSequence(), caseNumber.getCaseYear()));
                if (caseInfo != null) {
                    Page<AdjResult> adjResults = adjResultRepository
                        .findAll(PercentagesAdjustmentPredicate.findByCriteria(caseInfo), pageable);
                    if (adjResults != null) {
                        result = percentagesAdjustmentDTOAssembler.toResultDTO(adjResults);
                    }
                }
            }
        }
        log.info("searchPercentagesAdjustmentList - end and Page<PercentagesAdjustmentDTO> =" + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean savePercentageAdjustment(PercentagesAdjustmentDTO percentagesAdjustment) {
        log.info("savePercentageAdjustment - start and percentagesAdjustment =" + percentagesAdjustment);
        boolean flag = true;
        if (percentagesAdjustment != null) {
            List<ApprovedAdjucationResultItemDTO> appAdjResultItemList = percentagesAdjustment.getAppAdjResultItem();
            if (appAdjResultItemList != null) {
                for (ApprovedAdjucationResultItemDTO appAdjResult : appAdjResultItemList) {
                    Integer appAdjResultId = appAdjResult.getApprovedAdjucationResultItemId();
                    AppAdjResultItem adjResultItem = appAdjResultItemRepository.findOne(appAdjResultId);
                    Integer appAdjSaveResult = null;
                    Integer divScheduleDistSaveResult = null;
                    if (adjResultItem != null) {
                        adjResultItem.setAmountPaid(appAdjResult.getAmountPaid());
                        adjResultItem.setPercentPaid(appAdjResult.getPercentPaid());
                        appAdjSaveResult = appAdjResultItemRepository.save(adjResultItem).getAppResultItemId();
                    }
                    DivScheduleDist divScheduleDist = this.divScheduleDistRepository.findOne(DivScheduleDistPredicate
                        .findByAppAdjResultItemId(appAdjResult.getApprovedAdjucationResultItemId()));
                    if (divScheduleDist != null) {
                        divScheduleDist.setAmountPaid(appAdjResult.getAmountPaid());
                        divScheduleDist.setPercentPaid(appAdjResult.getPercentPaid());
                        divScheduleDistSaveResult
                            = this.divScheduleDistRepository.save(divScheduleDist).getScheduleDistId();
                    }
                    if (appAdjSaveResult == null || divScheduleDistSaveResult == null) {
                        flag = false;
                        break;
                    }
                }
            }
            log.info("savePercentageAdjustment - end and return : flag =" + flag);
            return flag;
        }
        log.info("savePercentageAdjustment - end and return =" + false);
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public PercentagesAdjustmentDTO searchPercentagesAdjustmentDTO(Integer adjudicationResultId) {
        log.info("searchPercentagesAdjustmentDTO - start and adjudicationResultId =" + adjudicationResultId);
        PercentagesAdjustmentDTO percentagesAdjustmentDTO = null;
        if (adjudicationResultId != null && adjudicationResultId.intValue() > 0) {
            AdjResult adjResult = adjResultRepository.findOne(adjudicationResultId);
            if (adjResult != null) {
                percentagesAdjustmentDTO = new PercentagesAdjustmentDTO();
                percentagesAdjustmentDTO = percentagesAdjustmentDTOAssembler.toDTO(adjResult);
            }
        }
        log.info("searchPercentagesAdjustmentDTO - end and percentagesAdjustmentDTO =" + percentagesAdjustmentDTO);
        return percentagesAdjustmentDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public DividendScheduleDistDTO searchDivScheduleDistByAppAdjResultItemId(Integer appAdjResultItemId) {
        log.info("searchDivScheduleDistByAppAdjResultItemId - start and appAdjResultItemId =" + appAdjResultItemId);
        DividendScheduleDistDTO result = null;
        if (appAdjResultItemId != null && appAdjResultItemId.intValue() > 0) {
            DivScheduleDist divScheduleDist = this.divScheduleDistRepository
                .findOne(DivScheduleDistPredicate.findByAppAdjResultItemId(appAdjResultItemId));
            result = divScheduleDistAssembler.toDTO(divScheduleDist);
        }
        log.info("searchDivScheduleDistByAppAdjResultItemId - end and result =" + result);
        return result;
    }
}
