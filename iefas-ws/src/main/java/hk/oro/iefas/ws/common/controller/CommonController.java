package hk.oro.iefas.ws.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.CaseAccountTypeDTO;
import hk.oro.iefas.domain.common.dto.ApplicationCodeTableDTO;
import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.domain.common.dto.OutsiderTypeDTO;
import hk.oro.iefas.domain.common.dto.StatusDTO;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeDTO;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeTypeDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentTypeDTO;
import hk.oro.iefas.domain.shroff.dto.TransferAmountTypeDTO;
import hk.oro.iefas.domain.shroff.dto.VoucherTypeDTO;
import hk.oro.iefas.domain.system.dto.SysRejectReasonDTO;
import hk.oro.iefas.ws.common.service.CommonService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3129 $ $Date: 2018-06-13 18:18:42 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_COMMON)
public class CommonController {

    @Autowired
    private CommonService commonService;

    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_STATUS_LIST)
    public List<StatusDTO> searchStatusList() {
        log.info("searchStatusList start -");
        List<StatusDTO> list = commonService.searchStatusList();
        log.info("searchStatusList end - rerurn: " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_CASE_TYPE_LIST)
    public List<CaseTypeDTO> searchCaseTypeList() {
        log.info("searchCaseTypeList start -");
        List<CaseTypeDTO> list = commonService.searchCaseTypeList();
        log.info("searchCaseTypeList end - return: " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_DIVIDEND_CASE_TYPE_LIST)
    public List<CaseTypeDTO> searchDividendCaseTypeList() {
        log.info("searchDividendCaseTypeList start -");
        List<CaseTypeDTO> list = commonService.searchDividendCaseTypeList();
        log.info("searchDividendCaseTypeList end - return: " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_CALCULATION_METHOD)
    public List<ApplicationCodeTableDTO> searchCalculationMethod() {
        log.info("searchCalculationMethod start -");
        List<ApplicationCodeTableDTO> list = commonService.searchCalculationMethod();
        log.info("searchCalculationMethod end - return: " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_OUTSIDER_TYPE_LIST)
    public List<OutsiderTypeDTO> searchOutsiderTypeList() {
        log.info("searchOutsiderTypeList start -");
        List<OutsiderTypeDTO> list = commonService.searchOutsiderTypeList();
        log.info("searchOutsiderTypeList end - return: " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_ADDRESS_TYPE_LIST)
    public List<ApplicationCodeTableDTO> searchAddressType() {
        log.info("searchAddressType start -");
        List<ApplicationCodeTableDTO> list = commonService.searchAddressType();
        log.info("searchAddressType end - rerurn: " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_NATURE_OF_CLAIM)
    public List<ApplicationCodeTableDTO> searchNatureOfClaim() {
        log.info("searchNatureOfClaim start -");
        List<ApplicationCodeTableDTO> list = commonService.searchNatureOfClaim();
        log.info("searchNatureOfClaim end - return: " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_CASE_ACCOUNT_TYPE_LIST)
    public List<CaseAccountTypeDTO> searchCaseAccountTypeList() {
        log.info("searchCaseAccountTypeList start -");
        List<CaseAccountTypeDTO> list = commonService.searchCaseAccountTypeList();
        log.info("searchCaseAccountTypeList end - return: " + list);
        return list;
    }

    @GetMapping(value = RequestUriConstant.URI_PAYMENT_TYPE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<ApplicationCodeTableDTO> searchPaymentType() {
        log.info("searchPaymentType start -");
        List<ApplicationCodeTableDTO> list = commonService.searchPaymentType();
        log.info("searchPaymentType end - rerurn: " + list);
        return list;
    }

    @GetMapping(value = RequestUriConstant.URI_DIVIDEND_SCHEDULE_STATUS_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<ApplicationCodeTableDTO> searchDividendScheduleStatus() {
        log.info("searchDividendScheduleStatus start -");
        List<ApplicationCodeTableDTO> list = commonService.searchDividendScheduleStatus();
        log.info("searchDividendScheduleStatus end - rerurn: " + list);
        return list;
    }

    @GetMapping(value = RequestUriConstant.URI_PLEDGE_TYPE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<ApplicationCodeTableDTO> searchPledgeType() {
        log.info("searchPledgeType start -");
        List<ApplicationCodeTableDTO> list = commonService.searchPledgeType();
        log.info("searchPledgeType end - rerurn: " + list);
        return list;
    }

    @GetMapping(value = RequestUriConstant.URI_DIVIDEND_SCHEDULE_TYPE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<ApplicationCodeTableDTO> searchDividendScheduleType() {
        log.info("searchDividendScheduleType start -");
        List<ApplicationCodeTableDTO> list = commonService.searchDividendScheduleType();
        log.info("searchDividendScheduleType end - rerurn: " + list);
        return list;
    }

    @GetMapping(value = RequestUriConstant.URI_SEARCH_ANALYSIS_CODE)
    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<AnalysisCodeDTO> searchAnalysisCodeList() {
        log.info("searchAnalysisCodeList start -");
        List<AnalysisCodeDTO> list = commonService.searchAnalysisCodeList();
        log.info("searchAnalysisCodeList end - rerurn: " + list);
        return list;
    }

    @GetMapping(value = RequestUriConstant.URI_SEARCH_ALL_CODE_TABLE)
    public List<ApplicationCodeTableDTO> searchAllCodeTable() {
        log.info("searchAllCodeTable - start");
        List<ApplicationCodeTableDTO> result = commonService.searchAllCodeTable();
        log.info("searchAllCodeTable - end " + result);
        return result;
    }

    @GetMapping(value = RequestUriConstant.URI_SEARCH_ANALYSIS_CODE_TYPE)
    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<AnalysisCodeTypeDTO> searchAnalysisCodeTypeList() {
        log.info("searchAnalysisCodeList start -");
        List<AnalysisCodeTypeDTO> list = commonService.searchAnalysisCodeTypeList();
        log.info("searchAnalysisCodeList end - rerurn: " + list);
        return list;
    }

    @GetMapping(value = RequestUriConstant.URI_SEARCH_VOUCHER_TYPE)
    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<VoucherTypeDTO> searchVoucherTypeList() {
        log.info("searchVoucherTypeList start -");
        List<VoucherTypeDTO> list = commonService.searchVoucherTypeList();
        log.info("searchVoucherTypeList end - rerurn: " + list);
        return list;
    }

    @GetMapping(value = RequestUriConstant.URI_SEARCH_PAYMENT_TYPE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<PaymentTypeDTO> searchPaymentTypeList() {
        log.info("searchPaymentType start -");
        List<PaymentTypeDTO> list = commonService.searchPaymentTypeList();
        log.info("searchPaymentType end - rerurn: " + list);
        return list;
    }

    @GetMapping(value = RequestUriConstant.URI_SEARCH_REJECT_REASON_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<SysRejectReasonDTO> searchRejectReasonList() {
        log.info("searchRejectReasonList start -");
        List<SysRejectReasonDTO> list = commonService.searchRejectReasonList();
        log.info("searchRejectReasonList end - rerurn: " + list);
        return list;
    }
    
    @GetMapping(value = RequestUriConstant.URI_SEARCH_TRANSFER_AMOUNT_TYPE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_COMMON, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<TransferAmountTypeDTO> searchTransferAmountTypeList() {
        log.info("searchTransferAmountTypeList start -");
        List<TransferAmountTypeDTO> list = commonService.searchTransferAmountTypeList();
        log.info("searchTransferAmountTypeList end - rerurn: " + list);
        return list;
    }
}
