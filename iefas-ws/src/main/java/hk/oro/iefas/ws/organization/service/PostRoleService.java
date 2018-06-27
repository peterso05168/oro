/**
 * 
 */
package hk.oro.iefas.ws.organization.service;

import java.util.List;

import hk.oro.iefas.domain.organization.dto.SearchRoleCriteriaDTO;
import hk.oro.iefas.domain.organization.dto.SearchRoleResultDTO;
import hk.oro.iefas.domain.security.dto.PostRoleDTO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface PostRoleService {

    Integer save(PostRoleDTO postRoleDTO);

    List<SearchRoleResultDTO> findByPostId(Integer postId);

    PostRoleDTO findByPostAndRole(SearchRoleCriteriaDTO criteria);

    List<PostRoleDTO> findByRole(SearchRoleCriteriaDTO criteria);
}
