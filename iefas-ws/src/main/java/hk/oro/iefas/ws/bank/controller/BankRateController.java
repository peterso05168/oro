package hk.oro.iefas.ws.bank.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.dto.BankDepositTypeDTO;
import hk.oro.iefas.domain.bank.dto.BankRateDTO;
import hk.oro.iefas.domain.bank.dto.CreateUploadBankRateDTO;
import hk.oro.iefas.domain.bank.dto.SearchUploadBankRateCriteriaDTO;
import hk.oro.iefas.domain.bank.dto.UploadBankRateDTO;
import hk.oro.iefas.domain.report.DownloadFileDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.bank.service.BankRateService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_BANK_RATE)
public class BankRateController {

    @Autowired
    private BankRateService bankRateService;

    @PostMapping(value = RequestUriConstant.URI_BANK_RATE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<BankRateDTO>
        searchUploadBankRateList(@RequestBody SearchDTO<SearchUploadBankRateCriteriaDTO> criteria) {
        return bankRateService.searchUploadBankRateList(criteria);
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_BANK_RATE_SAVE)
    public Integer createUploadBankRate(@RequestBody CreateUploadBankRateDTO uploadBankRateDTO) {
        return bankRateService.createUploadBankRate(uploadBankRateDTO);
    }

    @PostMapping(value = RequestUriConstant.URI_BANK_RATE_SAVE_VALIDATE)
    public boolean createUploadBankRateValidate(@RequestBody Date investDate) {
        return bankRateService.createUploadBankRateValidate(investDate);
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_BANK_RATE_DETAIL)
    public UploadBankRateDTO searchUploadBankRate(@RequestBody Integer bankRateId) {
        return bankRateService.searchUploadBankRate(bankRateId);
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_BANK_RATE_LIST_SAVE)
    public Integer saveDailyBankRateList(@RequestBody UploadBankRateDTO uploadBankRateDTO) {
        return bankRateService.saveDailyBankRateList(uploadBankRateDTO);
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_BANK_DEPOSIT_TYPE_LIST)
    public List<BankDepositTypeDTO> findBankDepositTypeList() {
        return bankRateService.findBankDepositTypeList();
    }

    @GetMapping(value = RequestUriConstant.URI_BANKRATE_TEMPLATE_DOWNLOAD)
    public DownloadFileDTO downloadBankRateTemplate() {
        return bankRateService.downloadBankRateTemplate();
    }
}
