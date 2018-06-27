/**
 * 
 */
package hk.oro.iefas.web.system.profile.service;

import java.util.List;

import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.security.vo.UserPwdHistVO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface UserPwdHistClientService {

    Integer saveUserPwdHist(UserPwdHistVO userPwdHistVO);

    List<UserPwdHistVO> findByUserAcId(SearchVO<Integer> searchVO);
}
