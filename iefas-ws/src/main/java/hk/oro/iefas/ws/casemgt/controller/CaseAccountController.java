package hk.oro.iefas.ws.casemgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.CaseAccountInfoDTO;
import hk.oro.iefas.domain.shroff.dto.SearchOldCaseAccountCriteriaDTO;
import hk.oro.iefas.ws.casemgt.service.CaseAccountService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_CASE_ACCOUNT)
public class CaseAccountController {

    @Autowired
    private CaseAccountService caseAccountService;

    @PostMapping(value = RequestUriConstant.URI_FIND_CASE_ACCOUNT_BY_CASE_ID)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<CaseAccountInfoDTO> findCaseAccountByCaseId(@RequestBody Integer caseId) {
        log.info("findCaseAccountByCaseId() start - caseId = " + caseId);
        List<CaseAccountInfoDTO> result = caseAccountService.findCaseAccountByCaseId(caseId);
        log.info("findCaseAccountByCaseId() end and result = " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_CASE_ACCOUNT_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public CaseAccountInfoDTO findOne(@RequestBody Integer caseAccountId) {
        log.info("getCaseAccountDetail() start - caseAccountId = " + caseAccountId);
        CaseAccountInfoDTO caseAccount = caseAccountService.findOne(caseAccountId);
        log.info("getCaseAccountDetail() end and result = " + caseAccount);
        return caseAccount;
    }

    @PostMapping(value = RequestUriConstant.URI_CASE_ACCOUNT_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = true)
    public Integer save(@RequestBody CaseAccountInfoDTO caseAccount) {
        log.info("saveCaseAccountDetail() start - CaseAccountInfoDTO = " + caseAccount);
        Integer caseAccountId = caseAccountService.save(caseAccount);
        log.info("saveCaseAccountDetail() end and caseAccountId = " + caseAccountId);
        return caseAccountId;
    }

    @PostMapping(value = RequestUriConstant.URI_FIND_CASE_ACCOUNT_BY_AC_NO)
    public CaseAccountInfoDTO findByAccountNumber(@RequestBody String accountNumber) {
        log.info("findByAccountNumber() start - and AccountNumber = " + accountNumber);
        CaseAccountInfoDTO caseAccountInfoDTO = caseAccountService.findByAccountNumber(accountNumber);
        log.info("findByAccountNumber() end - and returnVal = " + caseAccountInfoDTO);
        return caseAccountInfoDTO;
    }
    
    @PostMapping(value = RequestUriConstant.URI_FIND_OLD_CASE_ACCOUNT_BY_ACCOUNT_TYPE)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<CaseAccountInfoDTO> findOldCaseAccountByAccountType(@RequestBody SearchOldCaseAccountCriteriaDTO criteria) {
        log.info("findOldCaseAccountByAccountType() start - criteria = " + criteria);
        List<CaseAccountInfoDTO> result = caseAccountService.findOldCaseAccountByAccountType(criteria);
        log.info("findOldCaseAccountByAccountType() end and result = " + result);
        return result;
    }
}
