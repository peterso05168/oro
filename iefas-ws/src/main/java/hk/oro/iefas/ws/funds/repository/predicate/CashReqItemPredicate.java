package hk.oro.iefas.ws.funds.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.funds.entity.QCashReqItem;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class CashReqItemPredicate {

    private static final QCashReqItem CASH_REQ_ITEM = QCashReqItem.cashReqItem;

    public static BooleanExpression findByNameAndInvestTypeCode(String cashRqmtItemTypeName, String investTypeCode) {
        return CASH_REQ_ITEM.investType.investTypeCode.equalsIgnoreCase(investTypeCode)
            .and(CASH_REQ_ITEM.investType.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE))
            .and(CASH_REQ_ITEM.cashRqmtItemTypeName.equalsIgnoreCase(cashRqmtItemTypeName))
            .and(CASH_REQ_ITEM.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE));
    }
}
