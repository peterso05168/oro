package hk.oro.iefas.ws.casemgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.DepositCardDTO;
import hk.oro.iefas.ws.casemgt.service.DepositCardService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_DEPOSIT_CARD)
public class DepositCardController {

    @Autowired
    private DepositCardService depositCardService;

    @PostMapping(value = RequestUriConstant.URI_FIND_DEPOSIT_CARD_BY_CASE)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<DepositCardDTO> findDepositCardByCase(@RequestBody Integer caseId) {
        log.info("findDepositCardByCase() start - caseId = " + caseId);
        List<DepositCardDTO> result = depositCardService.findDepositCardByCase(caseId);
        log.info("findDepositCardByCase() end and result = " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_DEPOSIT_CARD_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public DepositCardDTO findOne(@RequestBody Integer depositCardId) {
        log.info("getDepositCardDetail() start - depositCardId = " + depositCardId);
        DepositCardDTO depositCard = depositCardService.findOne(depositCardId);
        log.info("getDepositCardDetail() end and result = " + depositCard);
        return depositCard;
    }

    @PostMapping(value = RequestUriConstant.URI_DEPOSIT_CARD_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = true)
    public Integer save(@RequestBody DepositCardDTO depositCard) {
        log.info("saveDepositCardDetail() start - DepositCardDTO = " + depositCard);
        Integer depositCardId = depositCardService.save(depositCard);
        log.info("saveDepositCardDetail() end and depositCardId = " + depositCardId);
        return depositCardId;
    }
}
