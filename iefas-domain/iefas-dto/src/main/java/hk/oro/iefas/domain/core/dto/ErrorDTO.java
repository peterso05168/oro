package hk.oro.iefas.domain.core.dto;

import java.util.Date;
import java.util.List;

import hk.oro.iefas.core.exceptions.support.ErrorMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorDTO {

    private String message;
    private Date timestamp;
    private String exception;
    private List<ErrorMsg> errorMsgs;
}
