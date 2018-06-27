package hk.oro.iefas.ws.organization.controlller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.dto.ApproverDTO;
import hk.oro.iefas.domain.organization.dto.PostDTO;
import hk.oro.iefas.domain.organization.dto.SearchPostCriteriaDTO;
import hk.oro.iefas.domain.organization.dto.SearchPostResultDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.organization.service.PostService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_POST)
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = RequestUriConstant.URI_POST_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    public Integer save(@Valid @RequestBody PostDTO postDTO) {
        log.info("savePostDetail() start - " + postDTO);
        Integer postId = postService.save(postDTO);
        log.info("savePostDetail() end and postId = " + postId);
        return postId;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_VALIDATE)
    public Boolean existsByPostTitle(@RequestBody SearchPostCriteriaDTO criteria) {
        log.info("existsByPostName() start - and criteria = " + criteria);
        return postService.existsByPostTitle(criteria.getPostTitle(), criteria.getPostId());
    }

    @PostMapping(value = RequestUriConstant.URI_POST_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public PostDTO findOne(@RequestBody Integer postId) {
        log.info("getPostDetail() start - " + postId);
        PostDTO post = postService.findOne(postId);
        log.info("getPostDetail() end ");
        return post;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public Page<SearchPostResultDTO> findByCriteria(@RequestBody SearchDTO<SearchPostCriteriaDTO> criteria) {
        log.info("searchPostList() start - " + criteria);
        Page<SearchPostResultDTO> postList = postService.findByCriteria(criteria);
        log.info("searchPostList() end ");
        return postList;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_FINDBY_DIVISIONID)
    public List<PostDTO> findByDivisionId(@RequestBody Integer divisionId) {
        log.info("findByDivisionId() start - divisionId: " + divisionId);
        List<PostDTO> result = postService.findByDivisionId(divisionId);
        log.info("findByDivisionId() end - " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_FINDBY_DIVISIONADMIN)
    public List<PostDTO> findByDivisionAdmin(@RequestBody Integer postId) {
        log.info("findByDivisionAdmin() start - PostId: " + postId);
        List<PostDTO> result = postService.findByDivisionAdmin(postId);
        log.info("findByDivisionAdmin() end - " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_GET_FIRST_APPROVER)
    public List<ApproverDTO> getFirstApprover(@RequestBody Integer divisionId) {
        log.info("getFirstApprover() start - DivisionId: " + divisionId);
        List<ApproverDTO> dtoList = postService.getFirstApprover(divisionId);
        log.info("getFirstApprover() end  - " + dtoList);
        return dtoList;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_GET_SECOND_APPROVER)
    public List<ApproverDTO> getSecondApprover(@RequestBody Integer divisionId) {
        log.info("getSecondApprover() start - DivisionId: " + divisionId);
        List<ApproverDTO> dtoList = postService.getFirstApprover(divisionId);
        log.info("getSecondApprover() end  - " + dtoList);
        return dtoList;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_GET_A_APPROVER)
    public List<ApproverDTO> getAApprover(@RequestBody Integer divisionId) {
        log.info("getAApprover() start - DivisionId: " + divisionId);
        List<ApproverDTO> dtoList = postService.getAApprover(divisionId);
        log.info("getAApprover() end  - " + dtoList);
        return dtoList;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_GET_B_APPROVER)
    public List<ApproverDTO> getBApprover(@RequestBody Integer divisionId) {
        log.info("getBApprover() start - DivisionId: " + divisionId);
        List<ApproverDTO> dtoList = postService.getBApprover(divisionId);
        log.info("getBApprover() end  - " + dtoList);
        return dtoList;
    }
}
