package hk.oro.iefas.web.ledger.voucherhandling.common.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.report.DownloadFileVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.shroff.vo.JournalVoucherDetailVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherDetailVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherBasicInformationVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherDetailVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherResultVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherSearchVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.VoucherClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3122 $ $Date: 2018-06-13 17:34:56 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Service
public class VoucherClientServiceImpl extends BaseClientService implements VoucherClientService {

    @Override
    public Integer saveReceiptVoucher(ReceiptVoucherBasicInformationVO receiptVoucherBasicInformation) {
        log.info("saveReceiptVoucher start - ReceiptVoucherBasicInformationVO: " + receiptVoucherBasicInformation);
        ResponseEntity<Integer> responseEntity
            = super.postForEntity(RequestUriConstant.CLIENT_URI_VOUCHER_UPDATE_RECEIPT_VOUCHER,
                receiptVoucherBasicInformation, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveReceiptVoucher end - VoucherId: " + body);
        return body;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResultPageVO<List<ReceiptVoucherResultVO>> findByCriteria(SearchVO<ReceiptVoucherSearchVO> criteria) {
        log.info("findByCriteria start - " + criteria);
        ResponseEntity<ResultPageVO> postForEntity = super.postForEntity(
            RequestUriConstant.CLIENT_URI_FIND_RECEIPT_VOUCHER_BY_CRITERIA, criteria, ResultPageVO.class);
        ResultPageVO<List<ReceiptVoucherResultVO>> body = postForEntity.getBody();
        log.info("findByCriteria end - " + body);
        return body;
    }

    @Override
    public Integer saveReceiptVoucher(ReceiptVoucherDetailVO receiptVoucherDetailVO) {
        log.info("saveReceiptVoucher() start - " + receiptVoucherDetailVO);
        ResponseEntity<Integer> responseEntity = super.postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_SAVE_RECEIPT_VOUCHER, receiptVoucherDetailVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveReceiptVoucher() end VoucherId: " + body);
        return body;
    }

    @Override
    public Integer saveJournalVoucher(JournalVoucherDetailVO journalVoucherDetail) {
        log.info("saveJournalVoucher() start - " + journalVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_SAVE_JOURNAL_VOUCHER, journalVoucherDetail, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveJournalVoucher() end VoucherId: " + body);
        return body;
    }

    @Override
    public JournalVoucherDetailVO findJournalVoucher(Integer voucherId) {
        log.info("findJournalVoucher() start - VoucherId: " + voucherId);
        ResponseEntity<JournalVoucherDetailVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_FIND_JOURNAL_VOUCHER, voucherId, JournalVoucherDetailVO.class);
        JournalVoucherDetailVO journalVoucherDetailVO = responseEntity.getBody();
        log.info("findJournalVoucher() end - " + journalVoucherDetailVO);
        return journalVoucherDetailVO;
    }

    @Override
    public Integer savePaymentVoucher(PaymentVoucherDetailVO PaymentVoucherDetail) {
        log.info("savePaymentVoucher() start - " + PaymentVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_SAVE_PAYMENT_VOUCHER, PaymentVoucherDetail, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("savePaymentVoucher() end VoucherId: " + body);
        return body;
    }

    @Override
    public ReceiptVoucherDetailVO findReceiptVoucher(Integer voucherId) {
        log.info("findReceiptVoucher() start - voucherId: " + voucherId);
        ResponseEntity<ReceiptVoucherDetailVO> responseEntity = super.postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_FIND_RECEIPT_VOUCHER, voucherId, ReceiptVoucherDetailVO.class);
        ReceiptVoucherDetailVO receiptVoucherDetailVO = responseEntity.getBody();
        log.info("findReceiptVoucher() end - " + receiptVoucherDetailVO);
        return receiptVoucherDetailVO;
    }

    @Override
    public PaymentVoucherDetailVO findPaymentVoucher(Integer voucherId) {
        log.info("findPaymentVoucher() start - VoucherId: " + voucherId);
        ResponseEntity<PaymentVoucherDetailVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_FIND_PAYMENT_VOUCHER, voucherId, PaymentVoucherDetailVO.class);
        PaymentVoucherDetailVO PaymentVoucherDetailVO = responseEntity.getBody();
        log.info("findPaymentVoucher() end - " + PaymentVoucherDetailVO);
        return PaymentVoucherDetailVO;
    }

    @Override
    public void deleteVoucher(Integer voucherId) {
        log.info("deleteVoucher() start - VoucherId: " + voucherId);
        postForEntity(RequestUriConstant.CLIENT_URI_VOUCHER_DELETE_VOUCHER, voucherId, Void.class);
        log.info("deleteVoucher() end");
    }

    @Override
    public DownloadFileVO downloadImportTemplate(List<String> header) {
        log.info("downloadImportTemplate() start - header: " + header);
        ResponseEntity<DownloadFileVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_VOUCHER_DOWNLOAD_TEMPLATE, header, DownloadFileVO.class);
        DownloadFileVO file = responseEntity.getBody();
        log.info("downloadImportTemplate() end");
        return file;
    }

