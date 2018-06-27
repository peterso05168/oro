package hk.oro.iefas.ws.shroff.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.shroff.dto.SearchChequeFileDTO;
import hk.oro.iefas.domain.shroff.dto.SearchIncomingChequeDTO;
import hk.oro.iefas.domain.shroff.dto.SearchOutgoingChequeDTO;
import hk.oro.iefas.domain.shroff.entity.QShrCheque;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
public class ChequePredicate {

    public static Predicate findIncomingChequeByCriteria(SearchIncomingChequeDTO criteria) {
        QShrCheque shrCheque = QShrCheque.shrCheque;
        BooleanExpression chequeNumberExpression = null;
        if (criteria.getChequeNumber() != null) {
            chequeNumberExpression = shrCheque.chequeNo.eq(criteria.getChequeNumber().toString());
        }
        BooleanExpression receiveDataExpression = null;
        if (criteria.getReceiveDate() != null) {
            receiveDataExpression = shrCheque.receiptDate.eq(criteria.getReceiveDate());
        }
        BooleanExpression bankCodeExpression = null;
        if (criteria.getBankCode() != null) {
            bankCodeExpression = shrCheque.bankCode.eq(criteria.getBankCode());
        }
        BooleanExpression caseOfficerExpression = null;
        if (criteria.getCaseOffice() != null) {
            caseOfficerExpression = shrCheque.caseInfo.post.postTitle.eq(criteria.getCaseOffice());
        }
        BooleanExpression statusExpression = null;
        if (criteria.getStatus() != null) {
            statusExpression = shrCheque.status.eq(criteria.getStatus());
        } else {
            statusExpression = shrCheque.status.ne(CoreConstant.STATUS_DELETE);
        }
        BooleanExpression typeExpression;
        typeExpression = shrCheque.chequeTypeId.eq(ShroffConstant.CQ_INCOMING);

        BooleanExpression currencyExpression = null;
        if (criteria.getCurrencyId() != null) {
            currencyExpression = shrCheque.curcy.curcyId.eq(criteria.getCurrencyId());
        }
        return Expressions.allOf(chequeNumberExpression, receiveDataExpression, bankCodeExpression,
            caseOfficerExpression, statusExpression, currencyExpression, typeExpression);
    }

    public static Predicate findOutgoingChequeByCriteria(SearchOutgoingChequeDTO criteria) {
        QShrCheque shrCheque = QShrCheque.shrCheque;

        BooleanExpression typeExpression = shrCheque.chequeTypeId.eq(ShroffConstant.CQ_OUTGOING);
        BooleanExpression typeMExpression = shrCheque.chequeTypeId.eq(ShroffConstant.CQ_COM_OUTGOING);
        typeExpression = typeExpression.or(typeMExpression);

        BooleanExpression chequeNumberExpression = null;
        if (criteria.getChequeNumber() != null) {
            chequeNumberExpression = shrCheque.chequeNo.eq(criteria.getChequeNumber().toString());
        }
        BooleanExpression voucherNumberExpression = null;
        if (criteria.getVoucherNumber() != null) {
            voucherNumberExpression = shrCheque.shrVcrInfo.voucherNo.eq(criteria.getVoucherNumber());
        }
        BooleanExpression chequeDateExpression = null;
        if (criteria.getChequeDate() != null) {
            chequeDateExpression = shrCheque.chequeDate.eq(criteria.getChequeDate());
        }
        BooleanExpression payeeExpression = null;
        if (criteria.getPayee() != null) {
            payeeExpression = shrCheque.payee.eq(criteria.getPayee());
        }
        BooleanExpression currencyExpression = null;
        if (criteria.getCurrencyId() != null) {
            currencyExpression = shrCheque.curcy.curcyId.eq(criteria.getCurrencyId());
        }
        BooleanExpression statusExpression;
        if (criteria.getStatus() != null) {
            statusExpression = shrCheque.status.eq(criteria.getStatus());
        } else {
            statusExpression = shrCheque.status.ne(CoreConstant.STATUS_DELETE);
            statusExpression = statusExpression.and(shrCheque.status.ne(CoreConstant.CHEQUE_STATUS_COMBINED));
        }

        return Expressions.allOf(typeExpression, chequeNumberExpression, chequeDateExpression, voucherNumberExpression,
            payeeExpression, currencyExpression, statusExpression);
    }

    public static Predicate getOutgoingChequeListByParentId(Integer parentId) {
        QShrCheque shrCheque = QShrCheque.shrCheque;
        BooleanExpression parentIdExpression = null;
        if (parentId != null) {
            parentIdExpression = shrCheque.parentChequeId.eq(parentId);
        }
        return Expressions.allOf(parentIdExpression);
    }

    public static Predicate findOutgoingChequeForBulkReversal(Date cutOffDate) {
        QShrCheque shrCheque = QShrCheque.shrCheque;
        BooleanExpression chequeTypeExpression = shrCheque.chequeTypeId.eq(ShroffConstant.CQ_OUTGOING);
        BooleanExpression cutOffDateExpression = shrCheque.chequeDate.before(DateUtils.getEndDate(cutOffDate));
        BooleanExpression statusExpression = shrCheque.status.eq(CoreConstant.CHEQUE_STATUS_GENERATED);
        return Expressions.allOf(chequeTypeExpression, cutOffDateExpression, statusExpression);
    }

    public static Predicate searchChequeForChequeFile(SearchChequeFileDTO criteria) {
        QShrCheque cheque = QShrCheque.shrCheque;
        BooleanExpression voucherNumberExpression = null;
        if (criteria.getVoucherNumber() != null) {
            voucherNumberExpression = cheque.shrVcrInfo.voucherNo.eq(criteria.getVoucherNumber());
        }
        BooleanExpression chequeDateExpression = null;
        if (criteria.getChequeDate() != null) {
            chequeDateExpression = cheque.chequeDate.eq(criteria.getChequeDate());
        }
        BooleanExpression currencyExpression = null;
        if (criteria.getCurrencyId() != null) {
            currencyExpression = cheque.curcy.curcyId.eq(criteria.getCurrencyId());
        }
        return Expressions.allOf(voucherNumberExpression, chequeDateExpression, currencyExpression);
    }
}
