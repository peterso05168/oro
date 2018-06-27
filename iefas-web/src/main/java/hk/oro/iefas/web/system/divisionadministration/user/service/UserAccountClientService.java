/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.user.service;

import java.util.List;

import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.security.vo.SearchUserCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchUserResultVO;
import hk.oro.iefas.domain.security.vo.UserAccountVO;
import hk.oro.iefas.domain.security.vo.UserDetailVO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface UserAccountClientService {

    UserAccountVO findByLoginName(String loginName);

    UserDetailVO findUserDetail(String loginName);

    void clearLock(String loginName);

    Integer save(UserAccountVO userAccountVO);

    ResultPageVO<List<SearchUserResultVO>> searchUserList(SearchVO<SearchUserCriteriaVO> searchVO);

    Integer insertUserDetail(UserAccountVO userAccountVO);

    Integer updateUserDetail(UserAccountVO userAccountVO);

    UserAccountVO findOne(Integer id);
}
