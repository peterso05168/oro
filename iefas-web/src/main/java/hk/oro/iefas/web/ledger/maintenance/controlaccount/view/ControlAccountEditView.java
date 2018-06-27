package hk.oro.iefas.web.ledger.maintenance.controlaccount.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.shroff.vo.ControlAccountTypeVO;
import hk.oro.iefas.domain.shroff.vo.ControlAccountVO;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.ledger.maintenance.controlaccount.service.ControlAccountClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3257 $ $Date: 2018-06-22 10:09:33 +0800 (週五, 22 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class ControlAccountEditView extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private ControlAccountClientService controlAccountClientService;
    @Inject
    private CommonClientService commonClientService;
    @Inject
    private CurrencyClientService currencyClientService;
    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private Integer ctlAcId;

    @Getter
    @Setter
    private ControlAccountVO controlAccount;

    @Getter
    @Setter
    private List<StatusVO> statusVOS;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyVOS;

    @Getter
    @Setter
    private List<ControlAccountTypeVO> controlAccountTypeVOS;

    @Getter
    @Setter
    private List<StatusVO> balanceNatures;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @PostConstruct
    public void init() {
        log.info("=============== CtlAcEditView init start ====================");
        statusVOS = commonConstantClientService.searchStatusList();
        currencyVOS = currencyClientService.findAll();
        controlAccountTypeVOS = commonClientService.searchCtlAcTypeList();
        balanceNatures = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.BLN.name());
        ctlAcId = Faces.getRequestParameter("ctlAcId", Integer.class);
        if (ctlAcId != null) {
            controlAccount = controlAccountClientService.getControlAccountDetail(ctlAcId);
        } else {
            controlAccount = new ControlAccountVO();
            // tmp
            controlAccount.setShrCtlAcType(new ControlAccountTypeVO());
            controlAccount.setOnHoldAmountCr(BigDecimal.ZERO);
            controlAccount.setOnHoldAmountDr(BigDecimal.ZERO);
            controlAccount.setInvestmentAmount(BigDecimal.ZERO);
            controlAccount.setLiquidCashAmount(BigDecimal.ZERO);
            controlAccount.setBalance(BigDecimal.ZERO);
            // tmp
        }
        log.info("=============== CtlAcEditView init end =======================");
    }

    public void save() {
        log.info("save() start");
        Integer ctlAcId = controlAccountClientService.saveControlAccount(controlAccount);
        if (ctlAcId != null) {
            controlAccount = controlAccountClientService.getControlAccountDetail(ctlAcId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        } else {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
        }
        log.info("save() end");
    }
}
