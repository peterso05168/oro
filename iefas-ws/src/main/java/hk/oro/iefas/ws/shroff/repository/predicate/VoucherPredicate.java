package hk.oro.iefas.ws.shroff.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.BusinessUtils;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherSearchDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherSearchDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherSearchDTO;
import hk.oro.iefas.domain.shroff.entity.QShrVcrItem;
import hk.oro.iefas.domain.shroff.entity.QVoucher;

/**
 * @version $Revision: 3064 $ $Date: 2018-06-11 21:26:54 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
public class VoucherPredicate {

    public static Predicate searchReceiptVoucher(ReceiptVoucherSearchDTO receiptiVoucherSearchDTO) {
        QVoucher voucher = QVoucher.voucher;

        BooleanExpression statusExpression = voucher.status.notEqualsIgnoreCase(CoreConstant.VOUCHER_STATUS_DELETED);

        BooleanExpression voucherTypeCodeExpression = null;
        if (CommonUtils.isNotBlank(receiptiVoucherSearchDTO.getReceitpVoucherTypeCode())) {
            voucherTypeCodeExpression = voucher.voucherType.voucherTypeCode
                .equalsIgnoreCase(receiptiVoucherSearchDTO.getReceitpVoucherTypeCode());
        }

        BooleanExpression voucherNumberExpression = null;
        if (CommonUtils.isNotBlank(receiptiVoucherSearchDTO.getVoucherNo())) {
            voucherNumberExpression
                = voucher.voucherNo.likeIgnoreCase("%" + receiptiVoucherSearchDTO.getVoucherNo() + "%");
        }

        BooleanExpression voucherDateExpression = null;
        if (receiptiVoucherSearchDTO.getVoucherDate() != null) {
            voucherDateExpression = voucher.voucherDate.eq(receiptiVoucherSearchDTO.getVoucherDate());
        }

        BooleanExpression groupCodeExpression = null;
        if (CommonUtils.isNotBlank(receiptiVoucherSearchDTO.getGroupCode())) {
            groupCodeExpression = voucher.groupCode.equalsIgnoreCase(receiptiVoucherSearchDTO.getGroupCode());
        }

        BooleanExpression accountNumberExpression = null;
        if (CommonUtils.isNotBlank(receiptiVoucherSearchDTO.getCaseTypeCodeValue())
            && CommonUtils.isNotBlank(receiptiVoucherSearchDTO.getAccountTypeCodeValue())
            && CommonUtils.isNotBlank(receiptiVoucherSearchDTO.getCaseNoValue())
            && CommonUtils.isNotBlank(receiptiVoucherSearchDTO.getCaseYearValue())) {
            accountNumberExpression
                = voucher.shrVcrItems.any().caseAccount.caseAcNumber.eq(BusinessUtils.genAccountNumber(
                    receiptiVoucherSearchDTO.getCaseTypeCodeValue(), receiptiVoucherSearchDTO.getAccountTypeCodeValue(),
                    receiptiVoucherSearchDTO.getCaseNoValue(), receiptiVoucherSearchDTO.getCaseYearValue()));
            accountNumberExpression
                = voucher.shrVcrItems.any().caseAccount.caseAcNumber.eq(BusinessUtils.genAccountNumber(
                    receiptiVoucherSearchDTO.getCaseTypeCodeValue(), receiptiVoucherSearchDTO.getAccountTypeCodeValue(),
                    receiptiVoucherSearchDTO.getCaseNoValue(), receiptiVoucherSearchDTO.getCaseYearValue()));
            accountNumberExpression
                = voucher.shrVcrItems.any().caseAccount.caseAcNumber.equalsIgnoreCase(BusinessUtils.genAccountNumber(
                    receiptiVoucherSearchDTO.getCaseTypeCodeValue(), receiptiVoucherSearchDTO.getAccountTypeCodeValue(),
                    receiptiVoucherSearchDTO.getCaseNoValue(), receiptiVoucherSearchDTO.getCaseYearValue()));
        }

        BooleanExpression analysisCodeExpression = null;
        if (CommonUtils.isNotBlank(receiptiVoucherSearchDTO.getAnalysisCode())) {
            analysisCodeExpression
                = voucher.shrVcrItems.any().analysisCode.eq(receiptiVoucherSearchDTO.getAnalysisCode());
            analysisCodeExpression
                = voucher.shrVcrItems.any().analysisCode.eq(receiptiVoucherSearchDTO.getAnalysisCode());
            analysisCodeExpression
                = voucher.shrVcrItems.any().analysisCode.equalsIgnoreCase(receiptiVoucherSearchDTO.getAnalysisCode());
        }

        return Expressions.allOf(statusExpression, voucherTypeCodeExpression, voucherNumberExpression,
            voucherDateExpression, groupCodeExpression, accountNumberExpression, analysisCodeExpression);
    }

    public static Predicate searchJournalVoucher(JournalVoucherSearchDTO journalVoucherSearchDTO) {
        QVoucher voucher = QVoucher.voucher;

        BooleanExpression voucherTypeExpression = voucher.voucherType.voucherTypeCode.eq(ShroffConstant.VT_JOU);

        BooleanExpression voucherNumberExpression = null;
        if (CommonUtils.isNotBlank(journalVoucherSearchDTO.getVoucherNumber())) {
            voucherNumberExpression
                = voucher.voucherNo.likeIgnoreCase("%" + journalVoucherSearchDTO.getVoucherNumber() + "%");
        }

        BooleanExpression journalTypeIdExpression = null;
        if (journalVoucherSearchDTO.getJournalTypeId() != null) {
            journalTypeIdExpression = voucher.journalType.journalTypeId.eq(journalVoucherSearchDTO.getJournalTypeId());
        }

        BooleanExpression voucherDateExpression = null;
        if (journalVoucherSearchDTO.getVoucherDate() != null) {
            voucherDateExpression = voucher.voucherDate.eq(journalVoucherSearchDTO.getVoucherDate());
        }

        BooleanExpression groupCodeExpression = null;
        if (CommonUtils.isNotBlank(journalVoucherSearchDTO.getGroupCode())) {
            groupCodeExpression = voucher.groupCode.likeIgnoreCase("%" + journalVoucherSearchDTO.getGroupCode() + "%");
        }

        BooleanExpression analysisCodeExpression = null;
        if (CommonUtils.isNotBlank(journalVoucherSearchDTO.getAnalysisCode())) {
            analysisCodeExpression
                = voucher.shrVcrItems.any().analysisCode.eq(journalVoucherSearchDTO.getAnalysisCode());

        }

        BooleanExpression accountNumberExpression = null;
        if (CommonUtils.isNotBlank(journalVoucherSearchDTO.getAccountType())
            && CommonUtils.isNotBlank(journalVoucherSearchDTO.getCaseType())
            && journalVoucherSearchDTO.getCaseNumber() != null && journalVoucherSearchDTO.getCaseYear() != null) {
            accountNumberExpression = voucher.shrVcrItems.any().caseAccount.caseAcNumber.eq(BusinessUtils
                .genAccountNumber(journalVoucherSearchDTO.getCaseType(), journalVoucherSearchDTO.getAccountType(),
                    journalVoucherSearchDTO.getCaseNumber(), journalVoucherSearchDTO.getCaseYear()));
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotBlank(journalVoucherSearchDTO.getStatus())) {
            statusExpression = voucher.status.eq(journalVoucherSearchDTO.getStatus());
        }

        return Expressions.allOf(voucherTypeExpression, voucherNumberExpression, journalTypeIdExpression,
            voucherDateExpression, groupCodeExpression, analysisCodeExpression, accountNumberExpression,
            statusExpression);
    }

    public static Predicate searchPaymentVoucher(PaymentVoucherSearchDTO paymentVoucherSearchDTO) {
        QVoucher voucher = QVoucher.voucher;
        QShrVcrItem item = QShrVcrItem.shrVcrItem;

        BooleanExpression voucherTypeExpression = voucher.voucherType.voucherTypeCode.eq(ShroffConstant.VT_PAY);

        BooleanExpression voucherNumberExpression = null;
        if (CommonUtils.isNotEmpty(paymentVoucherSearchDTO.getVoucherNumber())) {
            voucherNumberExpression
                = voucher.voucherNo.likeIgnoreCase("%" + paymentVoucherSearchDTO.getVoucherNumber() + "%");
        }

        BooleanExpression accountNumberExpression = null;
        if (CommonUtils.isNotEmpty(paymentVoucherSearchDTO.getAccountNumber())) {
            accountNumberExpression = item.caseAccount.caseAcNumber.eq(paymentVoucherSearchDTO.getAccountNumber());
        }

        BooleanExpression analysisCodeExpression = null;
        if (CommonUtils.isNotEmpty(paymentVoucherSearchDTO.getAnalysisCode())) {
            analysisCodeExpression = voucher.groupCode.eq(paymentVoucherSearchDTO.getAnalysisCode());
        }

        BooleanExpression paymentDateExpression = null;
        if (paymentVoucherSearchDTO.getPaymentDate() != null) {
            paymentDateExpression = voucher.voucherDate.eq(paymentVoucherSearchDTO.getPaymentDate());
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotEmpty(paymentVoucherSearchDTO.getStatus())) {
            statusExpression = voucher.status.eq(paymentVoucherSearchDTO.getStatus());
        } else {
            statusExpression = voucher.status.notEqualsIgnoreCase(CoreConstant.VOUCHER_STATUS_DELETED);
        }

        return Expressions.allOf(voucherTypeExpression, voucherNumberExpression, accountNumberExpression,
            analysisCodeExpression, paymentDateExpression, statusExpression);
    }
}
