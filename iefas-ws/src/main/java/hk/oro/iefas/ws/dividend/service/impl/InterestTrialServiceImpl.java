package hk.oro.iefas.ws.dividend.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.constant.WorkFlowAction;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjIntTrAdj;
import hk.oro.iefas.domain.adjudication.entity.AdjIntTrialAccItem;
import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.adjudication.entity.AdjResultIntItem;
import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.dividend.dto.AdjIntTrialAccItemDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationResultIntItemDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleItemDTO;
import hk.oro.iefas.domain.dividend.dto.InterestTrialAdjudicationDTO;
import hk.oro.iefas.domain.dividend.dto.SearchAdjudicationCriteriaDTO;
import hk.oro.iefas.domain.dividend.dto.SearchInterestTrialCriteriaDTO;
import hk.oro.iefas.domain.dividend.dto.SysApprovalWfDTO;
import hk.oro.iefas.domain.dividend.entity.DivScheduleItem;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.adjudication.repository.AdjResultRepository;
import hk.oro.iefas.ws.adjudication.repository.AppAdjResultItemRepository;
import hk.oro.iefas.ws.adjudication.repository.predicate.AdjResultPredicate;
import hk.oro.iefas.ws.common.service.CommonService;
import hk.oro.iefas.ws.dividend.repository.AdjIntTrAdjRepository;
import hk.oro.iefas.ws.dividend.repository.AdjIntTrialAccItemRepository;
import hk.oro.iefas.ws.dividend.repository.AdjResultIntItemRepository;
import hk.oro.iefas.ws.dividend.repository.AdjTypeRepository;
import hk.oro.iefas.ws.dividend.repository.DivScheduleItemRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.ApproveHistoryDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.DividendScheduleItemDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.InterestTrialAdjudicationDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.AdjIntTrAdjPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.AdjResultIntItemPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleItemPredicate;
import hk.oro.iefas.ws.dividend.service.InterestTrialService;
import hk.oro.iefas.ws.system.repository.SysApprovalWFRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3164 $ $Date: 2018-06-15 16:32:01 +0800 (週五, 15 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class InterestTrialServiceImpl implements InterestTrialService {
    @Autowired
    private CommonService commonService;

    @Autowired
    private AdjIntTrAdjRepository adjIntTrAdjRepository;

    @Autowired
    private SysApprovalWFRepository sysApprovalWFRepository;

    @Autowired
    private AdjIntTrialAccItemRepository adjIntTrialAccItemRepository;

    @Autowired
    private AdjResultRepository adjResultRepository;

    @Autowired
    private DivScheduleItemRepository divScheduleItemRepository;

    @Autowired
    private AdjResultIntItemRepository adjResultIntItemRepository;

    @Autowired
    private AdjTypeRepository adjTypeRepository;

    @Autowired
    private AppAdjResultItemRepository appAdjResultItemRepository;

    @Autowired
    private InterestTrialAdjudicationDTOAssembler interestTrialAdjudicationDTOAssembler;

    @Autowired
    private DividendScheduleItemDTOAssembler dividendScheduleItemDTOAssembler;

    @Autowired
    private ApproveHistoryDTOAssembler approveHistoryDTOAssembler;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<InterestTrialAdjudicationDTO>
        searchInterestTrialList(SearchDTO<SearchInterestTrialCriteriaDTO> criteria) {
        log.info("searchInterestTrialList - start - and criteria =" + criteria);
        Page<InterestTrialAdjudicationDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            SearchAdjudicationCriteriaDTO criteriaDTO = new SearchAdjudicationCriteriaDTO();
            criteriaDTO.setStatus(CoreConstant.COMMON_STATUS_APPROVED);
            criteriaDTO.setCaseId(criteria.getCriteria().getCaseId());
            List<AdjResult> adjResults
                = (List<AdjResult>)adjResultRepository.findAll(AdjResultPredicate.findByCriteria(criteriaDTO, null));
            Page<AdjIntTrAdj> adjIntTrAdjs
                = adjIntTrAdjRepository.findAll(AdjIntTrAdjPredicate.findByAdjResults(adjResults), pageable);
            result = interestTrialAdjudicationDTOAssembler.toResultDTO(adjIntTrAdjs);
        }
        log.info("searchInterestTrialList - end - and return result =" + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public InterestTrialAdjudicationDTO searchInterestTrialById(Integer interestTrialAdjudicationId) {
        log.info("searchInterestTrial - start - and interestTrialAdjudicationId =" + interestTrialAdjudicationId);
        InterestTrialAdjudicationDTO result = null;
        if (interestTrialAdjudicationId != null && interestTrialAdjudicationId.intValue() > 0) {
            AdjIntTrAdj adjIntTrAdj = adjIntTrAdjRepository.findOne(interestTrialAdjudicationId);
            if (adjIntTrAdj != null) {
                result = interestTrialAdjudicationDTOAssembler.toDTO(adjIntTrAdj);
                Integer workFlowId = adjIntTrAdj.getWorkFlowId();
                if (workFlowId != null && workFlowId.intValue() > 0) {
                    List<SysApprovalWf> sysApprovalWfs
                        = sysApprovalWFRepository.findByWorkflowIdAndStatusIgnoreCaseOrderByApprovalWorkflowId(
                            workFlowId, CoreConstant.STATUS_ACTIVE);
                    result.setApproveHistories(approveHistoryDTOAssembler.toDTOList(sysApprovalWfs));
                }
            }
        }

        log.info("searchInterestTrial - end - and return result =" + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public InterestTrialAdjudicationDTO createInterestTrial(Integer creditorId) {
        log.info("createInterestTrial start - and creditorId =" + creditorId);
        InterestTrialAdjudicationDTO result = null;
        // OneToOne map between adjResult and caseCreditor
        List<AdjResult> adjResults
            = (List<AdjResult>)adjResultRepository.findAll(AdjResultPredicate.findApprovedAdjByCreditor(creditorId));
        if (CommonUtils.isNotEmpty(adjResults)) {
            AdjResult adjResult = adjResults.get(0);
            List<AdjIntTrAdj> adjIntTrAdjs = (List<AdjIntTrAdj>)adjIntTrAdjRepository
                .findAll(AdjIntTrAdjPredicate.findByAdjResultId(adjResult.getAdjResultId()));
            if (CommonUtils.isEmpty(adjIntTrAdjs)) {
                result = interestTrialAdjudicationDTOAssembler.toDTO(adjResult);
            }
        }
        log.info("createInterestTrial end - and result =" + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DividendScheduleItemDTO> searchDivScheduleItemByAdjResultId(Integer adjResultId) {
        log.info("searchDivScheduleItemByAdjResultId start - and adjResultId =" + adjResultId);
        List<DividendScheduleItemDTO> result = null;
        if (adjResultId != null && adjResultId.intValue() > 0) {
            List<DivScheduleItem> dividendScheduleItemList = (List<DivScheduleItem>)divScheduleItemRepository
                .findAll(DivScheduleItemPredicate.findByAdjResultId(adjResultId));
            if (dividendScheduleItemList != null && !dividendScheduleItemList.isEmpty()) {
                result = dividendScheduleItemDTOAssembler.toDTOList(dividendScheduleItemList);
            }
        }
        log.info("searchDivScheduleItemByAdjResultId end - and result =" + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveInterestTrial(InterestTrialAdjudicationDTO dto) {
        log.info("saveInterestTrial - start - and dto =" + dto);
        Integer result = null;
        if (dto != null) {
            AdjIntTrAdj adjIntTrAdj = DataUtils.copyProperties(dto, AdjIntTrAdj.class);
            Integer adjResultId = dto.getAdjResultId();
            if (adjResultId != null && adjResultId.intValue() > 0) {
                AdjResult adjResult = adjResultRepository.findOne(adjResultId);
                adjIntTrAdj.setAdjResult(adjResult);
            }

            SysApprovalWfDTO sysApprovalWfDTO = dto.getSysApprovalWf();
            if (sysApprovalWfDTO != null) {
                Integer workflowId = commonService.saveSysApprovalWf(dto.getWorkFlowId(), sysApprovalWfDTO);
                adjIntTrAdj.setWorkFlowId(workflowId);
            }
            result = adjIntTrAdjRepository.save(adjIntTrAdj).getIntTrAdjId();
            List<AdjIntTrialAccItemDTO> adjIntTrialAccItemList = dto.getAdjIntTrialAccItemList();
            if (adjIntTrialAccItemList != null && !adjIntTrialAccItemList.isEmpty()) {
                for (AdjIntTrialAccItemDTO intTrialAccItem : adjIntTrialAccItemList) {
                    AdjIntTrialAccItem adjIntTrialAccItem
                        = DataUtils.copyProperties(intTrialAccItem, AdjIntTrialAccItem.class);
                    adjIntTrialAccItem.setAdjIntTrAdjId(result);
                    adjIntTrialAccItemRepository.save(adjIntTrialAccItem);
                }
            }

            List<AdjucationResultIntItemDTO> adjucationResultIntItemList = dto.getAdjucationResultIntItemList();
            if (CommonUtils.isNotEmpty(adjucationResultIntItemList)) {
                boolean judgeIsGenerate = adjucationResultIntItemList.get(0).getAdjIntResultItemId() == null;
                if (judgeIsGenerate) {
                    List<AdjResultIntItem> adjResultIntItems = (List<AdjResultIntItem>)adjResultIntItemRepository
                        .findAll(AdjResultIntItemPredicate.findByadjIntTrialAdjResultId(result));
                    if (CommonUtils.isNotEmpty(adjResultIntItems)) {
                        for (AdjResultIntItem adjResultIntItem : adjResultIntItems) {
                            adjResultIntItem.setStatus(CoreConstant.STATUS_INACTIVE);
                            adjResultIntItemRepository.save(adjResultIntItem);
                        }
                    }
                }
                for (AdjucationResultIntItemDTO adjResIntItem : adjucationResultIntItemList) {
                    AdjResultIntItem adjResultIntItem = DataUtils.copyProperties(adjResIntItem, AdjResultIntItem.class);
                    adjResultIntItem.setAdjIntTrAdjId(result);
                    adjResultIntItemRepository.save(adjResultIntItem);
                }
            }
            if (dto.getSysApprovalWf() != null
                && WorkFlowAction.Approve.name().equals(dto.getSysApprovalWf().getAction())) {
                saveAppAdjResultItem(dto);
            }
        }
        log.info("saveInterestTrial - end - and return result =" + result);
        return result;
    }

    private void saveAppAdjResultItem(InterestTrialAdjudicationDTO dto) {
        AppAdjResultItem appAdjResultItem = new AppAdjResultItem();
        appAdjResultItem.setStatus(CoreConstant.STATUS_ACTIVE);
        appAdjResultItem.setAdjResultId(dto.getAdjResultId());
        appAdjResultItem.setAdmAmount(dto.getTotalIntAmount());
        appAdjResultItem.setAmountPaid(BigDecimal.ZERO);
        appAdjResultItem.setPercentPaid(BigDecimal.ZERO);
        appAdjResultItem.setAdjType(adjTypeRepository.findByAdjTypeDesc(DividendConstant.ADJTYPE_INT));
        appAdjResultItemRepository.save(appAdjResultItem);
    }
}
