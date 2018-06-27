/**
 * 
 */
package hk.oro.iefas.ws.security.repository.assembler;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.ForgotPwdLogDTO;
import hk.oro.iefas.domain.security.entity.ForgotPwdLog;

/**
 * @version $Revision $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class ForgotPwdLogAssembler {

    public static ForgotPwdLogDTO toDTO(ForgotPwdLog forgotPwdLog) {
        ForgotPwdLogDTO forgotPwdLogDTO = null;
        if (forgotPwdLog != null) {
            forgotPwdLogDTO = DataUtils.copyProperties(forgotPwdLog, ForgotPwdLogDTO.class);
        }
        return forgotPwdLogDTO;
    }

}
