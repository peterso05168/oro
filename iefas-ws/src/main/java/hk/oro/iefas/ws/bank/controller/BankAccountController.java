package hk.oro.iefas.ws.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.dto.BankAccountInfoResultDTO;
import hk.oro.iefas.domain.bank.dto.BankInfoDTO;
import hk.oro.iefas.domain.bank.dto.SearchBankAccountInfoCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.bank.service.BankAccountService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_BANK_ACCOUNT)
public class BankAccountController {

    @Autowired
    private BankAccountService bankService;

    @PostMapping(value = RequestUriConstant.URI_BANK_ACCOUNT_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public Page<BankAccountInfoResultDTO>
        searchBankAccountInfoList(@RequestBody SearchDTO<SearchBankAccountInfoCriteriaDTO> criteriaDTO) {
        log.info("searchBankAccountInfoList() start - " + criteriaDTO);
        Page<BankAccountInfoResultDTO> page = bankService.findByCriteria(criteriaDTO);
        log.info("searchBankAccountInfoList() end - " + page);
        return page;
    }

    @PostMapping(value = RequestUriConstant.URI_BANK_ACCOUNT_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    public Integer saveBankAccountInfo(@Valid @RequestBody BankInfoDTO bankInfo) {
        log.info("saveBankAccountInfo() start - " + bankInfo);
        Integer bankInfoId = bankService.save(bankInfo);
        log.info("saveBankAccountInfo() end ");
        return bankInfoId;
    }

    @PostMapping(value = RequestUriConstant.URI_BANK_ACCOUNT_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public BankInfoDTO searchBankAccountInfo(@RequestBody Integer bankInfoId) {
        log.info("searchBankAccountInfo() start - bankInfoId: " + bankInfoId);
        BankInfoDTO bankInfo = bankService.findOne(bankInfoId);
        log.info("searchBankAccountInfo() end - " + bankInfo);
        return bankInfo;
    }

    @GetMapping(value = RequestUriConstant.URI_FIND_ALL)
    public List<BankInfoDTO> findAll() {
        log.info("findAll() start - ");
        List<BankInfoDTO> bankInfoDTOs = bankService.findAll();
        log.info("findAll() end - " + bankInfoDTOs);
        return bankInfoDTOs;
    }

    @PostMapping(value = RequestUriConstant.URI_BANK_ACCOUNT_EXISTSBY_BANKCODE)
    public Boolean existsByBankCode(@RequestBody SearchBankAccountInfoCriteriaDTO criteriaDTO) {
        log.info("existsByBankCode() start - " + criteriaDTO);
        return bankService.existsByBankCodeAndBankInfoIdNot(criteriaDTO.getBankCode(), criteriaDTO.getBankInfoId());
    }

    @PostMapping(value = RequestUriConstant.URI_BANK_ACCOUNT_EXISTSBY_BANKNAME)
    public Boolean existsByBankName(@RequestBody SearchBankAccountInfoCriteriaDTO criteriaDTO) {
        log.info("existsByBankName() start - " + criteriaDTO);
        return bankService.existsByBankNameAndBankInfoIdNot(criteriaDTO.getBankName(), criteriaDTO.getBankInfoId());
    }

    @PostMapping(value = RequestUriConstant.URI_BANK_ACCOUNT_EXISTSBY_BANKSHORTNAME)
    public Boolean existsByBankShortName(@RequestBody SearchBankAccountInfoCriteriaDTO criteriaDTO) {
        log.info("existsByBankCode() start - " + criteriaDTO);
        return bankService.existsByBankShortNameAndBankInfoIdNot(criteriaDTO.getBankShortName(),
            criteriaDTO.getBankInfoId());
    }

    @PostMapping(value = RequestUriConstant.URI_BANK_ACCOUNT_FINDBY_BANKNAME)
    public BankInfoDTO findByBankName(@RequestBody String bankName) {
        log.info("findByBankName() start - " + bankName);
        BankInfoDTO bankInfoDTO = bankService.findByBankName(bankName);
        log.info("findByBankName() end - return: " + bankInfoDTO);
        return bankInfoDTO;
    }

}
