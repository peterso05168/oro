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
import hk.oro.iefas.domain.shroff.dto.BankTransferItemResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchBankTransferItemDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.ShrBankTxfItemService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3032 $ $Date: 2018-06-11 18:14:28 +0800 (週一, 11 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_BANK_TXF_ITEM)
public class ShrBankTxfItemController {

    @Autowired
    private ShrBankTxfItemService shrBankTxfItemService;

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_BANK_TXF_ITEM_SEARCH)
    public Page<BankTransferItemResultDTO>
        searchBankTransferItem(@RequestBody SearchDTO<SearchBankTransferItemDTO> searchBankTransferItemDTO) {
        log.info("searchBankTransferItem() start - " + searchBankTransferItemDTO);
        Page<BankTransferItemResultDTO> result
            = shrBankTxfItemService.searchBankTransferItem(searchBankTransferItemDTO);
        log.info("searchBankTransferItem() end - " + result);
        return result;
    }

}
