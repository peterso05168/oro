package hk.oro.iefas.core.aop;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
public class ServiceMonitor {

    private static final String START_LOG_TEMPLATE = "Start: %s, Args: %s";
    private static final String COMPLETE_LOG_TEMPLATE = "Completed: %s, ReturnVal: %s";

    @Pointcut("execution (* hk.oro.iefas..*(..))")
    public void serviceAspect() {}

    @Before("serviceAspect()")
    public void logServiceStart(JoinPoint joinPoint) {
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class<?> targetClass = Class.forName(targetName);

            Field loggerField = null;
            try {
                loggerField = targetClass.getDeclaredField("logger");
                loggerField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                loggerField = null;
            }

            if (loggerField != null) {
                Logger classLogger = (Logger)loggerField.get(joinPoint.getTarget());

                classLogger.info(String.format(START_LOG_TEMPLATE, methodName, Arrays.toString(arguments)));
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @AfterReturning(pointcut = "serviceAspect()", returning = "returnValue")
    public void logServiceEnd(JoinPoint joinPoint, Object returnValue) {
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Class<?> targetClass = Class.forName(targetName);

            Field loggerField = null;
            try {
                loggerField = targetClass.getDeclaredField("logger");
                loggerField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                loggerField = null;
            }

            if (loggerField != null) {
                Logger classLogger = (Logger)loggerField.get(joinPoint.getTarget());

                classLogger.info(String.format(COMPLETE_LOG_TEMPLATE, methodName,
                    returnValue != null ? returnValue.toString() : "[]"));
            }

        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }

}
