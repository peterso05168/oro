package hk.oro.iefas.ws.shroff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.dto.GeneralLedgerDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.GeneralLedgerService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @version $Revision$ $Date$
 * @author $Author$
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_GENERAL_LEDGER)
public class GeneralLedgerController {
    @Autowired
    private GeneralLedgerService generalLedgerService;
    
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_GENERAL_LEDGER_FIND_GENERAL_LEDGER_BY_CONTROL_ACCOUNT_ID)
    public GeneralLedgerDTO findGeneralLedgerByControlAcId(@RequestBody Integer controlAccountId) {
        log.info("findJournalVoucher() start - controlAccountId: " + controlAccountId);
        GeneralLedgerDTO generalLedger = this.generalLedgerService.findGeneralLedgerByControlAcId(controlAccountId);
        log.info("findJournalVoucher() end - GeneralLedgerDTO: " + generalLedger);
        return generalLedger;
    }
    
//    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
//    @PostMapping(value = RequestUriConstant.URI_GENERAL_LEDGER_FIND_GENERAL_LEDGER)
//    public GeneralLedgerDTO findGeneralLedger(@RequestBody Integer generalLedgerId) {
//        log.info("findJournalVoucher() start - generalLedgerId: " + generalLedgerId);
//        GeneralLedgerDTO generalLedger = this.generalLedgerService.findGeneralLedger(generalLedgerId);
//        log.info("findJournalVoucher() end - GeneralLedgerDTO: " + generalLedger);
//        return generalLedger;
//    }
}
