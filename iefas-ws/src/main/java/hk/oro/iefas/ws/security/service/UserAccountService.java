/**
 * 
 */
package hk.oro.iefas.ws.security.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.security.dto.SearchUserCriteriaDTO;
import hk.oro.iefas.domain.security.dto.SearchUserResultDTO;
import hk.oro.iefas.domain.security.dto.UserAccountDTO;
import hk.oro.iefas.domain.security.dto.UserDetailDTO;

/**
 * @version $Revision: 1974 $ $Date $
 * @author $Author: marvel.ma $
 */
public interface UserAccountService {

    UserAccountDTO findByLoginName(String loginName);

    UserDetailDTO findUserDetail(String loginName);

    Integer save(UserAccountDTO userAccountDTO);

    void clearLock(String loginName);

    Page<SearchUserResultDTO> searchUserList(SearchDTO<SearchUserCriteriaDTO> searchDTO);

    Integer insertUserDetail(UserAccountDTO userAccountDTO);

    Integer updateUserDetail(UserAccountDTO userAccountDTO);

    UserAccountDTO findOne(Integer id);

}
