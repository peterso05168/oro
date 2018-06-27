/**
 * 
 */
package hk.oro.iefas.web.security.service;

import java.util.Date;

import hk.oro.iefas.domain.security.vo.ForgotPwdLogVO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface ForgotPwdLogClientService {

    Integer save(ForgotPwdLogVO forgotPwdLogVO);

    ForgotPwdLogVO findOne(Integer id);

    String createForgotPwdLog(Integer userAcId, Date forgotPwdDate);

    ForgotPwdLogVO findByToken(String token);
}
