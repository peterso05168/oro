package hk.oro.iefas.web.ledger.deposithandling.enquiry.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.bank.vo.CurrencyVO;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.shroff.vo.ChequeVO;
import hk.oro.iefas.domain.shroff.vo.PaymentTypeVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVO;
import hk.oro.iefas.domain.shroff.vo.ReceiveDepositVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.ledger.chequehandling.incomingcheque.service.ChequeClientService;
import hk.oro.iefas.web.ledger.deposithandling.enquiry.service.ReceiveDepositClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3104 $ $Date: 2018-06-13 11:14:36 +0800 (週三, 13 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class ReceiveDepositEnquiryEditView extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer depositId;

    @Setter
    @Getter
    private ReceiveDepositVO receiveDeposit;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfoVOS;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private CommonClientService commonClientService;

    @Inject
    private ReceiveDepositClientService receiveDepositClientService;

    @Inject
    private CaseClientService caseClientService;

    @Getter
    @Setter
    private CaseNumberVO caseNumber;

    @Getter
    @Setter
    private List<StatusVO> statusVOS;

    @Getter
    @Setter
    private CaseVO caseVO;

    @Getter
    @Setter
    private List<PaymentTypeVO> paymentTypeVOS;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private ChequeClientService chequeClientService;

    private ChequeVO chequeVO;

    @PostConstruct
    public void init() {
        log.info("=========== enquiryEditView init start ============");
        caseNumber = new CaseNumberVO();
        depositId = Faces.getRequestParameter("depositId", Integer.class);
        if (depositId != null) {
            receiveDeposit = receiveDepositClientService.getReceiveDepositDetail(depositId);
            caseVO = receiveDeposit.getCaseInfo();
            if (receiveDeposit.getCaseInfo() != null) {
                caseNumber.setCaseSequence(receiveDeposit.getCaseInfo().getCaseNo());
                caseNumber.setCaseYear(receiveDeposit.getCaseInfo().getCaseYear());
                if (receiveDeposit.getCaseInfo().getCaseType() != null) {
                    caseNumber.setCaseType(receiveDeposit.getCaseInfo().getCaseType().getCaseTypeCode());
                }
            }
        } else {
            initReceiveDeposit();
        }
        if (receiveDeposit.getShrReceipt() == null) {
            receiveDeposit.setShrReceipt(new ReceiptVO());
        }
        currencyBasicInfoVOS = currencyClientService.findAll();
        paymentTypeVOS = commonClientService.searchPaymentTypeList();
        statusVOS = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.DPS.name());
        log.info("=========== enquiryEditView init end =============");
    }

    public void save() {
        log.info("save start");
        if (receiveDeposit.getStatus() == null) {
            receiveDeposit.setStatus(CoreConstant.DEPOSIT_STATUS_PENDING);
        }
        chequeVO = chequeClientService.getChequeDetailByChequeNo(receiveDeposit.getChequeNo().toString());
        if (chequeVO != null && chequeVO.getShrVcrInfo() != null)
            receiveDeposit.setVoucherId(new BigDecimal(chequeVO.getShrVcrInfo().getVoucherId()));
        if (caseVO == null
            && (CommonUtils.isNotBlank(caseNumber.getCaseType()) || CommonUtils.isNotBlank(caseNumber.getCaseSequence())
                || CommonUtils.isNotBlank(caseNumber.getCaseYear()))) {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.CASE_NUMBER));
            return;
        }
        if (receiveDeposit.getDepositNo() == null) {
            receiveDeposit.setDepositNo(receiveDepositClientService.generateDepositNumber());
        }
        Integer depositId = receiveDepositClientService.saveReceiveDeposit(receiveDeposit);
        if (depositId == null) {
            receiveDeposit.setDepositNo(null);
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
        } else {
            receiveDeposit = receiveDepositClientService.getReceiveDepositDetail(depositId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        }
        log.info("save end");
    }

    public void reverse() {
        log.info("reverse start");
        receiveDeposit.setStatus(CoreConstant.DEPOSIT_STATUS_REVERSED);
        save();
        log.info("reverse end");
    }

    public void searchCaseInfo() {
        log.info("searchCaseInfo start");
        if (CommonUtils.isNotBlank(caseNumber.getCaseType()) && CommonUtils.isNotBlank(caseNumber.getCaseSequence())
            && CommonUtils.isNotBlank(caseNumber.getCaseYear()))
            caseVO = caseClientService.findByCaseNumber(caseNumber);
        receiveDeposit.setCaseInfo(caseVO);
        log.info("searchCaseInfo end");
    }

    public void createNew() {
        log.info("createNew start");
        initReceiveDeposit();
        caseNumber = new CaseNumberVO();
        caseVO = null;
        chequeVO = null;
        log.info("createNew end");
    }

    private void initReceiveDeposit() {
        this.receiveDeposit = new ReceiveDepositVO();
        receiveDeposit.setReceiveDate(appResourceUtils.getBusinessDate());
        receiveDeposit.setCurcy(new CurrencyVO());
        receiveDeposit.setShrReceipt(new ReceiptVO());
    }

    public void printReceipt() {
        log.info("printReceipt start");
        chequeVO = chequeClientService.getChequeDetailByChequeNo(receiveDeposit.getChequeNo().toString());
        if (chequeVO != null && chequeVO.getShrVcrInfo() != null)
            receiveDeposit.setVoucherId(new BigDecimal(chequeVO.getShrVcrInfo().getVoucherId()));
        if (caseVO == null && CommonUtils.isNotBlank(caseNumber.getCaseType())) {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.CASE_NUMBER));
            return;
        }
        Integer depositId;
        depositId = receiveDepositClientService.printReceipt(receiveDeposit);
        if (depositId == null) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_PRINT_RECEIPT_FAIL));
        } else {
            receiveDeposit = receiveDepositClientService.getReceiveDepositDetail(depositId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_PRINT_RECEIPT_SUCCESS));
        }

        log.info("printReceipt end");
    }

}
