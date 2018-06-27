package hk.oro.iefas.web.system.systemadministration.divisionprivillege.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.SearchDivisionCriteriaVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.systemadministration.divisionprivillege.service.DivisionClientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
public class DivisionClientServiceImpl extends BaseClientService implements DivisionClientService {

    @Override
    public List<DivisionVO> findByCriteria(SearchDivisionCriteriaVO criteria) {
        log.info("findByCriteria() start - criteria: " + criteria);
        HttpEntity<SearchDivisionCriteriaVO> entity = new HttpEntity<SearchDivisionCriteriaVO>(criteria);

        ResponseEntity<List<DivisionVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_DIVISION_BY_CRITERIA, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<DivisionVO>>() {});
        List<DivisionVO> body = responseEntity.getBody();
        log.info("findByCriteria() end - " + body);
        return body;
    }

    @Override
    public DivisionVO findOne(Integer divisionId) {
        log.info("findByCriteria() start - divisionId: " + divisionId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(divisionId);

        ResponseEntity<DivisionVO> responseEntity = this.exchange(RequestUriConstant.CLIENT_URI_DIVISION_DETAIL,
            HttpMethod.POST, entity, new ParameterizedTypeReference<DivisionVO>() {});
        DivisionVO body = responseEntity.getBody();
        log.info("findByCriteria() end - " + body);
        return body;
    }

}
