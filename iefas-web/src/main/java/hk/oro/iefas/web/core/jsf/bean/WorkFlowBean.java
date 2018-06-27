package hk.oro.iefas.web.core.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.WorkFlowAction;
import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.core.security.CustomUserDetails;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.dividend.vo.SysApprovalWfVO;
import hk.oro.iefas.domain.system.vo.SysWfInitialStatusVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.SysWfInitialStatusClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.SysWorkFlowRuleClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2786 $ $Date: 2018-05-31 17:43:02 +0800 (Thu, 31 May 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
public abstract class WorkFlowBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    protected AppResourceUtils appResourceUtils;

    @Inject
    protected SysWorkFlowRuleClientService sysWorkFlowRuleClientService;

    @Inject
    protected SysWfInitialStatusClientService sysWfInitialStatusClientService;

    private CustomUserDetails user;

    @Getter
    @Setter
    protected boolean saveable;

    @Getter
    @Setter
    protected boolean submitable;

    @Getter
    @Setter
    protected boolean approvable;

    @Getter
    @Setter
    protected boolean rejectable;
    
    @Getter
    @Setter
    protected boolean confirmable;

    @Getter
    @Setter
    protected boolean deletable;

    @Getter
    @Setter
    protected boolean printable;

    @Getter
    @Setter
    protected boolean reissuesable;

    @Getter
    protected boolean formDisabled = false;

    @Getter
    protected String status;

    @Getter
    @Setter
    protected List<ApplicationCodeTableVO> statusVOs;

    protected SysWfInitialStatusVO sysWfInitialStatusVO;

    protected List<SysWorkFlowRuleVO> sysWorkFlowRuleVOs;

    @PostConstruct
    private void init() {
        log.info("======WorkFlowBean init======");
        statusVOs = appResourceUtils.getApplicationCodeByGroup(getStatusGroupCode());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            user = (CustomUserDetails)authentication.getPrincipal();
        }
        sysWorkFlowRuleVOs = sysWorkFlowRuleClientService.findByPrivilegeCode(getSubmitPrivilegeCode());

        // TODO by role of user
        if (user.isSystemAdminInd()) {
            if (sysWorkFlowRuleVOs == null) {
                sysWorkFlowRuleVOs = new ArrayList<SysWorkFlowRuleVO>();
            }
            sysWorkFlowRuleVOs.addAll(sysWorkFlowRuleClientService.findByPrivilegeCode(getApprovePrivilegeCode()));
        }
    }

    protected void getStatusDesc(String statusCode) {
        List<ApplicationCodeTableVO> list
            = statusVOs.stream().filter(item -> item.getCodeValue().equals(statusCode)).collect(Collectors.toList());

        if (CommonUtils.isNotEmpty(list)) {
            status = list.get(0).getCodeDesc();
        } else {
            // TODO
            log.info("miss code of " + statusCode);
            throw new BusinessException("miss code of " + statusCode);
        }
    }

    protected abstract String getStatusGroupCode();

    protected abstract String getSubmitPrivilegeCode();

    protected abstract String getApprovePrivilegeCode();

    protected SysWorkFlowRuleVO getAfterStatusByAction(String beforeStatus, String action) {
        log.info("getAfterStatusByAction() start");
        List<SysWorkFlowRuleVO> after
            = sysWorkFlowRuleVOs.parallelStream().filter(item -> item.getAction().getCodeValue().equals(action)
                && item.getBeforeStatus().getCodeValue().equals(beforeStatus)).collect(Collectors.toList());
       
        if (CommonUtils.isNotEmpty(after)) {
            formDisabled = getFormDisabled(after.get(0).getAfterStatus().getCodeValue());
        }else {
            throw new BusinessException("..............");
        }
        log.info("getAfterStatusByAction() start");
        return after.get(0);
    }

    private boolean getFormDisabled(String status) {
        return !CoreConstant.COMMON_STATUS_DRAFT.equals(status) && !CoreConstant.COMMON_STATUS_REJECTED.equals(status);
    }

    protected String initStatus() {
        sysWfInitialStatusVO = sysWfInitialStatusClientService.findByPrivilegeCode(getSubmitPrivilegeCode());
        return sysWfInitialStatusVO.getStatus().getCodeValue();
    }

    protected void refreshActionButton(String status) {
        log.info("refreshActionButton() start");

        formDisabled = getFormDisabled(status);
        saveable = false;
        submitable = false;
        approvable = false;
        rejectable = false;
        deletable = false;
        printable = false;
        reissuesable = false;
        confirmable = false;

        List<ApplicationCodeTableVO> applicationCodeTables
            = statusVOs.stream().filter(item -> item.getCodeValue().equals(status)).collect(Collectors.toList());
        if (CommonUtils.isNotEmpty(applicationCodeTables)) {
            Integer statusId = applicationCodeTables.get(0).getApplicationCodeTableId();

            List<SysWorkFlowRuleVO> currentRules = sysWorkFlowRuleVOs.parallelStream()
                .filter(rule -> rule.getBeforeStatus().getApplicationCodeTableId().equals(statusId))
                .collect(Collectors.toList());

            currentRules.parallelStream().forEach(rule -> {
                WorkFlowAction workFlowAction = WorkFlowAction.getByCode(rule.getAction().getCodeValue());
                if (workFlowAction != null) {
                    switch (WorkFlowAction.getByCode(rule.getAction().getCodeValue())) {
                        case Save:
                            saveable = true;
                            break;
                        case Submit:
                            submitable = true;
                            break;
                        case Approve:
                            approvable = true;
                            break;
                        case Reject:
                            rejectable = true;
                            break;
                        case Delete:
                            deletable = true;
                            break;
                        case Print:
                            printable = true;
                            break;
                        case Reissues:
                            reissuesable = true;
                        case Confirm:
                            confirmable = true;    
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        log.info("refreshActionButton() end");
    }

    /**
     * submit==>save, submit, delete approve==>approve, reject
     * 
     * @param workFlowAction
     * @return
     */
    protected String getWorkFlowPrivilegeCode(WorkFlowAction workFlowAction) {
        switch (workFlowAction) {
            case Save:
                return getSubmitPrivilegeCode();
            case Submit:
                return getSubmitPrivilegeCode();
            case Approve:
                return getApprovePrivilegeCode();
            case Reject:
                return getApprovePrivilegeCode();
            case Delete:
                return getSubmitPrivilegeCode();
            default:
                return getSubmitPrivilegeCode();
        }
    }

    protected String getSuccessMsg(WorkFlowAction workFlowAction) {
        switch (workFlowAction) {
            case Save:
                return MsgCodeConstant.MSG_SAVE_SUCCESS;
            case Submit:
                return MsgCodeConstant.MSG_SUBMIT_SUCCESS;
            case Approve:
                return MsgCodeConstant.MSG_APPROV_SUCCESS;
            case Reject:
                return MsgCodeConstant.MSG_REJECT_SUCCESS;
            case Delete:
                return MsgCodeConstant.MSG_DELETE_SUCCESS;
            default:
                return MsgCodeConstant.MSG_SAVE_SUCCESS;
        }
    }

    protected SysApprovalWfVO getSysApprovalWf(WorkFlowAction action, String privilegeCode) {
        SysApprovalWfVO sysApprovalWf = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            sysApprovalWf = new SysApprovalWfVO();
            sysApprovalWf.setAction(action.name());
            sysApprovalWf.setPrivilegeCode(privilegeCode);
            sysApprovalWf.setActionByPerson(user.getUserFullName());
            sysApprovalWf.setActionByPostId(user.getPostId());
            sysApprovalWf.setDivisionId(user.getDivisionId());
        }
        return sysApprovalWf;
    }
}
