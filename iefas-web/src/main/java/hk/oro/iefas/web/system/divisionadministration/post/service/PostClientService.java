package hk.oro.iefas.web.system.divisionadministration.post.service;

import java.util.List;

import hk.oro.iefas.domain.organization.vo.ApproverVO;
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.domain.organization.vo.SearchPostCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchPostResultVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface PostClientService {

    PostVO findOne(Integer postId);

    Integer save(PostVO postVO);

    ResultPageVO<List<SearchPostResultVO>> findByCriteria(SearchVO<SearchPostCriteriaVO> criteria);

    Boolean existsByPostName(String postName, Integer postId);

    List<PostVO> findByDivisionId(Integer divisionId);

    List<PostVO> findByDivisionAdmin(Integer postId);

    List<ApproverVO> getFirstApprover(Integer divisionId);

    List<ApproverVO> getSecondApprover(Integer divisionId);

    List<PostVO> findByDivisionName(String divisionName);

    List<ApproverVO> getAApprover(Integer divisionId);

    List<ApproverVO> getBApprover(Integer divisionId);
}
