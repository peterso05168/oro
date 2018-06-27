package hk.oro.iefas.ws.dividend.repository.assembler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjAccNumber;
import hk.oro.iefas.domain.adjudication.entity.AdjApplyItem;
import hk.oro.iefas.domain.adjudication.entity.AdjIntTrAdj;
import hk.oro.iefas.domain.adjudication.entity.AdjIntTrialAccItem;
import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.adjudication.entity.AdjResultIntItem;
import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.adjudication.entity.CredType;
import hk.oro.iefas.domain.casemgt.entity.CaseCred;
import hk.oro.iefas.domain.dividend.dto.AdjIntTrialAccItemDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationAccountDTO;
import hk.oro.iefas.domain.dividend.dto.InterestTrialAdjudicationDTO;
import hk.oro.iefas.ws.adjudication.repository.AdjAccNumberRepository;
import hk.oro.iefas.ws.adjudication.repository.AdjApplyItemRepository;
import hk.oro.iefas.ws.adjudication.repository.AppAdjResultItemRepository;
import hk.oro.iefas.ws.adjudication.repository.predicate.AdjAccNumberPredicate;
import hk.oro.iefas.ws.adjudication.repository.predicate.AdjApplyItemPredicate;
import hk.oro.iefas.ws.casemgt.repository.assembler.CreditorDTOAssembler;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import hk.oro.iefas.ws.dividend.repository.AdjIntTrialAccItemRepository;
import hk.oro.iefas.ws.dividend.repository.AdjResultIntItemRepository;
import hk.oro.iefas.ws.dividend.repository.predicate.AdjIntTrialAccItemPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.AdjResultIntItemPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.AppAdjResultItemPredicate;

