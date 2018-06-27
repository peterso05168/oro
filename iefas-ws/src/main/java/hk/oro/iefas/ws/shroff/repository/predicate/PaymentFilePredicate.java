package hk.oro.iefas.ws.shroff.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.shroff.dto.SearchPaymentFileDTO;
import hk.oro.iefas.domain.shroff.entity.QShrPaymentFile;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
public class PaymentFilePredicate {
    public static Predicate searchPaymentFile(SearchPaymentFileDTO criteria) {
        QShrPaymentFile paymentFile = QShrPaymentFile.shrPaymentFile;
        BooleanExpression paymentFileTypeExoression = null;
        if (criteria.getPaymentFileTypeId() != null) {
            paymentFileTypeExoression
                = paymentFile.paymentFileType.paymentFileTypeId.eq(criteria.getPaymentFileTypeId());
        }
        BooleanExpression paymentFileIdExpression = null;
        if (criteria.getPaymentFileId() != null) {
            paymentFileIdExpression = paymentFile.paymentFileId.eq(criteria.getPaymentFileId());
        }
        BooleanExpression currencyExpression = null;
        if (criteria.getCurrencyId() != null) {
            currencyExpression = paymentFile.curcy.curcyId.eq(criteria.getCurrencyId());
        }
        BooleanExpression dateExpression = null;
        if (criteria.getProcessDate() != null) {
            dateExpression = paymentFile.processDate.eq(criteria.getProcessDate());
        }
        BooleanExpression statusExpression = null;
        if (criteria.getStatus() != null) {
            statusExpression = paymentFile.status.eq(criteria.getStatus());
        } else {
            statusExpression = paymentFile.status.ne(CoreConstant.STATUS_DELETE);
        }
        return Expressions.allOf(paymentFileIdExpression, paymentFileTypeExoression, currencyExpression, dateExpression,
            statusExpression);
    }
}
