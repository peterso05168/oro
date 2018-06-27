package hk.oro.iefas.web.system.divisionadministration.post.service;

import java.util.List;

import hk.oro.iefas.domain.organization.vo.SearchRoleCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchRoleResultVO;
import hk.oro.iefas.domain.security.vo.PostRoleVO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface PostRoleClientService {

    List<SearchRoleResultVO> findByPostId(Integer postId);

    Integer save(PostRoleVO postRoleVO);

    PostRoleVO findByPostAndRole(SearchRoleCriteriaVO criteria);

    List<PostRoleVO> findByRole(SearchRoleCriteriaVO criteria);

}
