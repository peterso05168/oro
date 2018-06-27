package hk.oro.iefas.web.funds.investment.cashrequirement.service.impl;

import javax.inject.Named;

import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.funds.vo.DailyCashRequirementResultVO;
import hk.oro.iefas.domain.funds.vo.SearchDailyCashRequirementCriteriaVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.funds.investment.cashrequirement.service.CashRequirementClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class CashRequirementClientServiceImpl extends BaseClientService implements CashRequirementClientService {

    @Override
    public DailyCashRequirementResultVO searchDailyCashRequirement(SearchDailyCashRequirementCriteriaVO criteriaVO) {
        log.info("searchDailyCashRequirement() start - criteriaVO: " + criteriaVO);
        ResponseEntity<DailyCashRequirementResultVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_DAILY_CASH_REQUIREMENT_DETAIL, criteriaVO,
                DailyCashRequirementResultVO.class);
        DailyCashRequirementResultVO body = responseEntity.getBody();
        log.info("searchDailyCashRequirement() end - " + body);
        return body;
    }
}
