package hk.oro.iefas.web.system.divisionadministration.role.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.vo.DivisionPrivilegeVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeResultVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionPrivilegeClientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@Service
public class DivisionPrivilegeClientServiceImpl extends BaseClientService implements DivisionPrivilegeClientService {

    @Override
    public List<SearchPrivilegeResultVO> findByCriteria(SearchPrivilegeCriteriaVO criteria) {
        log.info("findByCriteria() start - " + criteria);
        HttpEntity<SearchPrivilegeCriteriaVO> entity = new HttpEntity<SearchPrivilegeCriteriaVO>(criteria);

        ResponseEntity<List<SearchPrivilegeResultVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_DIVISION_PRIVILEGE_LIST, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<SearchPrivilegeResultVO>>() {});
        List<SearchPrivilegeResultVO> body = responseEntity.getBody();
        log.info("findByCriteria() end - " + body);
        return body;
    }

    @Override
    public DivisionPrivilegeVO findByDivisionAndPrivilege(SearchPrivilegeCriteriaVO criteria) {
        log.info("findByDivisionAndPrivilege() start - " + criteria);
        HttpEntity<SearchPrivilegeCriteriaVO> entity = new HttpEntity<SearchPrivilegeCriteriaVO>(criteria);

        ResponseEntity<DivisionPrivilegeVO> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_DIVISION_PRIVILEGE_FINDBY_DIVISION, HttpMethod.POST, entity,
                new ParameterizedTypeReference<DivisionPrivilegeVO>() {});
        DivisionPrivilegeVO body = responseEntity.getBody();
        log.info("findByDivisionAndPrivilege() end - " + body);
        return body;
    }

    @Override
    public Integer save(DivisionPrivilegeVO divisionPrivilegeVO) {
        log.info("create() start - " + divisionPrivilegeVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_DIVISION_PRIVILEGE_SAVE, divisionPrivilegeVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end - " + body);
        return body;
    }

}
