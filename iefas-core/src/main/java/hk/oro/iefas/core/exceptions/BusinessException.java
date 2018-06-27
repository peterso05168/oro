package hk.oro.iefas.core.exceptions;

import java.util.List;

import hk.oro.iefas.core.exceptions.support.ErrorMsg;
import lombok.Getter;
import lombok.Setter;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    List<ErrorMsg> errors;

    public BusinessException() {
        super();
    }

    /**
     * @param message
     */
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, List<ErrorMsg> errors) {
        super(message);
        this.errors = errors;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
    }

    public BusinessException(String message, Throwable e, List<ErrorMsg> errors) {
        super(message, e);
        this.errors = errors;
    }

    public BusinessException(Throwable e) {
        super(e);
    }

}
