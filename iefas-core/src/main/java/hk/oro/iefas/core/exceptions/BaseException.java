package hk.oro.iefas.core.exceptions;

import lombok.NoArgsConstructor;

/**
 * @version $Revision: 918 $ $Date: 2018-01-20 15:46:36 +0800 (週六, 20 一月 2018) $
 * @author $Author: marvel.ma $
 */
@NoArgsConstructor
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -6407498577231238030L;

    public BaseException(String message, Throwable e) {
        super(message, e);
    }

    public BaseException(Throwable e) {
        super(e);
    }

    public BaseException(String message) {
        super(message);
    }

}
