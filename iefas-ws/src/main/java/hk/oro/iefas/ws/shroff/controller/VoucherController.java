package hk.oro.iefas.ws.shroff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.report.DownloadFileDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherResultItemDTO;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherSearchDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherResultDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherSearchDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherBasicInfoDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherResultDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherSearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.VoucherService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3122 $ $Date: 2018-06-13 17:34:56 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_VOUCHER)
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_UPDATE_RECEIPT_VOUCHER)
    public Integer saveReceiptVoucher(@RequestBody ReceiptVoucherBasicInfoDTO receiptVoucherBasicInformation) {
        log.info("saveReceiptVoucher() start - " + receiptVoucherBasicInformation);
        Integer voucherId = this.voucherService.saveReceiptVoucher(receiptVoucherBasicInformation);
        log.info("saveReceiptVoucher() end - voucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_FIND_RECEIPT_VOUCHER_BY_CRITERIA)
    public Page<ReceiptVoucherResultDTO>
        findReceiptVoucherByCriteria(@RequestBody SearchDTO<ReceiptVoucherSearchDTO> criteria) {
        log.info("findReceiptVoucherByCriteria() start - " + criteria);
        Page<ReceiptVoucherResultDTO> receiptVouchers = this.voucherService.findReceiptVoucherByCriteria(criteria);
        log.info("findReceiptVoucherByCriteria() end");
        return receiptVouchers;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_SAVE_RECEIPT_VOUCHER)
    public Integer saveReceiptVoucher(@RequestBody ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("saveReceiptVoucher() start - " + receiptVoucherDetail);
        Integer voucherId = this.voucherService.saveReceiptVoucher(receiptVoucherDetail);
        log.info("saveReceiptVoucher() end - voucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_SAVE_JOURNAL_VOUCHER)
    public Integer saveJournalVoucher(@RequestBody JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("saveJournalVoucher() start - " + journalVoucherDetail);
        Integer voucherId = voucherService.saveJournalVoucher(journalVoucherDetail);
        log.info("saveJournalVoucher() end VoucherId: " + voucherId);
        return voucherId;
    }

    @PostMapping(value = RequestUriConstant.URI_VOUCHER_FIND_JOURNAL_VOUCHER)
    public JournalVoucherDetailDTO findJournalVoucher(@RequestBody Integer voucherId) {
        log.info("findJournalVoucher() start - VoucherId: " + voucherId);
        JournalVoucherDetailDTO dto = voucherService.findJournalVoucher(voucherId);
        log.info("findJournalVoucher() end - " + dto);
        return dto;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_FIND_PAYMENT_VOUCHER_BY_CRITERIA)
    public Page<PaymentVoucherResultDTO>
        findPaymentVoucherByCriteria(@RequestBody SearchDTO<PaymentVoucherSearchDTO> criteria) {
        log.info("findPaymentVoucherByCriteria() start - criteria: " + criteria);
        Page<PaymentVoucherResultDTO> page = voucherService.findPaymentVoucherByCriteria(criteria);
        log.info("findPaymentVoucherByCriteria() end - " + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_SAVE_PAYMENT_VOUCHER)
    public Integer savePaymentVoucher(@RequestBody PaymentVoucherDetailDTO voucher) {
        log.info("savePaymentVoucher() start - voucher = " + voucher);
        Integer voucherId = voucherService.savePaymentVoucher(voucher);
        log.info("savePaymentVoucher() end VoucherId: " + voucherId);
        return voucherId;
    }
    
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_FIND_RECEIPT_VOUCHER)
    public ReceiptVoucherDetailDTO findReceiptVoucher(@RequestBody Integer voucherId) {
        log.info("findReceiptVoucher() start - VoucherId: " + voucherId);
        ReceiptVoucherDetailDTO dto = voucherService.findReceiptVoucher(voucherId);
        log.info("findReceiptVoucher() end - " + dto);
        return dto;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_FIND_PAYMENT_VOUCHER)
    public PaymentVoucherDetailDTO findPaymentVoucher(@RequestBody Integer voucherId) {
        log.info("findPaymentVoucher() start - VoucherId: " + voucherId);
        PaymentVoucherDetailDTO dto = voucherService.findPaymentVoucher(voucherId);
        log.info("findPaymentVoucher() end - " + dto);
        return dto;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_SEARCH_JOURNAL_VOUCHER)
    public Page<JournalVoucherResultItemDTO>
        searchJournalVoucher(@RequestBody SearchDTO<JournalVoucherSearchDTO> searchDTO) {
        log.info("searchJournalVoucher() start - " + searchDTO);
        Page<JournalVoucherResultItemDTO> journalVoucherResultItems = voucherService.searchJournalVoucher(searchDTO);
        log.info("searchJournalVoucher() end - " + journalVoucherResultItems);
        return journalVoucherResultItems;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_DELETE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_DELETE_VOUCHER)
    public void deleteVoucher(@RequestBody Integer voucherId) {
        log.info("deleteVoucher() start - VoucherId: " + voucherId);
        voucherService.deleteVoucher(voucherId);
        log.info("deleteVoucher() end");
    }

    @PostMapping(value = RequestUriConstant.URI_VOUCHER_DOWNLOAD_TEMPLATE)
    public DownloadFileDTO downloadImportTemplate(@RequestBody List<String> header) {
        log.info("downloadImportTemplate() start - header: " + header);
        DownloadFileDTO file = voucherService.downloadImportTemplate(header);
        log.info("downloadImportTemplate() end and file = " + file);
        return file;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_SUBMIT_JOURNAL_VOUCHER)
    public Integer submitJournalVoucher(@RequestBody JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("submitJournalVoucher() start - " + journalVoucherDetail);
        Integer voucherId = voucherService.submitJournalVoucher(journalVoucherDetail);
        log.info("submitJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_APPROVE_JOURNAL_VOUCHER)
    public Integer approveJournalVoucher(@RequestBody JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("approveJournalVoucher() start - " + journalVoucherDetail);
        Integer voucherId = voucherService.approveJournalVoucher(journalVoucherDetail);
        log.info("approveJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_REJECT_JOURNAL_VOUCHER)
    public Integer rejectJournalVoucher(@RequestBody JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("rejectJournalVoucher() start - " + journalVoucherDetail);
        Integer voucherId = voucherService.rejectJournalVoucher(journalVoucherDetail);
        log.info("rejectJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_VERIFY_JOURNAL_VOUCHER)
    public Integer verifyJournalVoucher(@RequestBody JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("verifyJournalVoucher() start - " + journalVoucherDetail);
        Integer voucherId = voucherService.verifyJournalVoucher(journalVoucherDetail);
        log.info("verifyJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_REVERSE_JOURNAL_VOUCHER)
    public Integer reverseJournalVoucher(@RequestBody JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("reverseJournalVoucher() start - " + journalVoucherDetail);
        Integer voucherId = voucherService.reverseJournalVoucher(journalVoucherDetail);
        log.info("reverseJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_DELETE_JOURNAL_VOUCHER)
    public Integer deleteJournalVoucher(@RequestBody JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("deleteJournalVoucher() start - " + journalVoucherDetail);
        Integer voucherId = voucherService.deleteJournalVoucher(journalVoucherDetail);
        log.info("deleteJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_SUBMIT_VOUCHER)
    public Integer submitReceiptVoucher(@RequestBody ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("submitReceiptVoucher() start - voucherWorkFlowDTO: " + receiptVoucherDetail);
        Integer result = this.voucherService.submitReceiptVoucher(receiptVoucherDetail);
        log.info("submitReceiptVoucher() end - result: " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_SUBMIT_PAYMENT_VOUCHER)
    public Integer submitPaymentVoucher(@RequestBody PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("submitPaymentVoucher() start - " + paymentVoucherDetail);
        Integer voucherId = voucherService.submitPaymentVoucher(paymentVoucherDetail);
        log.info("submitPaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_APPROVE_RECEIPT_VOUCHER)
    public Integer approveReceiptVoucher(@RequestBody ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("approveReceiptVoucher() start - ReceiptVoucherDetailDTO: " + receiptVoucherDetail);
        Integer voucherId = voucherService.approveReceiptVoucher(receiptVoucherDetail);
        log.info("approveReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_APPROVE_PAYMENT_VOUCHER)
    public Integer approvePaymentVoucher(@RequestBody PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("approvePaymentVoucher() start - " + paymentVoucherDetail);
        Integer voucherId = voucherService.approvePaymentVoucher(paymentVoucherDetail);
        log.info("approvePaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_REJECT_RECEIPT_VOUCHER)
    public Integer rejectReceiptVoucher(@RequestBody ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("rejectReceiptVoucher() start - ReceiptVoucherDetailDTO: " + receiptVoucherDetail);
        Integer voucherId = voucherService.rejectReceiptVoucher(receiptVoucherDetail);
        log.info("rejectReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_REJECT_PAYMENT_VOUCHER)
    public Integer rejectPaymentVoucher(@RequestBody PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("rejectPaymentVoucher() start - " + paymentVoucherDetail);
        Integer voucherId = voucherService.rejectPaymentVoucher(paymentVoucherDetail);
        log.info("rejectPaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_VERIFY_RECEIPT_VOUCHER)
    public Integer verifyReceiptVoucher(@RequestBody ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("verifyReceiptVoucher() start - ReceiptVoucherDetailDTO: " + receiptVoucherDetail);
        Integer voucherId = voucherService.verifyReceiptVoucher(receiptVoucherDetail);
        log.info("verifyReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_VERIFY_PAYMENT_VOUCHER)
    public Integer verifyPaymentVoucher(@RequestBody PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("verifyPaymentVoucher() start - " + paymentVoucherDetail);
        Integer voucherId = voucherService.verifyPaymentVoucher(paymentVoucherDetail);
        log.info("verifyPaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_REVERSE_RECEIPT_VOUCHER)
    public Integer reverseReceiptVoucher(@RequestBody ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("reverseReceiptVoucher() start - ReceiptVoucherDetailDTO: " + receiptVoucherDetail);
        Integer voucherId = voucherService.reverseReceiptVoucher(receiptVoucherDetail);
        log.info("reverseReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_REVERSE_PAYMENT_VOUCHER)
    public Integer reversePaymentVoucher(@RequestBody PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("reversePaymentVoucher() start - " + paymentVoucherDetail);
        Integer voucherId = voucherService.reversePaymentVoucher(paymentVoucherDetail);
        log.info("reversePaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_DELETE_RECEIPT_VOUCHER)
    public Integer deleteReceiptVoucher(@RequestBody ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("deleteReceiptVoucher() start - ReceiptVoucherDetailDTO： " + receiptVoucherDetail);
        Integer voucherId = voucherService.deleteReceiptVoucher(receiptVoucherDetail);
        log.info("deleteReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_VOUCHER_DELETE_PAYMENT_VOUCHER)
    public Integer deletePaymentVoucher(@RequestBody PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("deletePaymentVoucher() start - " + paymentVoucherDetail);
        Integer voucherId = voucherService.deletePaymentVoucher(paymentVoucherDetail);
        log.info("deletePaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

}
