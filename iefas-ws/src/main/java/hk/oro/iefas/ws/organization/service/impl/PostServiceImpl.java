/**
 * 
 */
package hk.oro.iefas.ws.organization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.ApproverDTO;
import hk.oro.iefas.domain.organization.dto.PostDTO;
import hk.oro.iefas.domain.organization.dto.SearchPostCriteriaDTO;
import hk.oro.iefas.domain.organization.dto.SearchPostResultDTO;
import hk.oro.iefas.domain.organization.entity.Post;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.organization.repository.DivisionRepository;
import hk.oro.iefas.ws.organization.repository.PostRepository;
import hk.oro.iefas.ws.organization.repository.RankRepository;
import hk.oro.iefas.ws.organization.repository.UserProfileRepository;
import hk.oro.iefas.ws.organization.repository.assembler.ApproverDTOAssembler;
import hk.oro.iefas.ws.organization.repository.assembler.DivisionDTOAssembler;
import hk.oro.iefas.ws.organization.repository.assembler.PostDTOAssembler;
import hk.oro.iefas.ws.organization.repository.assembler.RankDTOAssembler;
import hk.oro.iefas.ws.organization.repository.predicate.PostPredicate;
import hk.oro.iefas.ws.organization.service.PostService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3267 $ $Date: 2018-06-25 11:30:40 +0800 (週一, 25 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ApproverDTOAssembler approverDTOAssembler;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(PostDTO postDTO) {
        log.info("save() start - and postDTO = " + postDTO);
        Integer postId;
        if (postDTO.getPostId() != null && postDTO.getPostId() != 0) {
            Post post = DataUtils.copyProperties(postDTO, Post.class);
            postRepository.save(post);
            postId = post.getPostId();
        } else {
            postDTO.setStatus(CoreConstant.STATUS_ACTIVE);
            if (postDTO.getDivision().getDivisionId() != null && postDTO.getDivision().getDivisionId() > 0) {
                postDTO.setDivision(
                    DivisionDTOAssembler.toDTO(divisionRepository.findOne(postDTO.getDivision().getDivisionId())));
            }
            if (postDTO.getRank().getRankId() != null && postDTO.getRank().getRankId() > 0) {
                postDTO.setRank(RankDTOAssembler.toDTO(rankRepository.findOne(postDTO.getRank().getRankId())));
            }
            Post post = DataUtils.copyProperties(postDTO, Post.class);
            postRepository.save(post);
            postId = post.getPostId();
        }
        log.info("save end - and returnVal = " + postId);
        return postId;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SearchPostResultDTO> findByCriteria(SearchDTO<SearchPostCriteriaDTO> criteria) {
        log.info("findByCriteria() start - " + criteria);
        Page<SearchPostResultDTO> page = null;
        List<SearchPostResultDTO> tempList = null;
        Page<SearchPostResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = postRepository.findAll(PostDTOAssembler.toResultDTO(),
                    PostPredicate.findByCriteria(criteria.getCriteria()), pageable);
            }
        }
        if (page != null && CommonUtils.isNotEmpty(page.getContent())) {
            tempList = page.getContent();
            tempList.stream().forEach(item -> {
                String str = userProfileRepository.findUserNameByPostId(item.getPostId());
                item.setUserName(str == null || str.isEmpty() ? "" : str);
            });
            result = new PageImpl<>(tempList, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
                page.getTotalElements());
        }
        log.info("findByCriteria() end - " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO findOne(Integer postId) {
        log.info("findOne() start - and postId = " + postId);
        Post post = postRepository.findOne(postId);
        PostDTO result = PostDTOAssembler.toDTO(post);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByPostTitle(String postTitle, Integer postId) {
        log.info("existsByPostTitle() start - postTitle: " + postTitle + ", postId: " + postId);
        boolean result = postRepository.existsByPostTitleIgnoreCaseAndPostIdNot(postTitle, postId);
        log.info("existsByPostTitle() end - result: " + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostDTO> findByDivisionId(Integer divisionId) {
        log.info("findByDivisionId() start - and divisionId = " + divisionId);
        List<Post> posts
            = (List<Post>)postRepository.findAll(PostPredicate.findByDivisionId(divisionId), new Sort("postTitle"));
        List<PostDTO> result = PostDTOAssembler.toDTOList(posts);
        log.info("findByDivisionId end - and returnVal = " + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostDTO> findByDivisionAdmin(Integer postId) {
        log.info("findByDivisionAdmin() start - and postId = " + postId);
        List<Post> posts = postRepository.findByDivisionAdmin(postId);
        List<PostDTO> result = PostDTOAssembler.toDTOList(posts);
        log.info("findByDivisionAdmin end - and returnVal = " + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApproverDTO> getFirstApprover(Integer divisionId) {
        log.info("getFirstApprover() start - DivisionId: " + divisionId);
        List<Post> approvers = postRepository.getFirstApprover(divisionId);
        List<ApproverDTO> dtoList = approverDTOAssembler.toDTOList(approvers);
        log.info("getFirstApprover() end  - " + dtoList);
        return dtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApproverDTO> getSecondApprover(Integer divisionId) {
        log.info("getSecondApprover() start - DivisionId: " + divisionId);
        List<Post> approvers = postRepository.getSecondApprover(divisionId);
        List<ApproverDTO> dtoList = approverDTOAssembler.toDTOList(approvers);
        log.info("getSecondApprover() end  - " + dtoList);
        return dtoList;
    }

    @Override
    public List<ApproverDTO> getAApprover(Integer divisionId) {
        log.info("getAApprover() start - DivisionId: " + divisionId);
        List<Post> approvers = postRepository.getAApprover(divisionId);
        List<ApproverDTO> dtoList = approverDTOAssembler.toDTOList(approvers);
        log.info("getAApprover() end  - " + dtoList);
        return dtoList;
    }

    @Override
    public List<ApproverDTO> getBApprover(Integer divisionId) {
        log.info("getBApprover() start - DivisionId: " + divisionId);
        List<Post> approvers = postRepository.getBApprover(divisionId);
        List<ApproverDTO> dtoList = approverDTOAssembler.toDTOList(approvers);
        log.info("getBApprover() end  - " + dtoList);
        return dtoList;
    }
}
