package hk.oro.iefas.web.system.divisionadministration.role.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.vo.DivisionAdminVO;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.SearchDivisionCriteriaVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
public class DivisionAdminClientServiceImpl extends BaseClientService implements DivisionAdminClientService {

    @Override
    public List<DivisionVO> getByPostId(Integer postId) {
        log.info("getByPostId() start - postId: " + postId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(postId);

        ResponseEntity<List<DivisionVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_DIVISION_ADMIN_BY_POST, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<DivisionVO>>() {});
        List<DivisionVO> body = responseEntity.getBody();
        log.info("getByPostId() end - " + body);
        return body;
    }

    @Override
    public List<DivisionAdminVO> getByDivisionId(Integer divisionId) {
        log.info("getByDivisionId() start - divisionId: " + divisionId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(divisionId);

        ResponseEntity<List<DivisionAdminVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_DIVISION_ADMIN_BY_DIVISION, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<DivisionAdminVO>>() {});
        List<DivisionAdminVO> body = responseEntity.getBody();
        log.info("getByDivisionId() end - " + body);
        return body;
    }

    @Override
    public Integer save(DivisionAdminVO divisionAdminVO) {
        log.info("create() start - " + divisionAdminVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_DIVISION_ADMIN_SAVE, divisionAdminVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end -  result = " + body);
        return body;
    }

    @Override
    public DivisionAdminVO findOne(Integer divisionAdminId) {
        log.info("findOne() start - divisionAdminId: " + divisionAdminId);
        ResponseEntity<DivisionAdminVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_GET_DIVISION_ADMIN_DETAIL, divisionAdminId, DivisionAdminVO.class);
        DivisionAdminVO body = responseEntity.getBody();
        log.info("findOne() end - " + body);
        return body;
    }

    @Override
    public DivisionAdminVO findByDivisionAndPost(SearchDivisionCriteriaVO criteria) {
        log.info("findByDivisionAndPost() start - criteria: " + criteria);
        HttpEntity<SearchDivisionCriteriaVO> entity = new HttpEntity<SearchDivisionCriteriaVO>(criteria);

        ResponseEntity<DivisionAdminVO> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_DIVISION_ADMIN_BY_DIVISION_AND_POST, HttpMethod.POST, entity,
                new ParameterizedTypeReference<DivisionAdminVO>() {});
        DivisionAdminVO body = responseEntity.getBody();
        log.info("findByDivisionAndPost() end - " + body);
        return body;
    }
}
