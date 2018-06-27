/**
 * 
 */
package hk.oro.iefas.web.ledger.paymenthandling.createbanktransferfile.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import hk.oro.iefas.core.constant.PrivilegeConstant;
import hk.oro.iefas.domain.common.vo.ActionVO;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.SysWorkFlowRuleClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3261 $ $Date: 2018-06-22 17:53:57 +0800 (週五, 22 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class CreateBankTransferFileEditView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private SysWorkFlowRuleClientService sysWorkFlowRuleClientService;

    @Getter
    @Setter
    private ActionVO action;

    @PostConstruct
    private void init() {
        log.info("=============== CreateBankTransferFileEditView init start =================");
        initActionButton();
        log.info("=============== CreateBankTransferFileEditView init end ===================");
    }

    private void initActionButton() {
        action = sysWorkFlowRuleClientService.findIntialAction(PrivilegeConstant.PRIVILEGE_CBTF);
    }

    private void refreshActionButton(String codeValue) {
        // TODO Auto-generated method stub

    }

}
