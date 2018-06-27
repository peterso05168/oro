/**
 * 
 */
package hk.oro.iefas.ws.security.service;

import java.util.Date;

import hk.oro.iefas.domain.security.dto.ForgotPwdLogDTO;

/**
 * @version $Revision $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface ForgotPwdLogService {

    Integer save(ForgotPwdLogDTO forgotPwdLogDTO);

    ForgotPwdLogDTO findOne(Integer id);

    String createForgotPwdLog(Integer userAcId, Date forgotPwdDate);

    ForgotPwdLogDTO findByToken(String token);
}
