/**
 * 
 */
package hk.oro.iefas.ws.security.repository;

import hk.oro.iefas.domain.security.entity.ForgotPwdLog;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface ForgotPwdLogRepository extends BaseRepository<ForgotPwdLog, Integer> {

    ForgotPwdLog findByToken(String token);
}
