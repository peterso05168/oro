package hk.oro.iefas.web.system.divisionadministration.post.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.vo.SearchRoleCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchRoleResultVO;
import hk.oro.iefas.domain.security.vo.PostRoleVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostRoleClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class PostRoleClientServiceImpl extends BaseClientService implements PostRoleClientService {

    @Override
    public List<SearchRoleResultVO> findByPostId(Integer postId) {
        log.info("findByCriteria() start - postId = " + postId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(postId);

        ResponseEntity<List<SearchRoleResultVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_POST_ROLE_LIST_BY_POST, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<SearchRoleResultVO>>() {});
        List<SearchRoleResultVO> body = responseEntity.getBody();
        log.info("findByCriteria() end - result = " + body);
        return body;
    }

    @Override
    public Integer save(PostRoleVO postRoleVO) {
        log.info("create() start - postRoleVO = " + postRoleVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_POST_ROLE_SAVE, postRoleVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end - result = " + body);
        return body;
    }

    @Override
    public PostRoleVO findByPostAndRole(SearchRoleCriteriaVO criteria) {
        log.info("findByPostAndRole() start - criteria = " + criteria);
        HttpEntity<SearchRoleCriteriaVO> entity = new HttpEntity<SearchRoleCriteriaVO>(criteria);

        ResponseEntity<PostRoleVO> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_POST_ROLE_BY_POST_AND_ROLE, HttpMethod.POST, entity,
                new ParameterizedTypeReference<PostRoleVO>() {});
        PostRoleVO body = responseEntity.getBody();
        log.info("findByPostAndRole() end - result = " + body);
        return body;
    }

    @Override
    public List<PostRoleVO> findByRole(SearchRoleCriteriaVO criteria) {
        log.info("findByPostAndRole() start - criteria = " + criteria);
        HttpEntity<SearchRoleCriteriaVO> entity = new HttpEntity<SearchRoleCriteriaVO>(criteria);

        ResponseEntity<List<PostRoleVO>> responseEntity = this.exchange(RequestUriConstant.CLIENT_URI_POST_ROLE_BY_ROLE,
            HttpMethod.POST, entity, new ParameterizedTypeReference<List<PostRoleVO>>() {});
        List<PostRoleVO> body = responseEntity.getBody();
        log.info("findByPostAndRole() end - result = " + body);
        return body;
    }
}
