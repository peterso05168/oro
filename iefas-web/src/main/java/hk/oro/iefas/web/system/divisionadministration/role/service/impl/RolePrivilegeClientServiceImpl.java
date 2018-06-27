/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.role.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.vo.RolePrivilegeVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeResultVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.RolePrivilegeClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class RolePrivilegeClientServiceImpl extends BaseClientService implements RolePrivilegeClientService {
    @Override
    public RolePrivilegeVO findOne(Integer rolePrivilegeId) {
        log.info("findOne() start - roleId: " + rolePrivilegeId);
        ResponseEntity<RolePrivilegeVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_ROLE_PRIVILEGE_DETAIL, rolePrivilegeId, RolePrivilegeVO.class);
        RolePrivilegeVO body = responseEntity.getBody();
        log.info("findOne() end - " + body);
        return body;
    }

    @Override
    public Integer save(RolePrivilegeVO rolePrivilegeVO) {
        log.info("create() start - " + rolePrivilegeVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_ROLE_PRIVILEGE_SAVE, rolePrivilegeVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end - " + body);
        return body;
    }

    @Override
    public List<SearchPrivilegeResultVO> findByCriteria(SearchPrivilegeCriteriaVO criteria) {
        log.info("findByCriteria() start - " + criteria);
        HttpEntity<SearchPrivilegeCriteriaVO> entity = new HttpEntity<SearchPrivilegeCriteriaVO>(criteria);

        ResponseEntity<List<SearchPrivilegeResultVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_ROLE_PRIVILEGE_LIST, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<SearchPrivilegeResultVO>>() {});
        List<SearchPrivilegeResultVO> body = responseEntity.getBody();
        log.info("findByCriteria() end - " + body);
        return body;
    }

    @Override
    public RolePrivilegeVO findByRoleAndPrivilege(SearchPrivilegeCriteriaVO criteria) {
        log.info("findByRoleAndPrivilege() start - " + criteria);
        HttpEntity<SearchPrivilegeCriteriaVO> entity = new HttpEntity<SearchPrivilegeCriteriaVO>(criteria);

        ResponseEntity<RolePrivilegeVO> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_ROLE_PRIVILEGE_FINDBY_ROLE, HttpMethod.POST, entity,
                new ParameterizedTypeReference<RolePrivilegeVO>() {});
        RolePrivilegeVO body = responseEntity.getBody();
        log.info("findByRoleAndPrivilege() end - " + body);
        return body;
    }

}
