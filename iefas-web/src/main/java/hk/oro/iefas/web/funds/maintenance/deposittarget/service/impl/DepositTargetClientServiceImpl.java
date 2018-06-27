package hk.oro.iefas.web.funds.maintenance.deposittarget.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.funds.vo.CashRequirementResultVO;
import hk.oro.iefas.domain.funds.vo.SearchCashRequirementCriteriaVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.funds.maintenance.deposittarget.service.DepositTargetClientService;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Named
public class DepositTargetClientServiceImpl extends BaseClientService implements DepositTargetClientService {

    @Override
    public ResultPageVO<List<CashRequirementResultVO>>
        searchCashRequirementList(SearchVO<SearchCashRequirementCriteriaVO> criteriaVO) {
        ResponseEntity<ResultPageVO<List<CashRequirementResultVO>>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_CASH_REQUIREMENT_LIST, HttpMethod.POST,
                new HttpEntity<SearchVO<SearchCashRequirementCriteriaVO>>(criteriaVO),
                new ParameterizedTypeReference<ResultPageVO<List<CashRequirementResultVO>>>() {});
        ResultPageVO<List<CashRequirementResultVO>> body = responseEntity.getBody();
        return body;
    }

    @Override
    public CashRequirementResultVO searchCashRequirement(Integer cashReqId) {
        ResponseEntity<CashRequirementResultVO> responseEntity
            = this.postForEntity(RequestUriConstant.CLIENT_URI_CASH_REQUIREMENT_DETAIL,
                cashReqId == null ? 0 : cashReqId, CashRequirementResultVO.class);
        CashRequirementResultVO body = responseEntity.getBody();
        return body;
    }

    @Override
    public Integer saveCaseRequirement(CashRequirementResultVO resultVO) {
        ResponseEntity<Integer> responseEntity
            = this.postForEntity(RequestUriConstant.CLIENT_URI_CASH_REQUIREMENT_SAVE, resultVO, Integer.class);
        Integer body = responseEntity.getBody();
        return body;
    }

    @Override
    public Boolean saveCaseRequirementValidate(CashRequirementResultVO cashRequirementResult) {
        ResponseEntity<Boolean> responseEntity = this.postForEntity(
            RequestUriConstant.CLIENT_URI_CASH_REQUIREMENT_SAVE_VALIDATE, cashRequirementResult, Boolean.class);
        Boolean body = responseEntity.getBody();
        return body;
    }

}
