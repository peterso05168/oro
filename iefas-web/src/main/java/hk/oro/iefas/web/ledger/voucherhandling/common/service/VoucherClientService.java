package hk.oro.iefas.web.ledger.voucherhandling.common.service;

import java.util.List;

import hk.oro.iefas.domain.report.DownloadFileVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.shroff.vo.JournalVoucherDetailVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherDetailVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherBasicInformationVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherDetailVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherResultVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherSearchVO;

/**
 * @version $Revision: 3122 $ $Date: 2018-06-13 17:34:56 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface VoucherClientService {

    Integer saveReceiptVoucher(ReceiptVoucherBasicInformationVO receiptVoucherBasicInformation);

    ResultPageVO<List<ReceiptVoucherResultVO>> findByCriteria(SearchVO<ReceiptVoucherSearchVO> searchVO);

    Integer saveReceiptVoucher(ReceiptVoucherDetailVO receiptVoucherDetailVO);

    ReceiptVoucherDetailVO findReceiptVoucher(Integer voucherId);

    Integer saveJournalVoucher(JournalVoucherDetailVO journalVoucherDetail);

    JournalVoucherDetailVO findJournalVoucher(Integer voucherId);

    PaymentVoucherDetailVO findPaymentVoucher(Integer paymentVoucherId);

    Integer savePaymentVoucher(PaymentVoucherDetailVO paymentVoucherVO);

    void deleteVoucher(Integer voucherId);

    DownloadFileVO downloadImportTemplate(List<String> header);

    Integer submitJournalVoucher(JournalVoucherDetailVO journalVoucherDetail);

    Integer approveJournalVoucher(JournalVoucherDetailVO journalVoucherDetail);

    Integer rejectJournalVoucher(JournalVoucherDetailVO journalVoucherDetail);

    Integer verifyJournalVoucher(JournalVoucherDetailVO journalVoucherDetail);

    Integer reverseJournalVoucher(JournalVoucherDetailVO journalVoucherDetail);

    Integer deleteJournalVoucher(JournalVoucherDetailVO journalVoucherDetail);

    Integer submitPaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail);

    Integer approvePaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail);

    Integer rejectPaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail);

    Integer verifyPaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail);

    Integer reversePaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail);

    Integer deletePaymentVoucher(PaymentVoucherDetailVO paymentVoucherDetail);

    Integer submitReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO);

    Integer approveReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO);

    Integer verifyReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO);

    Integer reverseReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO);

    Integer rejectReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO);

    Integer deleteReceiptVoucher(ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO);

}