    @Override
    public Integer submitJournalVoucher(JournalVoucherDetailVO journalVoucherDetail) {
        log.info("submitJournalVoucher() start - " + journalVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_SUBMIT_JOURNAL_VOUCHER, journalVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("submitJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer approveJournalVoucher(JournalVoucherDetailVO journalVoucherDetail) {
        log.info("approveJournalVoucher() start - " + journalVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_APPROVE_JOURNAL_VOUCHER, journalVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("approveJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer rejectJournalVoucher(JournalVoucherDetailVO journalVoucherDetail) {
        log.info("rejectJournalVoucher() start - " + journalVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_REJECT_JOURNAL_VOUCHER, journalVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("rejectJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer verifyJournalVoucher(JournalVoucherDetailVO journalVoucherDetail) {
        log.info("verifyJournalVoucher() start - " + journalVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_VERIFY_JOURNAL_VOUCHER, journalVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("verifyJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer reverseJournalVoucher(JournalVoucherDetailVO journalVoucherDetail) {
        log.info("reverseJournalVoucher() start - " + journalVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_REVERSE_JOURNAL_VOUCHER, journalVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("reverseJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer deleteJournalVoucher(JournalVoucherDetailVO journalVoucherDetail) {
        log.info("deleteJournalVoucher() start - " + journalVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_DELETE_JOURNAL_VOUCHER, journalVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("deleteJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer submitReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO) {
        log.info("submitReceiptVoucher() start - prepareReceiptVoucherDetailVO: " + prepareReceiptVoucherDetailVO);
        ResponseEntity<Integer> responseEntity = postForEntity(RequestUriConstant.CLIENT_URI_VOUCHER_SUBMIT_VOUCHER,
            prepareReceiptVoucherDetailVO, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("submitReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer submitPaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail) {
        log.info("submitPaymentVoucher() start - " + paymentVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_SUBMIT_PAYMENT_VOUCHER, paymentVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("submitPaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer approveReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO) {
        log.info("approveReceiptVoucher() start - prepareReceiptVoucherDetailVO: " + prepareReceiptVoucherDetailVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_VOUCHER_APPROVE_RECEIPT_VOUCHER,
                prepareReceiptVoucherDetailVO, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("approveReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer approvePaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail) {
        log.info("approvePaymentVoucher() start - " + paymentVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_APPROVE_PAYMENT_VOUCHER, paymentVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("approvePaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer rejectReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO) {
        log.info("rejectPaymentVoucher() start - ReceiptVoucherDetailVO: " + prepareReceiptVoucherDetailVO);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_REJECT_RECEIPT_VOUCHER, prepareReceiptVoucherDetailVO, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("rejectPaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer rejectPaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail) {
        log.info("rejectPaymentVoucher() start - " + paymentVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_REJECT_PAYMENT_VOUCHER, paymentVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("rejectPaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer verifyReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO) {
        log.info("verifyReceiptVoucher() start - ReceiptVoucherDetailVO: " + prepareReceiptVoucherDetailVO);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_VERIFY_RECEIPT_VOUCHER, prepareReceiptVoucherDetailVO, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("verifyReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer verifyPaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail) {
        log.info("verifyPaymentVoucher() start - " + paymentVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_VERIFY_PAYMENT_VOUCHER, paymentVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("verifyPaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer reverseReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO) {
        log.info("reverseReceiptVoucher() start - ReceiptVoucherDetailVO: " + prepareReceiptVoucherDetailVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_VOUCHER_REVERSE_RECEIPT_VOUCHER,
                prepareReceiptVoucherDetailVO, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("reverseReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer reversePaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail) {
        log.info("reversePaymentVoucher() start - " + paymentVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_REVERSE_PAYMENT_VOUCHER, paymentVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("reversePaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer deleteReceiptVoucher(ReceiptVoucherDetailVO receiptVoucherDetailVO) {
        log.info("deleteReceiptVoucher() start - ReceiptVoucherDetailVO: " + receiptVoucherDetailVO);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_DELETE_RECEIPT_VOUCHER, receiptVoucherDetailVO, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("deleteReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Override
    public Integer deletePaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail) {
        log.info("deletePaymentVoucher() start - " + paymentVoucherDetail);
        ResponseEntity<Integer> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_VOUCHER_DELETE_PAYMENT_VOUCHER, paymentVoucherDetail, Integer.class);
        Integer voucherId = responseEntity.getBody();
        log.info("deletePaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }
}
