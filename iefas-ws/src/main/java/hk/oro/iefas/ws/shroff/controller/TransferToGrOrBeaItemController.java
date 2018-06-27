/**
 * 
 */
package hk.oro.iefas.ws.shroff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.dto.TransferToGrOrBeaItemDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.TransferToGrOrBeaItemService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3250 $ $Date: 2018-06-21 14:42:31 +0800 (週四, 21 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_TRANSFER_TO_BEA_OR_GR_ITEM)
public class TransferToGrOrBeaItemController {

    @Autowired
    private TransferToGrOrBeaItemService transferToGrOrBeaItemService;

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_FIND_TRANSFER_ITEM_BY_TRANSFER)
    public List<TransferToGrOrBeaItemDTO> findTransferItemByTransfer(@RequestBody Integer transferId) {
        log.info("findTransferItemByTransfer() start and transferId = " + transferId);
        List<TransferToGrOrBeaItemDTO> result = transferToGrOrBeaItemService.findTransferItemByTransfer(transferId);
        log.info("findTransferItemByTransfer() end - result" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(RequestUriConstant.URI_SAVE)
    public Integer saveTransferToGrOrBeaItem(@RequestBody TransferToGrOrBeaItemDTO item) {
        log.info("saveTransferToGrOrBea() start - transfer Item: " + item);
        Integer result = transferToGrOrBeaItemService.saveTransferToGrOrBeaItem(item);
        log.info("saveTransferToGrOrBea() end - " + result);
        return result;
    }
}
