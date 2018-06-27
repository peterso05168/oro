package hk.oro.iefas.web.dividend.dividendprocess.dividendcheque.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.PrivilegeConstant;
import hk.oro.iefas.core.constant.WorkFlowAction;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.dividend.vo.DividendChequeVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleItemVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.WorkFlowBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.dividendprocess.dividendcheque.service.impl.DividendChequeClientService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (Sun, 10 Jun 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class DividendChequeEditView extends WorkFlowBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Autowired
    private DividendChequeClientService dividendChequeClientService;

    @Getter
    private DividendChequeVO dividendChequeVO;

    @Getter
    private DividendScheduleItemVO dividendScheduleItem;

    @Getter
    private CaseVO caseInfo;

    @Getter
    private String status;

    @PostConstruct
    private void init() {
        log.info("DividendChequeEditView init");
        Integer dividendChequeId = Faces.getRequestParameter(DividendWebConstant.DIVIDEND_CHEQUE_ID, Integer.class);
        if (dividendChequeId != null && dividendChequeId.intValue() > 0) {
            dividendChequeVO = dividendChequeClientService.searchDividendCheque(dividendChequeId);
            if (dividendChequeVO != null) {
                this.dividendScheduleItem = dividendChequeVO.getDividendScheduleItem();
                if (dividendScheduleItem != null && this.dividendScheduleItem.getCreditor() != null) {
                    this.caseInfo = this.dividendScheduleItem.getCreditor().getCaseInfo();
                }
            }
            initData();
        }
    }

    public void reissues() {
        WorkFlowAction workFlowAction = WorkFlowAction.Reissues;
        prepareData(workFlowAction.getCode());
        Integer dividendChequeId = dividendChequeClientService.saveDividendCheque(dividendChequeVO);
        if (dividendChequeId != null && dividendChequeId.intValue() > 0) {
            this.dividendChequeVO = dividendChequeClientService.searchDividendCheque(dividendChequeId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_RE_ISSUES_SUCCESS));
            initData();
            hideComponent("reissuesDialog");
        } else {
            Messages.addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
        }
    }

    private void initData() {
        refreshActionButton(dividendChequeVO.getStatus());
        getStatusDesc(dividendChequeVO.getStatus());
    }

    public String cancel() {
        WorkFlowAction workFlowAction = WorkFlowAction.Delete;
        prepareData(workFlowAction.getCode());
        Integer dividendChequeId = dividendChequeClientService.saveDividendCheque(dividendChequeVO);
        if (dividendChequeId != null && dividendChequeId.intValue() > 0) {
            Faces.setFlashAttribute(DividendWebConstant.DELETE_MSG_CODE, MsgCodeConstant.MSG_DELETE_SUCCESS);
            return SEARCH_PAGE;
        } else {
            Messages.addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
        }
        return null;
    }

    private void prepareData(String workFlowActionCode) {
        SysWorkFlowRuleVO sysWorkFlowRuleVO
            = getAfterStatusByAction(this.dividendChequeVO.getStatus(), workFlowActionCode);
        dividendChequeVO.setStatus(sysWorkFlowRuleVO.getAfterStatus().getCodeValue());
    }

    @Override
    protected String getStatusGroupCode() {
        return ApplicationCodeTableEnum.DCS.name();
    }

    @Override
    protected String getSubmitPrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_DICR;
    }

    @Override
    protected String getApprovePrivilegeCode() {
        return "-1";
    }
}
