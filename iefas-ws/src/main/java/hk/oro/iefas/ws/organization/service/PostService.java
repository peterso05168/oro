/**
 * 
 */
package hk.oro.iefas.ws.organization.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.organization.dto.ApproverDTO;
import hk.oro.iefas.domain.organization.dto.PostDTO;
import hk.oro.iefas.domain.organization.dto.SearchPostCriteriaDTO;
import hk.oro.iefas.domain.organization.dto.SearchPostResultDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface PostService {

    Integer save(PostDTO postDTO);

    Page<SearchPostResultDTO> findByCriteria(SearchDTO<SearchPostCriteriaDTO> criteria);

    PostDTO findOne(Integer postId);

    boolean existsByPostTitle(String postTitle, Integer postId);

    List<PostDTO> findByDivisionId(Integer divisionId);

    List<PostDTO> findByDivisionAdmin(Integer postId);

    List<ApproverDTO> getFirstApprover(Integer divisionId);

    List<ApproverDTO> getSecondApprover(Integer divisionId);

    List<ApproverDTO> getAApprover(Integer divisionId);

    List<ApproverDTO> getBApprover(Integer divisionId);
}
