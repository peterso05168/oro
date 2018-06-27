package hk.oro.iefas.ws.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.core.exceptions.support.ErrorMsg;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.core.dto.ErrorDTO;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleOptimisticLockingFailureException(ObjectOptimisticLockingFailureException exception) {
        ErrorMsg errorMsg = new ErrorMsg(MsgCodeConstant.MSG_OUTDATED_DATA);
        ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), DateUtils.getCurrentDate(),
            exception.getClass().getName(), Arrays.asList(errorMsg));
        return errorDTO;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        ErrorDTO errorDTO = new ErrorDTO();
        if (bindingResult.hasFieldErrors()) {
            List<ErrorMsg> errors = new ArrayList<>();
            bindingResult.getFieldErrors().stream().forEach(error -> {
                errors.add(new ErrorMsg("XX", new String[] {error.getField()}));
            });
            errorDTO.setErrorMsgs(errors);
        }
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setTimestamp(DateUtils.getCurrentDate());
        errorDTO.setException(exception.getClass().getName());

        return errorDTO;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBusinessException(BusinessException exception) {

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMsgs(exception.getErrors());
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setTimestamp(DateUtils.getCurrentDate());
        errorDTO.setException(exception.getClass().getName());

        return errorDTO;
    }
}
