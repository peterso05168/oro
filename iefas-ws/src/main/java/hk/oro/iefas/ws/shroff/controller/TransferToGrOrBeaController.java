/**
 * 
 */
package hk.oro.iefas.ws.shroff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.SearchTransferToGrOrBeaCriteriaDTO;
import hk.oro.iefas.domain.shroff.dto.SearchTransferToGrOrBeaResultDTO;
import hk.oro.iefas.domain.shroff.dto.TransferToGrOrBeaDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.TransferToGrOrBeaService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3249 $ $Date: 2018-06-21 14:42:20 +0800 (週四, 21 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_TRANSFER_TO_BEA_OR_GR)
public class TransferToGrOrBeaController {

    @Autowired
    private TransferToGrOrBeaService transferToGrOrBeaService;

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_FIND_ONE)
    public TransferToGrOrBeaDTO getTransferToGrOrBeaDetail(@RequestBody Integer transferId) {
        log.info("getTransferToGrOrBeaDetail() start and transferId = " + transferId);
        TransferToGrOrBeaDTO result = transferToGrOrBeaService.getTransferToGrOrBeaDetail(transferId);
        log.info("getTransferToGrOrBeaDetail() end - result" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(RequestUriConstant.URI_FIND_TRANSFER_BY_CRITERIA)
    public Page<SearchTransferToGrOrBeaResultDTO>
        findTransferByCriteria(@RequestBody SearchDTO<SearchTransferToGrOrBeaCriteriaDTO> criteria) {
        log.info("searchPaymentFile start - criteria: " + criteria);
        Page<SearchTransferToGrOrBeaResultDTO> result = transferToGrOrBeaService.findByCriteria(criteria);
        log.info("searchPaymentFile start - result: " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(RequestUriConstant.URI_SAVE)
    public Integer saveTransferToGrOrBea(@RequestBody TransferToGrOrBeaDTO transfer) {
        log.info("saveTransferToGrOrBea() start - transfer : " + transfer);
        Integer result = transferToGrOrBeaService.saveTransferToGrOrBea(transfer);
        log.info("saveTransferToGrOrBea() end - " + result);
        return result;
    }
}
