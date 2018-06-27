package hk.oro.iefas.web.core.jsf.exceptionhandler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import lombok.Getter;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class WebExceptionHandlerFactory extends ExceptionHandlerFactory {

    @Getter
    private final ExceptionHandlerFactory wrapped;

    public WebExceptionHandlerFactory(final ExceptionHandlerFactory wrapped) {
        this.wrapped = wrapped;
    }

    /* (non-Javadoc)
     * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
     */
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new WebExceptionHandler(wrapped.getExceptionHandler());
    }

}
