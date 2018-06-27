package hk.oro.iefas.ws.shroff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiveDepositDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiveDepositResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchReceiveDepositDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.ReceiptService;
import hk.oro.iefas.ws.shroff.service.ReceiveDepositService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@RestController
@Slf4j
@RequestMapping(RequestUriConstant.URI_ROOT_RECEIVE_DEPOSIT)
public class ReceiveDepositController {

    @Autowired
    private ReceiveDepositService receiveDepositService;

    @Autowired
    private ReceiptService receiptService;

    @PostMapping(RequestUriConstant.URI_RECEIVE_DEPOSIT_SEARCH_RECEIVE_DEPOSIT)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<ReceiveDepositResultDTO>
        searchReceiveDepositList(@RequestBody SearchDTO<SearchReceiveDepositDTO> criteria) {
        log.info("searchReceiveDepositList start - criteria: " + criteria);
        Page<ReceiveDepositResultDTO> result = null;
        result = receiveDepositService.searchReceiveDepositList(criteria);
        log.info("searchReceiveDepositList end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_RECEIVE_DEPOSIT_GET_DEPOSIT_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public ReceiveDepositDTO getDepositDetail(@RequestBody Integer depositId) {
        log.info("getDepositDetail start - depositId: " + depositId);
        ReceiveDepositDTO result;
        result = receiveDepositService.getReceiveDepositDetail(depositId);
        log.info("getDepositDetail end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_RECEIVE_DEPOSIT_SAVE_DEPOSIT)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public Integer saveReceiveDeposit(@RequestBody ReceiveDepositDTO receiveDeposit) {
        log.info("saveReceiveDeposit start - receiveDeposit: " + receiveDeposit);
        Integer depositId;
        depositId = receiveDepositService.saveReceiveDeposit(receiveDeposit);
        log.info("saveReceiveDeposit end - " + depositId);
        return depositId;
    }

    @PostMapping(RequestUriConstant.URI_RECEIPT_SAVE_RECEIPT)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public Integer saveReceipt(@RequestBody ReceiveDepositDTO receiveDepositDTO) {
        log.info("saveReceipt start - receiveDepositDTO: " + receiveDepositDTO);
        Integer result = receiveDepositService.saveReceipt(receiveDepositDTO);
        log.info("saveReceipt end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_RECEIPT_GENERATE_RECEIPT_NUMBER)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public String generateReceiptNumber() {
        log.info("generateReceiptNumber start");
        String receiptNumber = receiptService.generateReceiptNumber();
        log.info("generateReceiptNumber end");
        return receiptNumber;
    }

    @PostMapping(RequestUriConstant.URI_RECEIVE_DEPOSIT_GENERATE_DEPOSIT_NUMBER)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public String generateDepositNumber() {
        log.info("generateDepositNumber start");
        String result = receiveDepositService.generateDepositNo();
        log.info("generateDepositNumber end - " + result);
        return result;
    }
}
