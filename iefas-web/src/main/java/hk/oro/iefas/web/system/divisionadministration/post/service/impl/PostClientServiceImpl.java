/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.post.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.vo.*;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import hk.oro.iefas.web.system.systemadministration.divisionprivillege.service.DivisionClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Service
public class PostClientServiceImpl extends BaseClientService implements PostClientService {

    @Inject
    private DivisionClientService divisionClientService;

    @Override
    public PostVO findOne(Integer postId) {
        log.info("findOne() start - postId: " + postId);
        ResponseEntity<PostVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_POST_DETAIL, postId, PostVO.class);
        PostVO body = responseEntity.getBody();
        log.info("findOne() end - result = " + body);
        return body;
    }

    @Override
    public Integer save(PostVO postVO) {
        log.info("create() start - " + postVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_POST_SAVE, postVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end -  result = " + body);
        return body;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResultPageVO<List<SearchPostResultVO>> findByCriteria(SearchVO<SearchPostCriteriaVO> criteria) {
        log.info("findByCriteria() start - " + criteria);
        ResponseEntity<ResultPageVO> postForEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_POST_LIST, criteria, ResultPageVO.class);
        ResultPageVO<List<SearchPostResultVO>> body = postForEntity.getBody();
        log.info("findByCriteria() end - result = " + body);
        return body;
    }

    @Override
    public Boolean existsByPostName(String postName, Integer postId) {
        log.info("existsByPostName() start - and postName = " + postName + ", postId = " + postId);
        SearchPostCriteriaVO criteria = new SearchPostCriteriaVO();
        criteria.setPostTitle(postName);
        criteria.setPostId(postId);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_POST_VALIDATE, criteria, Boolean.class);
        Boolean result = responseEntity.getBody();
        log.info("existsByPostName() end - result = " + result);
        return result;
    }

    @Override
    public List<PostVO> findByDivisionId(Integer divisionId) {
        log.info("findByDivisionId() start - and divisionId = " + divisionId);
        ResponseEntity<List<PostVO>> responseEntity = exchange(RequestUriConstant.CLIENT_URI_POST_FINDBY_DIVISIONID,
            HttpMethod.POST, new HttpEntity<Integer>(divisionId), new ParameterizedTypeReference<List<PostVO>>() {});
        List<PostVO> result = responseEntity.getBody();
        log.info("findByDivisionId() end - " + result);
        return result;
    }

    @Override
    public List<PostVO> findByDivisionAdmin(Integer postId) {
        log.info("findByDivisionAdmin() start - PostId: " + postId);
        ResponseEntity<List<PostVO>> responseEntity = exchange(RequestUriConstant.CLIENT_URI_POST_FINDBY_DIVISIONADMIN,
            HttpMethod.POST, new HttpEntity<Integer>(postId), new ParameterizedTypeReference<List<PostVO>>() {});
        List<PostVO> result = responseEntity.getBody();
        log.info("findByDivisionAdmin() end - " + result);
        return result;
    }

    @Override
    public List<ApproverVO> getFirstApprover(Integer divisionId) {
        log.info("get1stApprover() start - DivisionId: " + divisionId);
        ResponseEntity<List<ApproverVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_POST_GET_FIRST_APPROVER, HttpMethod.POST,
                new HttpEntity<Integer>(divisionId), new ParameterizedTypeReference<List<ApproverVO>>() {});
        List<ApproverVO> result = responseEntity.getBody();
        log.info("get1stApprover() end  - " + result);
        return result;
    }

    @Override
    public List<PostVO> findByDivisionName(String divisionName) {
        log.info("findByDivisionName() start - divisionName : " + divisionName);
        SearchDivisionCriteriaVO searchDivisionCriteriaVO = new SearchDivisionCriteriaVO(null, divisionName, null);
        List<DivisionVO> divisionVOS = divisionClientService.findByCriteria(searchDivisionCriteriaVO);
        List<PostVO> postVOS;
        if (divisionVOS != null) {
            if (divisionVOS.size() == 1) {
                DivisionVO divisionVO = divisionVOS.get(0);
                postVOS = findByDivisionId(divisionVO.getDivisionId());
            } else {
                postVOS = new ArrayList<>();
            }
        } else {
            postVOS = new ArrayList<>();
        }
        log.info("findByDivisionName() end -  " + postVOS);
        return postVOS;
    }

    @Override
    public List<ApproverVO> getSecondApprover(Integer divisionId) {
        log.info("getSecondApprover() start - DivisionId: " + divisionId);
        ResponseEntity<List<ApproverVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_POST_GET_SECOND_APPROVER, HttpMethod.POST,
                new HttpEntity<Integer>(divisionId), new ParameterizedTypeReference<List<ApproverVO>>() {});
        List<ApproverVO> result = responseEntity.getBody();
        log.info("getSecondApprover() end  - " + result);
        return result;
    }

    @Override
    public List<ApproverVO> getAApprover(Integer divisionId) {
        log.info("getAApprover() start - DivisionId: " + divisionId);
        ResponseEntity<List<ApproverVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_POST_GET_A_APPROVER, HttpMethod.POST,
                new HttpEntity<Integer>(divisionId), new ParameterizedTypeReference<List<ApproverVO>>() {});
        List<ApproverVO> result = responseEntity.getBody();
        log.info("getAApprover() end  - " + result);
        return result;
    }

    @Override
    public List<ApproverVO> getBApprover(Integer divisionId) {
        log.info("getSecondBpprover() start - DivisionId: " + divisionId);
        ResponseEntity<List<ApproverVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_POST_GET_B_APPROVER, HttpMethod.POST,
                new HttpEntity<Integer>(divisionId), new ParameterizedTypeReference<List<ApproverVO>>() {});
        List<ApproverVO> result = responseEntity.getBody();
        log.info("getSecondBpprover() end  - " + result);
        return result;
    }
}
