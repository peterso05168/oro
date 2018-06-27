package hk.oro.iefas.ws.shroff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.dto.BulkReversalDTO;
import hk.oro.iefas.domain.shroff.dto.BulkReversalResultDTO;
import hk.oro.iefas.domain.shroff.dto.GenerateBulkReversalDTO;
import hk.oro.iefas.domain.shroff.dto.SearchBulkReversalDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.BulkReversalService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
@RestController
@Slf4j
@RequestMapping(value = RequestUriConstant.URI_ROOT_BULK_REVESAL)
public class BulkReversalController {

    @Autowired
    private BulkReversalService bulkReversalService;

    @PostMapping(RequestUriConstant.URI_BULK_REVESAL_SEARCH)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public BulkReversalResultDTO searchBulkReversal(@RequestBody SearchBulkReversalDTO criteria) {
		log.info("searchBulkReversal() start - criteria: " + criteria);
		BulkReversalResultDTO result = null;
		result = bulkReversalService.searchBulkReversal(criteria);
		log.info("searchBulkReversal() end - " + result);
		return result;
	}
    
    @PostMapping(RequestUriConstant.URI_BULK_REVESAL_LOAD)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public BulkReversalDTO loadBulkReversalDetail(@RequestBody Long bulkReversalId) {
		log.info("loadBulkReversalDetail() start - bulkReversalId: " + bulkReversalId);
		BulkReversalDTO result = null;
		result = bulkReversalService.loadBulkReversalDetail(bulkReversalId);
		log.info("loadBulkReversalDetail() end - " + result);
		return result;
	}
    
    @PostMapping(RequestUriConstant.URI_BULK_REVESAL_GENERATE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public BulkReversalDTO generateBulkReversal(@RequestBody GenerateBulkReversalDTO generateBulkReversalDTO) {
		log.info("generateBulkReversal() start - generateBulkReversalDTO: " + generateBulkReversalDTO);
		BulkReversalDTO result = null;
		result = bulkReversalService.generateBulkReversal(generateBulkReversalDTO);
		log.info("generateBulkReversal() end - " + result);
		return result;
	}
    
    @PostMapping(RequestUriConstant.URI_BULK_REVESAL_INSERT)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
	public boolean insertBulkReversal(@RequestBody BulkReversalDTO confirmedBulkReversalDto) {
		log.info("insertBulkReversal() start - confirmedBulkReversalVo: " + confirmedBulkReversalDto);
		boolean result = false;
		result = bulkReversalService.insertBulkReversal(confirmedBulkReversalDto);
		log.info("insertBulkReversal() end - " + result);
		return result;
	}
    
    @PostMapping(RequestUriConstant.URI_BULK_REVESAL_CONFIRM)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
	public BulkReversalDTO confirmBulkReversal(@RequestBody BulkReversalDTO confirmedBulkReversal) {
		log.info("confirmBulkReversal() start - confirmedBulkReversalVo: " + confirmedBulkReversal);
		BulkReversalDTO result = bulkReversalService.confirmBulkReversal(confirmedBulkReversal);
		log.info("confirmBulkReversal() end - " + result);
		return result;
	}

}
