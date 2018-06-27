package hk.oro.iefas.web.casemgt.casedetailenquiry.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.BusinessUtils;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.ValidationUtils;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.bank.vo.CurrencyVO;
import hk.oro.iefas.domain.casemgt.vo.CaseAccountInfoVO;
import hk.oro.iefas.domain.casemgt.vo.CaseAccountTypeVO;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.casemgt.vo.DepositCardVO;
import hk.oro.iefas.domain.casemgt.vo.OutsideTrusteeVO;
import hk.oro.iefas.domain.organization.vo.RankVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseAccountClientService;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.DepositCardClientService;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.system.divisionadministration.post.service.RankClientService;
import hk.oro.iefas.web.system.profile.service.UserProfileClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3125 $ $Date: 2018-06-13 17:50:03 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Named
@ViewScoped
public class CaseDetailEnquiryEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private CommonClientService commonClientService;

    @Inject
    private CaseClientService caseClientService;

    @Inject
    private UserProfileClientService userProfileClientService;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private DepositCardClientService depositCardClientService;

    @Inject
    private CaseAccountClientService caseAccountClientService;

    @Inject
    private RankClientService rankClientService;

    @Getter
    @Setter
    private CaseVO caseVO;

    @Getter
    @Setter
    private String handlingOfficerName;

    @Getter
    @Setter
    private Integer caseId;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyVOs;

    @Getter
    @Setter
    private List<CaseAccountTypeVO> caseAcTypeVOs;

    // data part
    @Getter
    @Setter
    private List<CaseAccountInfoVO> accountInfoVOs;

    @Getter
    @Setter
    private CaseAccountInfoVO caseAccountVO = new CaseAccountInfoVO();

    @Getter
    @Setter
    private Integer caseAccountId;

    @Getter
    @Setter
    private List<DepositCardVO> depositCardVOs;

    @Getter
    @Setter
    private DepositCardVO depositCardVO = new DepositCardVO();

    @Getter
    @Setter
    private Integer depositCardId;

    @Getter
    @Setter
    private List<OutsideTrusteeVO> outsideTrustee = new ArrayList<>();

    @PostConstruct
    private void init() {
        log.info("======CaseDetailEnquiryEditView init======");
        caseId = Faces.getRequestParameter("caseId", Integer.class);
        if (caseId != null && caseId != 0) {
            caseVO = caseClientService.findOne(caseId);
            handlingOfficerName = userProfileClientService.getUserNameByPostId(caseVO.getPost().getPostId());
            caseAcTypeVOs = commonClientService.searchCaseAccountTypeList();
            currencyVOs = currencyClientService.findAll();
            accountInfoVOs = caseAccountClientService.findCaseAccountByCaseId(caseVO.getCaseId());
            depositCardVOs = depositCardClientService.findDepositCardByCase(caseVO.getCaseId());
            // test code
            outsideTrustee
                .add(new OutsideTrusteeVO(caseVO.getCaseFee() != null ? caseVO.getCaseFee().getCaseFeeId() : null,
                    "OR's Fees", caseVO.getCaseFee() != null ? caseVO.getCaseFee().getAmountPaid().toString() : null));
            outsideTrustee.add(
                new OutsideTrusteeVO(caseVO.getOrFeeVoucher() != null ? caseVO.getOrFeeVoucher().getVoucherId() : null,
                    "OR's Fees Voucher Number",
                    caseVO.getOrFeeVoucher() != null ? caseVO.getOrFeeVoucher().getVoucherNo() : null));
            outsideTrustee.add(
                new OutsideTrusteeVO(caseVO.getRemitVoucher() != null ? caseVO.getRemitVoucher().getVoucherId() : null,
                    "Remit Balance Voucher Number",
                    caseVO.getCaseFee() != null ? caseVO.getRemitVoucher().getVoucherNo() : null));

            caseAccountVO.setCurrency(new CurrencyVO());
            caseAccountVO.setCaseAccountType(new CaseAccountTypeVO());
        }
    }

    public void addCaseAccount() {
        log.info("addCaseAccount() start");
        caseAcTypeVOs.stream().forEach(item -> {
            if (item.getCaseAcTypeId().equals(caseAccountVO.getCaseAccountType().getCaseAcTypeId())) {
                caseAccountVO.setCaseAccountType(item);
            }
        });
        caseAccountVO.setCaseAcNumber(BusinessUtils.genAccountNumber(caseVO.getCaseType().getCaseTypeCode(),
            caseAccountVO.getCaseAccountType().getCaseAcTypeCode(), caseVO.getCaseNo(), caseVO.getCaseYear()));
        caseAccountVO.setInvestmentAmount(BigDecimal.ZERO);
        caseAccountVO.setLiquidCashAmount(BigDecimal.ZERO);
        caseAccountVO.setOnHoldAmountCr(BigDecimal.ZERO);
        caseAccountVO.setOnHoldAmountDr(BigDecimal.ZERO);
        caseAccountVO.setCaseInfo(caseVO);
        caseAccountVO.setLastTransactionDate(appResourceUtils.getBusinessDate());
        caseAccountVO.setStatus(CoreConstant.STATUS_ACTIVE);
        caseAccountClientService.save(caseAccountVO);
        // test code
        accountInfoVOs = caseAccountClientService.findCaseAccountByCaseId(caseVO.getCaseId());
        hideComponent("caseAccountDialog");
        log.info("addCaseAccount() end");
    }

    public void accountHandleClose() {
        log.info("accountHandleClose() start");
        this.caseAccountVO = new CaseAccountInfoVO();
        caseAccountVO.setCurrency(new CurrencyVO());
        caseAccountVO.setCaseAccountType(new CaseAccountTypeVO());
        log.info("accountHandleClose() end");
    }

    public void addDepositCard() {
        log.info("addDepositCard() start");
        if (validateDepositCard()) {
            String[] post = BusinessUtils.splitPostTitle(caseVO.getPost().getPostTitle());
            if (CommonUtils.isNotEmpty(post)) {
                RankVO rank = rankClientService.findByRankName(post[0]);
                if (rank != null) {
                    String narrative = BusinessUtils.genNarrative(caseVO.getCaseNo(), caseVO.getCaseYear(),
                        caseVO.getPost().getPostTitle(), rank.getNarrativeCode());
                    depositCardVO.setCaseInfo(caseVO);
                    depositCardVO.setStatus(CoreConstant.STATUS_ACTIVE);
                    depositCardVO.setDepositCardNarrative(narrative);
                    depositCardClientService.save(depositCardVO);
                    depositCardVOs = depositCardClientService.findDepositCardByCase(caseVO.getCaseId());
                    hideComponent("depositCardDialog");
                } else {
                    Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_NARRATIVE_NOT_GEN));
                }
            } else {
                Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_NARRATIVE_NOT_GEN));
            }
        }
        log.info("addDepositCard() end");
    }

    public boolean validateDepositCard() {
        log.info("validateDepositCard() start");
        boolean validateDatePeriod = ValidationUtils.validateDatePeriod(this.depositCardVO.getEffectiveStartDate(),
            this.depositCardVO.getEffectiveEndDate());
        boolean validateStartDate = ValidationUtils.validateDatePeriod(appResourceUtils.getBusinessDate(),
            this.depositCardVO.getEffectiveStartDate());
        boolean validateEndDate = ValidationUtils.validateDatePeriod(appResourceUtils.getBusinessDate(),
            this.depositCardVO.getEffectiveEndDate());

        if (!validateDatePeriod) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_MUST_BEFORE,
                MsgParamCodeConstant.EFFECTIVE_START_DATE, MsgParamCodeConstant.EFFECTIVE_END_DATE));
        }
        if (!validateStartDate) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_MUST_BEFORE,
                MsgParamCodeConstant.BUSINESS_DATE, MsgParamCodeConstant.EFFECTIVE_START_DATE));
        }
        if (!validateEndDate) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_MUST_BEFORE,
                MsgParamCodeConstant.BUSINESS_DATE, MsgParamCodeConstant.EFFECTIVE_END_DATE));
        }

        log.info("validateDepositCard() end");
        return validateDatePeriod && validateStartDate && validateEndDate;
    }

    public void depositCardHandleClose() {
        log.info("depositCardHandleClose() start");
        this.depositCardVO = new DepositCardVO();
        log.info("depositCardHandleClose() end");
    }

    public void changeAccountStatus() {
        log.info("changeAccountStatus() starts ");
        CaseAccountInfoVO caseAccountVO = caseAccountClientService.findOne(caseAccountId);
        if (CoreConstant.STATUS_ACTIVE.equals(caseAccountVO.getStatus())) {
            caseAccountVO.setStatus(CoreConstant.STATUS_INACTIVE);
            caseAccountClientService.save(caseAccountVO);
        } else {
            caseAccountVO.setStatus(CoreConstant.STATUS_ACTIVE);
            caseAccountClientService.save(caseAccountVO);
        }
        accountInfoVOs = caseAccountClientService.findCaseAccountByCaseId(caseVO.getCaseId());
        resetConfirm();
        log.info("changeAccountStatus() end ");
    }

    public void changeDepositCardStatus() {
        log.info("changeDepositCardStatus() starts ");
        DepositCardVO depositCardVO = depositCardClientService.findOne(depositCardId);
        if (CoreConstant.STATUS_ACTIVE.equals(depositCardVO.getStatus())) {
            depositCardVO.setStatus(CoreConstant.STATUS_SUSPEND);
            depositCardClientService.save(depositCardVO);
        } else {
            depositCardVO.setStatus(CoreConstant.STATUS_ACTIVE);
            depositCardClientService.save(depositCardVO);
        }
        depositCardVOs = depositCardClientService.findDepositCardByCase(caseVO.getCaseId());
        resetConfirm();
        log.info("changeDepositCardStatus() end ");
    }

    public void resetConfirm() {
        log.info("resetConfirm() start ");
        caseAccountId = null;
        depositCardId = null;
        hideComponent("cd");
        log.info("resetConfirm() end ");
    }
}
