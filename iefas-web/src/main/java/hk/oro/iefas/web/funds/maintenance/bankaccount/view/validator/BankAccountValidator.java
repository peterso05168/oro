package hk.oro.iefas.web.funds.maintenance.bankaccount.view.validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.domain.bank.vo.BankInfoVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.funds.maintenance.bankaccount.service.BankClientService;
import hk.oro.iefas.web.funds.maintenance.bankaccount.view.BankAccountDetailView;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@RequestScoped
public class BankAccountValidator {
    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private BankAccountDetailView bankAccountDetailView;

    @Inject
    private BankClientService bankClientService;

    private final String FORM_NAME = "editForm:";

    @PostConstruct
    private void init() {
        log.info("======BankAccountValidator init======");
    }

    public boolean validateSaveBankAccountInfo() {
        return validateBankCode() && validateBankName() && validateBankShortName() && validateFreeBankTransfers()
            && validateBankDepositLimit();
    }

    private boolean validateBankCode() {
        BankInfoVO bankInfoVO = bankAccountDetailView.getBankInfoVO();
        Boolean isExists = bankClientService.existsByBankCode(bankInfoVO.getBankCode(),
            bankInfoVO.getBankInfoId() != null ? bankInfoVO.getBankInfoId() : 0);
        if (isExists != null && isExists) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_DUPLICATE_VALUE, MsgParamCodeConstant.BANK_CODE), ""));
            ((UIInput)Faces.getViewRoot().findComponent(FORM_NAME + MsgParamCodeConstant.BANK_CODE)).setValid(false);
            return false;
        }
        return true;
    }

    private boolean validateBankName() {
        BankInfoVO bankInfoVO = bankAccountDetailView.getBankInfoVO();
        Boolean isExists = bankClientService.existsByBankName(bankInfoVO.getBankName(),
            bankInfoVO.getBankInfoId() != null ? bankInfoVO.getBankInfoId() : 0);
        if (isExists != null && isExists) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_DUPLICATE_VALUE, MsgParamCodeConstant.BANK_NAME), ""));
            ((UIInput)Faces.getViewRoot().findComponent(FORM_NAME + MsgParamCodeConstant.BANK_NAME)).setValid(false);
            return false;
        }
        return true;
    }

    private boolean validateBankShortName() {
        BankInfoVO bankInfoVO = bankAccountDetailView.getBankInfoVO();
        Boolean isExists = bankClientService.existsByBankShortName(bankInfoVO.getBankShortName(),
            bankInfoVO.getBankInfoId() != null ? bankInfoVO.getBankInfoId() : 0);
        if (isExists != null && isExists) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_DUPLICATE_VALUE, MsgParamCodeConstant.BANK_SHORT_NAME),
                ""));
            ((UIInput)Faces.getViewRoot().findComponent(FORM_NAME + MsgParamCodeConstant.BANK_SHORT_NAME))
                .setValid(false);
            return false;
        }
        return true;
    }

    private boolean validateFreeBankTransfers() {
        if (bankAccountDetailView.getFilteredFreeBankTransfers() == null
            || bankAccountDetailView.getFilteredFreeBankTransfers().size() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.BANK_TRANSFER), ""));
            return false;
        }
        return true;
    }

    private boolean validateBankDepositLimit() {
        if (bankAccountDetailView.getBankInfoId() != null && bankAccountDetailView.getBankInfoId().intValue() > 0
            && bankAccountDetailView.getBankInfoVO().getBankDepositlimit()
                .compareTo(bankAccountDetailView.getBankInfoVO().getAvaiableRoom()) < 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_INVALID_BANK_DEPOSIT_LIMIT), ""));
            ((UIInput)Faces.getViewRoot().findComponent(FORM_NAME + "DepositLimit")).setValid(false);
            return false;
        }
        return true;
    }
}
