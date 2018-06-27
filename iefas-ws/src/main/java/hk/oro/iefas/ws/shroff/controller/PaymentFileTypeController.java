package hk.oro.iefas.ws.shroff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.dto.PaymentFileTypeDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.PaymentFileTypeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@RestController
@Slf4j
@RequestMapping(RequestUriConstant.URI_ROOT_PAYMENT_FILE_TYPE)
public class PaymentFileTypeController {
    @Autowired
    private PaymentFileTypeService paymentFileTypeService;

    @GetMapping(RequestUriConstant.URI_FIND_ALL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<PaymentFileTypeDTO> findAllPaymentFileType() {
        log.info("findAllPaymentFileType start");
        List<PaymentFileTypeDTO> result = paymentFileTypeService.findAllPaymentFileType();
        log.info("findAllPaymentFileType end - " + result);
        return result;
    }
}
