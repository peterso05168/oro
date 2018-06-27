package hk.oro.iefas.ws.funds.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.funds.dto.CashRequirementResultDTO;
import hk.oro.iefas.domain.funds.dto.DailyCashRequirementResultDTO;
import hk.oro.iefas.domain.funds.dto.SearchCashRequirementCriteriaDTO;
import hk.oro.iefas.domain.funds.dto.SearchDailyCashRequirementCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CashRequirementService {

    Page<CashRequirementResultDTO> findByCriteria(SearchDTO<SearchCashRequirementCriteriaDTO> searchDTO);

    CashRequirementResultDTO searchCashRequirement(Integer cashReqId);

    Integer saveCaseRequirement(CashRequirementResultDTO cashRequirementResultDTO);

    boolean saveCaseRequirementValidate(CashRequirementResultDTO cashRequirementResultDTO);

    DailyCashRequirementResultDTO searchDailyCashRequirement(SearchDailyCashRequirementCriteriaDTO criteriaDTO);
}
