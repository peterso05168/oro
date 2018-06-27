/**
 * 
 */
package hk.oro.iefas.ws.security.service;

import java.util.List;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.security.dto.UserPwdHistDTO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface UserPwdHistService {

    Integer saveUserPwdHist(UserPwdHistDTO userPwdHistDTO);

    List<UserPwdHistDTO> findByUserAcId(SearchDTO<Integer> searchDTO);
}
