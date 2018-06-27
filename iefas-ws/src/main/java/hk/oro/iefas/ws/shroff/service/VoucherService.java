package hk.oro.iefas.ws.shroff.service;

import java.util.List;

import org.springframework.data.domain.Page;

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

/**
 * @version $Revision: 3122 $ $Date: 2018-06-13 17:34:56 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface VoucherService {

    Integer saveReceiptVoucher(ReceiptVoucherBasicInfoDTO receiptVoucherBasicInformation);

    Integer saveJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail);

    JournalVoucherDetailDTO findJournalVoucher(Integer voucherId);

    Page<JournalVoucherResultItemDTO> searchJournalVoucher(SearchDTO<JournalVoucherSearchDTO> searchDTO);

    Page<PaymentVoucherResultDTO> findPaymentVoucherByCriteria(SearchDTO<PaymentVoucherSearchDTO> criteria);

    Integer savePaymentVoucher(PaymentVoucherDetailDTO voucher);

    Integer saveReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail);

    ReceiptVoucherDetailDTO findReceiptVoucher(Integer voucherId);

    PaymentVoucherDetailDTO findPaymentVoucher(Integer voucherId);

    Page<ReceiptVoucherResultDTO> findReceiptVoucherByCriteria(SearchDTO<ReceiptVoucherSearchDTO> criteria);

    void deleteVoucher(Integer voucherId);

    DownloadFileDTO downloadImportTemplate(List<String> header);

    Integer submitJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail);

    Integer approveJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail);

    Integer rejectJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail);

    Integer verifyJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail);

    Integer reverseJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail);

    Integer deleteJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail);

    Integer submitPaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail);

    Integer approvePaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail);

    Integer rejectPaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail);

    Integer verifyPaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail);

    Integer reversePaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail);

    Integer deletePaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail);

    Integer submitReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail);

    Integer approveReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail);

    Integer verifyReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail);

    Integer reverseReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail);

    Integer rejectReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail);

    Integer deleteReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail);

}
