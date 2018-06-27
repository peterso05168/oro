package hk.oro.iefas.ws.funds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.funds.dto.CashRequirementResultDTO;
import hk.oro.iefas.domain.funds.dto.DailyCashRequirementResultDTO;
import hk.oro.iefas.domain.funds.dto.SearchCashRequirementCriteriaDTO;
import hk.oro.iefas.domain.funds.dto.SearchDailyCashRequirementCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.funds.service.CashRequirementService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_CASH_REQUIREMENT)
public class CashRequirementController {

    @Autowired
    private CashRequirementService cashRequirementService;

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_CASH_REQUIREMENT_LIST)
    public Page<CashRequirementResultDTO>
        searchCashRequirementList(@RequestBody SearchDTO<SearchCashRequirementCriteriaDTO> criteriaDTO) {
        log.info("searchCashRequirementList() start - ");
        Page<CashRequirementResultDTO> page = cashRequirementService.findByCriteria(criteriaDTO);
        log.info("searchCashRequirementList() end - return : " + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_CASH_REQUIREMENT_DETAIL)
    public CashRequirementResultDTO searchCashRequirement(@RequestBody Integer cashReqId) {
        log.info("searchCashRequirement() start -");
        CashRequirementResultDTO resultDTO = cashRequirementService.searchCashRequirement(cashReqId);
        log.info("searchCashRequirement() end - return : " + resultDTO);
        return resultDTO;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_CASH_REQUIREMENT_SAVE)
    public Integer saveCaseRequirement(@RequestBody CashRequirementResultDTO cashRequirementResultDTO) {
        Integer cashReqId = cashRequirementService.saveCaseRequirement(cashRequirementResultDTO);
        return cashReqId;
    }

    @PostMapping(value = RequestUriConstant.URI_CASH_REQUIREMENT_SAVE_VALIDATE)
    public boolean saveCaseRequirementValidate(@RequestBody CashRequirementResultDTO cashRequirementResultDTO) {
        boolean validate = cashRequirementService.saveCaseRequirementValidate(cashRequirementResultDTO);
        return validate;
    }

    @PostMapping(value = RequestUriConstant.URI_DAILY_CASH_REQUIREMENT_DETAIL)
    public DailyCashRequirementResultDTO searchDailyCashRequirement(SearchDailyCashRequirementCriteriaDTO criteriaDTO) {
        return null;
    }
}
