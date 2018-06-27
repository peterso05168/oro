package hk.oro.iefas.ws.shroff.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.counter.entity.QCtrCaseDeposit;
import hk.oro.iefas.domain.shroff.dto.SearchReceiveDepositDTO;

/**
 * @version $Revision: 2674 $ $Date: 2018-05-28 18:21:29 +0800 (週一, 28 五月 2018) $
 * @author $Author: garrett.han $
 */
public class ReceiveDepositPredicate {
    public static Predicate searchReceiveDepositList(SearchReceiveDepositDTO criteria) {
        QCtrCaseDeposit deposit = QCtrCaseDeposit.ctrCaseDeposit;
        BooleanExpression depositNumberExpression = null;
        if (criteria.getDepositNumber() != null) {
            depositNumberExpression = deposit.depositNo.eq(criteria.getDepositNumber());
        }
        BooleanExpression dateExpression = null;
        if (criteria.getDate() != null) {
            dateExpression = deposit.receiveDate.eq(criteria.getDate());
        }
        BooleanExpression receiptNumberExpression = null;
        if (criteria.getReceiptNumber() != null) {
            receiptNumberExpression = deposit.shrReceipt.receiptNo.eq(criteria.getReceiptNumber());
        }
        BooleanExpression payerNameExpression = null;
        if (criteria.getPayerName() != null) {
            payerNameExpression = deposit.payer.eq(criteria.getPayerName());
        }
        BooleanExpression currencyExpression = null;
        if (criteria.getCurrencyId() != null) {
            currencyExpression = deposit.curcy.curcyId.eq(criteria.getCurrencyId());
        }
        BooleanExpression statusExpression;
        if (criteria.getStatus() != null) {
            statusExpression = deposit.status.eq(criteria.getStatus());
        } else {
            statusExpression = deposit.status.ne(CoreConstant.STATUS_DELETE);
        }
        return Expressions.allOf(depositNumberExpression, dateExpression, receiptNumberExpression, payerNameExpression,
            currencyExpression, statusExpression);
    }
}
