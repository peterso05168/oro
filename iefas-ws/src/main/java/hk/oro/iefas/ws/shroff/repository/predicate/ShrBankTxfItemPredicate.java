/**
 * 
 */
package hk.oro.iefas.ws.shroff.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.shroff.dto.SearchBankTransferItemDTO;
import hk.oro.iefas.domain.shroff.entity.QShrBankTxfItem;

/**
 * @version $Revision: 3032 $ $Date: 2018-06-11 18:14:28 +0800 (週一, 11 六月 2018) $
 * @author $Author: marvel.ma $
 */
public class ShrBankTxfItemPredicate {

    public static Predicate searchBankTransferItem(SearchBankTransferItemDTO criteria) {
        QShrBankTxfItem shrbanktxfitem = QShrBankTxfItem.shrBankTxfItem;

        BooleanExpression curcyCodeExpression = null;
        if (CommonUtils.isNotBlank(criteria.getCurrencyCode())) {
            curcyCodeExpression = shrbanktxfitem.curcyCode.eq(criteria.getCurrencyCode());
        }

        BooleanExpression txfDateExpression = null;
        if (criteria.getTransferDate() != null) {
            txfDateExpression = shrbanktxfitem.transferDate.eq(criteria.getTransferDate());
        }

        BooleanExpression voucherNumberExpression = null;
        if (CommonUtils.isNotBlank(criteria.getVoucherNumber())) {
            voucherNumberExpression = shrbanktxfitem.voucher.voucherNo.eq(criteria.getVoucherNumber());

        }

        if (CommonUtils.isNotBlank(criteria.getFromAccount())) {

        }

        if (CommonUtils.isNotBlank(criteria.getToAccount())) {

        }

        return Expressions.allOf(curcyCodeExpression, txfDateExpression, voucherNumberExpression);
    }

}
