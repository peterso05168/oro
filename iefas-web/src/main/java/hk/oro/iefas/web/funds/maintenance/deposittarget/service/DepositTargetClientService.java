package hk.oro.iefas.web.funds.maintenance.deposittarget.service;

import java.util.List;

import hk.oro.iefas.domain.funds.vo.CashRequirementResultVO;
import hk.oro.iefas.domain.funds.vo.SearchCashRequirementCriteriaVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public interface DepositTargetClientService {

    public ResultPageVO<List<CashRequirementResultVO>>
        searchCashRequirementList(SearchVO<SearchCashRequirementCriteriaVO> criteriaVO);

    public CashRequirementResultVO searchCashRequirement(Integer cashReqId);

    public Integer saveCaseRequirement(CashRequirementResultVO resultVO);

    public Boolean saveCaseRequirementValidate(CashRequirementResultVO cashRequirementResult);
}
