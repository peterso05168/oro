package hk.oro.iefas.ws.core.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.security.UserInfo;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.common.UpdateAuditable;
import hk.oro.iefas.domain.common.UpdateSubmitApproveInfoAble;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.ws.common.util.RequestContextUtils;

/**
 * @version $Revision: 3217 $ $Date: 2018-06-20 14:01:41 +0800 (週三, 20 六月 2018) $
 * @author $Author: scott.feng $
 */
@Component
@Aspect
public class AuditorPostProvider {

    @Pointcut("execution(* org.springframework.data.auditing.AuditingHandler.markCreated(java.lang.Object))")
    public void prePersistPointcut() {}

    @Before(value = "prePersistPointcut()")
    public void prePersist(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (CommonUtils.isNotEmpty(args)) {
            Object target = args[0];
            if (target != null) {
                UserInfo userInfo = RequestContextUtils.getCurrentUserInfo();
                if (userInfo != null) {
                    String userName = userInfo.getUserName();
                    String userPost = userInfo.getUserPost();
                    if (target instanceof UpdateAuditable) {
                        UpdateAuditable auditable = (UpdateAuditable)target;
                        auditable.setCreatedByUser(userName);
                        auditable.setCreatedByPost(userPost);
                        auditable.setLastUpdatedByUser(userName);
                        auditable.setLastUpdatedByPost(userPost);
                    }

                    if (target instanceof UpdateSubmitApproveInfoAble) {
                        setSubmitApproveInfo((UpdateSubmitApproveInfoAble)target, userInfo);
                    }
                }

            }
            Long txnKeyRef = RequestContextUtils.getTxnKeyRef();
            if (txnKeyRef != null && target != null && target instanceof UpdateTxnKeyable) {
                UpdateTxnKeyable updateTxnKeyable = (UpdateTxnKeyable)target;
                updateTxnKeyable.setTxnKeyRef(txnKeyRef);
            }
        }

    }

    @Pointcut("execution(* org.springframework.data.auditing.AuditingHandler.markModified(java.lang.Object))")
    public void preUpdatePointcut() {}

    @Before(value = "preUpdatePointcut()")
    public void preUpdate(JoinPoint joinPoint) throws IllegalArgumentException, IllegalAccessException {
        Object[] args = joinPoint.getArgs();
        if (CommonUtils.isNotEmpty(args)) {
            Object target = args[0];
            if (target != null) {
                UserInfo userInfo = RequestContextUtils.getCurrentUserInfo();
                if (userInfo != null) {
                    if (target instanceof UpdateAuditable) {
                        UpdateAuditable auditable = (UpdateAuditable)target;
                        auditable.setLastUpdatedByUser(userInfo.getUserName());
                        auditable.setLastUpdatedByPost(userInfo.getUserPost());
                    }

                    if (target instanceof UpdateSubmitApproveInfoAble) {
                        setSubmitApproveInfo((UpdateSubmitApproveInfoAble)target, userInfo);
                    }
                }

                Long txnKeyRef = RequestContextUtils.getTxnKeyRef();
                if (txnKeyRef != null && target instanceof UpdateTxnKeyable) {
                    UpdateTxnKeyable updateTxnKeyable = (UpdateTxnKeyable)target;
                    updateTxnKeyable.setTxnKeyRef(txnKeyRef);
                }
            }
        }
    }

    private void setSubmitApproveInfo(UpdateSubmitApproveInfoAble updateAble, UserInfo userInfo) {
        String userName = userInfo.getUserName();
        Date currentDate = DateUtils.getCurrentDate();
        switch (updateAble.getStatus()) {
            case CoreConstant.COMMON_STATUS_SUBMITTED:
                updateAble.setSubmittedBy(userName);
                updateAble.setSubmittedDate(currentDate);
                break;
            case CoreConstant.COMMON_STATUS_APPROVED:
                updateAble.setApprovedBy(userName);
                updateAble.setApprovedDate(currentDate);
                break;
            case CoreConstant.COMMON_STATUS_REJECTED:
                updateAble.setSubmittedBy(null);
                updateAble.setSubmittedDate(null);
                updateAble.setApprovedBy(null);
                updateAble.setApprovedDate(null);
                break;
            default:
                break;
        }
    }
}
