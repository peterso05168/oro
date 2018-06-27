package hk.oro.iefas.ws.funds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.funds.dto.AccountInvestmentItemDTO;
import hk.oro.iefas.domain.funds.dto.InvestmentInstructionDetailDTO;
import hk.oro.iefas.domain.funds.dto.SearchInvestmentInstructiontCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.funds.service.InvestItemService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_INVESTMENT_INSTRUCTION)
public class InvestmentInstructionController {

    @Autowired
    private InvestItemService investItemService;

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_INVESTMENT_INSTRUCTION_LIST)
    public Page<AccountInvestmentItemDTO>
        searchInvestmentInstructionList(@RequestBody SearchDTO<SearchInvestmentInstructiontCriteriaDTO> criteriaDTO) {
        log.info("findByCriteria() start - " + criteriaDTO);
        Page<AccountInvestmentItemDTO> page = investItemService.findByCriteria(criteriaDTO);
        log.info("findByCriteria() end - " + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_INVESTMENT_INSTRUCTION_DETAIL)
    public InvestmentInstructionDetailDTO searchInvestmentInstruction(@RequestBody Integer invItemId) {
        log.info("findOne() start - invItemId: " + invItemId);
        InvestmentInstructionDetailDTO instructionDetail = investItemService.searchInvestmentInstruction(invItemId);
        log.info("findOne() end - " + instructionDetail);
        return instructionDetail;
    }
}
