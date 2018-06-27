package hk.oro.iefas.ws.core.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.security.UserInfo;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.log.entity.ActivityLog;
import hk.oro.iefas.domain.log.entity.TransactionLog;
import hk.oro.iefas.ws.common.util.RequestContextUtils;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.log.repository.ActivityLogRepository;
import hk.oro.iefas.ws.log.repository.TransactionLogRepository;
import hk.oro.iefas.ws.system.service.SysSequenceService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3217 $ $Date: 2018-06-20 14:01:41 +0800 (週三, 20 六月 2018) $
 * @author $Author: scott.feng $
 */
@Slf4j
@Component
@Aspect
public class LoggingHandler {
    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Autowired
    private SysSequenceService sysSequenceService;

    @Pointcut("@annotation(hk.oro.iefas.ws.core.annotation.ModuleLog)")
    public void logMethod() {}

    @Around(value = "logMethod()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        sysSequenceService.generateTxnKeyRefNo();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Object[] args = pjp.getArgs();

        ActivityLog activityLog = null;
        TransactionLog transactionLog = null;
        if (targetMethod.isAnnotationPresent(ModuleLog.class)) {
            ModuleLog moduleLog = targetMethod.getAnnotation(ModuleLog.class);
            activityLog = createActivityLog(args, moduleLog);
            if (moduleLog.needTransaction()) {
                transactionLog = createTransactionLog(args, moduleLog);
            }
        }

        Object returnVal = null;
        try {
            returnVal = pjp.proceed();
        } catch (Throwable t) {
            log.error(t.getMessage());
            if (activityLog != null) {
                activityLog.setActivityResult(ModuleLogConstant.RESULT_FAIL);
            }
            if (transactionLog != null) {
                transactionLog.setTransactionResult(ModuleLogConstant.RESULT_FAIL);
            }
            throw t;
        } finally {
            if (activityLog != null) {
                activityLogRepository.save(activityLog);
            }
            if (transactionLog != null) {
                transactionLogRepository.save(transactionLog);
            }
        }

        return returnVal;
    }

    private ActivityLog createActivityLog(Object[] args, ModuleLog moduleLog) {
        ActivityLog activityLog = new ActivityLog();
        UserInfo userInfo = RequestContextUtils.getCurrentUserInfo();
        if (userInfo != null) {
            activityLog.setLoginName(userInfo.getUserName());
            activityLog.setCurrLoginName(userInfo.getUserName());
            activityLog.setActAsLoginName(userInfo.getDelegateUser());
        }
        StringBuilder content = new StringBuilder();
        content.append("Module:").append(moduleLog.module()).append("; ");
        activityLog.setTransactionCode(moduleLog.operation());
        // if (args.length > 0) {
        // content.append("Parameters:");
        // for (int i = 0; i < args.length; i++) {
        // content.append(args[i].toString());
        // }
        // }
        activityLog.setActivityContent(content.toString());
        activityLog.setActivityResult(ModuleLogConstant.RESULT_SUCCESSFUL);
        activityLog.setActivityDatetime(DateUtils.getCurrentDate());
        activityLog.setTxnKeyRef(RequestContextUtils.getTxnKeyRef());
        return activityLog;
    }

    private TransactionLog createTransactionLog(Object[] args, ModuleLog moduleLog) {
        TransactionLog transactionLog = new TransactionLog();
        UserInfo userInfo = RequestContextUtils.getCurrentUserInfo();
        if (userInfo != null) {
            transactionLog.setCurrLoginName(userInfo.getUserName());
            transactionLog.setActAsLoginName(userInfo.getDelegateUser());
        }

        transactionLog.setTransactionCode(moduleLog.operation());
        transactionLog.setTransactionResult(ModuleLogConstant.RESULT_SUCCESSFUL);
        transactionLog.setTransactionDatetime(DateUtils.getCurrentDate());
        transactionLog.setTxnKeyRef(RequestContextUtils.getTxnKeyRef());
        return transactionLog;
    }
}
