/**
 * 
 */
package hk.oro.iefas.ws.security.service;

import hk.oro.iefas.domain.security.dto.UserAttemptLogDTO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface UserAttemptLogService {

    void save(UserAttemptLogDTO userAttemptLogDTO);

}
