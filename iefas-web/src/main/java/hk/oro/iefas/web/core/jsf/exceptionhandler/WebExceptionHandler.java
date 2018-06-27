package hk.oro.iefas.web.core.jsf.exceptionhandler;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ProjectStage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

import org.omnifaces.util.Messages;
import org.primefaces.application.exceptionhandler.ExceptionInfo;
import org.primefaces.application.exceptionhandler.PrimeExceptionHandler;

import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.web.common.util.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
public class WebExceptionHandler extends PrimeExceptionHandler {

    /**
     * @param wrapped
     */
    public WebExceptionHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle() throws FacesException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context == null || context.getResponseComplete()) {
            return;
        }

        Iterable<ExceptionQueuedEvent> exceptionQueuedEvents = getUnhandledExceptionQueuedEvents();
        if (exceptionQueuedEvents != null && exceptionQueuedEvents.iterator() != null) {
            Iterator<ExceptionQueuedEvent> unhandledExceptionQueuedEvents
                = getUnhandledExceptionQueuedEvents().iterator();

            if (unhandledExceptionQueuedEvents.hasNext()) {
                try {
                    Throwable throwable = unhandledExceptionQueuedEvents.next().getContext().getException();

                    unhandledExceptionQueuedEvents.remove();

                    Throwable rootCause = getRootCause(throwable);
                    ExceptionInfo info = createExceptionInfo(rootCause);

                    // print exception in development stage
                    if (context.getApplication().getProjectStage() == ProjectStage.Development) {
                        rootCause.printStackTrace();
                    }

                    if (isLogException(context, rootCause)) {
                        logException(rootCause);
                    }

                    if (rootCause instanceof BusinessException) {
                        BusinessException ve = (BusinessException)rootCause;
                        ve.getErrors().stream().forEach(error -> {
                            Messages.addGlobalError(ApplicationContextUtils.getAppResourceUtils()
                                .getMessage(error.getCode(), error.getFields()));
                        });

                    } else {
                        if (context.getPartialViewContext().isAjaxRequest()) {
                            handleAjaxException(context, rootCause, info);
                        } else {
                            handleRedirect(context, rootCause, info, false);
                        }
                    }

                } catch (Exception ex) {
                    log.error("Could not handle exception!", ex);
                }
            }

            while (unhandledExceptionQueuedEvents.hasNext()) {
                // Any remaining unhandled exceptions are not interesting. First fix the first.
                unhandledExceptionQueuedEvents.next();
                unhandledExceptionQueuedEvents.remove();
            }
        }
    }
}