@Component
public class InterestTrialAdjudicationDTOAssembler
    extends PagingAssemblerSupport<InterestTrialAdjudicationDTO, AdjIntTrAdj> {

    @Autowired
    private CreditorDTOAssembler creditorDTOAssembler;

    @Autowired
    private AppAdjResultItemRepository appAdjResultItemRepository;

    @Autowired
    private AdjucationAccountDTOAssembler adjucationAccountDTOAssembler;

    @Autowired
    private CreditorTypeDTOAssembler creditorTypeDTOAssembler;

    @Autowired
    private AdjucationApplyItemDTOAssembler adjucationApplyItemDTOAssembler;

    @Autowired
    private AdjAccNumberRepository adjAccNumberRepository;

    @Autowired
    private AdjApplyItemRepository adjApplyItemRepository;

    @Autowired
    private AdjIntTrialAccItemDTOAssembler adjIntTrialAccItemDTOAssembler;

    @Autowired
    private AdjucationResultIntItemDTOAssembler adjucationResultIntItemDTOAssembler;

    @Autowired
    private AdjIntTrialAccItemRepository adjIntTrialAccItemRepository;

    @Autowired
    private AdjResultIntItemRepository adjResultIntItemRepository;

    @Override
    public InterestTrialAdjudicationDTO toDTO(AdjIntTrAdj entity) {
        InterestTrialAdjudicationDTO result = null;
        if (entity != null && entity.getAdjResult() != null) {
            AdjResult adjResult = entity.getAdjResult();
            result = DataUtils.copyProperties(entity, InterestTrialAdjudicationDTO.class);
            result = getInfoFromAppAdjResult(adjResult, result);

            List<AdjIntTrialAccItem> adjIntTrialAccItemList = (List<AdjIntTrialAccItem>)adjIntTrialAccItemRepository
                .findAll(AdjIntTrialAccItemPredicate.findByIntTrAdjId(entity.getIntTrAdjId()));
            if (CommonUtils.isNotEmpty(adjIntTrialAccItemList)) {
                result.setAdjIntTrialAccItemList(adjIntTrialAccItemDTOAssembler.toDTOList(adjIntTrialAccItemList));
            }
            List<AdjResultIntItem> adjResultIntItemList = (List<AdjResultIntItem>)adjResultIntItemRepository
                .findAll(AdjResultIntItemPredicate.findByadjIntTrialAdjResultId(entity.getIntTrAdjId()));
            if (CommonUtils.isNotEmpty(adjResultIntItemList)) {
                result.setAdjucationResultIntItemList(
                    adjucationResultIntItemDTOAssembler.toDTOList(adjResultIntItemList));
            }

        }
        return result;
    }

    private AdjIntTrialAccItemDTO getAdjIntTrialAccItemDTO(String account) {
        AdjIntTrialAccItemDTO adjIntTrialAccItem = new AdjIntTrialAccItemDTO();
        adjIntTrialAccItem.setAccountNumber(account);
        adjIntTrialAccItem.setAdmAmount(BigDecimal.ZERO);
        adjIntTrialAccItem.setContractualRate(BigDecimal.ZERO);
        adjIntTrialAccItem.setFinalRate(BigDecimal.ZERO);
        adjIntTrialAccItem.setStatus(CoreConstant.STATUS_ACTIVE);
        return adjIntTrialAccItem;
    }

    public InterestTrialAdjudicationDTO toDTO(AdjResult adjResult) {
        InterestTrialAdjudicationDTO result = new InterestTrialAdjudicationDTO();
        if (adjResult != null) {
            result = getInfoFromAppAdjResult(adjResult, result);

            List<AdjIntTrialAccItemDTO> adjIntTrialAccItemList = new ArrayList<AdjIntTrialAccItemDTO>();
            List<AdjucationAccountDTO> adjucationAccountList = result.getAdjucationAccountList();
            if (adjucationAccountList != null && !adjucationAccountList.isEmpty()) {
                adjucationAccountList.forEach(t -> {
                    AdjIntTrialAccItemDTO adjIntTrialAccItem = getAdjIntTrialAccItemDTO(t.getAdjucationAccount());
                    adjIntTrialAccItemList.add(adjIntTrialAccItem);
                });
            } else {
                AdjIntTrialAccItemDTO adjIntTrialAccItem = getAdjIntTrialAccItemDTO(DividendConstant.NO_ACCOUNT_NUMBER);
                adjIntTrialAccItemList.add(adjIntTrialAccItem);
            }
            result.setAdjIntTrialAccItemList(adjIntTrialAccItemList);
        }
        return result;
    }

    private InterestTrialAdjudicationDTO getInfoFromAppAdjResult(AdjResult adjResult,
        InterestTrialAdjudicationDTO result) {
        CaseCred caseCred = adjResult.getCaseCred();
        if (caseCred != null) {
            result.setCreditor(creditorDTOAssembler.toDTO(caseCred));
        }
        result.setNatureOfClaim(adjResult.getNatureOfClaim());
        result.setClaimAmount(adjResult.getTotalClaimedAmount());
        result.setRemark(adjResult.getRemark());
        result.setAddressType(adjResult.getLocal());
        CredType credType = adjResult.getCreditorType();
        if (credType != null) {
            result.setCreditorType(creditorTypeDTOAssembler.toDTO(credType));
        }

        Integer adjResultId = adjResult.getAdjResultId();
        result.setAdjResultId(adjResultId);
        Iterable<AdjAccNumber> adjAccNumberList
            = adjAccNumberRepository.findAll(AdjAccNumberPredicate.findByAdjResult(adjResultId));
        result.setAdjucationAccountList(adjucationAccountDTOAssembler.toDTOList(adjAccNumberList));

        Iterable<AdjApplyItem> adjApplyItemList
            = adjApplyItemRepository.findAll(AdjApplyItemPredicate.findByAdjResult(adjResultId));
        result.setAdjucationApplyItems(adjucationApplyItemDTOAssembler.toDTOList(adjApplyItemList));

        List<AppAdjResultItem> appAdjResultItemList = (List<AppAdjResultItem>)appAdjResultItemRepository
            .findAll(AppAdjResultItemPredicate.findByAdjResultId(adjResult.getAdjResultId()));
        if (appAdjResultItemList != null && !appAdjResultItemList.isEmpty()) {
            BigDecimal totalAdmittedAmount = BigDecimal.ZERO;
            for (AppAdjResultItem appAdjResultItem : appAdjResultItemList) {
                if (appAdjResultItem.getAdmAmount() != null) {
                    totalAdmittedAmount = totalAdmittedAmount.add(appAdjResultItem.getAdmAmount());
                }
            }
            result.setSumOfAppAdjRsltAmount(totalAdmittedAmount);
        }
        return result;
    }
}
