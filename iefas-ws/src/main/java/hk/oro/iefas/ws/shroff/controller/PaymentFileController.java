package hk.oro.iefas.ws.shroff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentFileResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchPaymentFileDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.PaymentFileService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@RestController
@Slf4j
@RequestMapping(RequestUriConstant.URI_ROOT_PAYMENT_FILE)
public class PaymentFileController {

    @Autowired
    private PaymentFileService paymentFileService;

    @PostMapping(RequestUriConstant.URI_PAYMENT_FILE_SEARCH_PAYMENT_FILE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<PaymentFileResultDTO> searchPaymentFile(@RequestBody SearchDTO<SearchPaymentFileDTO> criteria) {
        log.info("searchPaymentFile start - criteria: " + criteria);
        Page<PaymentFileResultDTO> result;
        result = paymentFileService.searchPaymentFile(criteria);
        log.info("searchPaymentFile start - result: " + result);
        return result;
    }
}
