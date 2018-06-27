package hk.oro.iefas.web.system.divisionadministration.role.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.vo.SearchRoleCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchRoleResultVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.security.vo.RoleSummaryVO;
import hk.oro.iefas.domain.security.vo.RoleVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.RoleClientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@Service
public class RoleClientServiceImpl extends BaseClientService implements RoleClientService {

    @Override
    public RoleVO findOne(Integer roleId) {
        log.info("findOne() start - roleId: " + roleId);
        ResponseEntity<RoleVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_ROLE_DETAIL, roleId, RoleVO.class);
        RoleVO body = responseEntity.getBody();
        log.info("findOne() end - " + body);
        return body;
    }

    @Override
    public Integer save(RoleVO roleVO) {
        log.info("create() start - " + roleVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_ROLE_SAVE, roleVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end - " + body);
        return body;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResultPageVO<List<SearchRoleResultVO>> findByCriteria(SearchVO<SearchRoleCriteriaVO> criteria) {
        log.info("findByCriteria() start - " + criteria);
        ResponseEntity<ResultPageVO> postForEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_ROLE_LIST, criteria, ResultPageVO.class);
        ResultPageVO<List<SearchRoleResultVO>> body = postForEntity.getBody();
        log.info("findByCriteria() end - " + body);
        return body;
    }

    @Override
    public Boolean existsByRoleName(String roleName, Integer roleId) {
        log.info("existsByRoleName() start - and roleName = " + roleName + ", roleId = " + roleId);
        SearchRoleCriteriaVO criteria = new SearchRoleCriteriaVO();
        criteria.setRoleName(roleName);
        criteria.setRoleId(roleId);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_ROLE_VALIDATE, criteria, Boolean.class);
        Boolean result = responseEntity.getBody();
        log.info("existsByRoleName() end - " + result);
        return result;
    }

    @Override
    public List<RoleSummaryVO> findByPostId(Integer postId) {
        log.info("findByPostId() start - and postId = " + postId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(postId);
        ResponseEntity<List<RoleSummaryVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_ROLE_SUMMARY_LIST, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<RoleSummaryVO>>() {});
        List<RoleSummaryVO> body = responseEntity.getBody();
        log.info("findByPostId() end - " + body);
        return body;
    }

    @Override
    public List<SearchRoleResultVO> findByDivisionId(Integer divisionId) {
        log.info("findByDivisionId() start - and postId = " + divisionId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(divisionId);
        ResponseEntity<List<SearchRoleResultVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_ROLE_LIST_BY_DIVISION, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<SearchRoleResultVO>>() {});
        List<SearchRoleResultVO> body = responseEntity.getBody();
        log.info("findByDivisionId() end - " + body);
        return body;
    }

}
