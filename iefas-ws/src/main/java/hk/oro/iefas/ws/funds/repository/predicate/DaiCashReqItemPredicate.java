package hk.oro.iefas.ws.funds.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.funds.entity.QDaiCashReqItem;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class DaiCashReqItemPredicate {

    private static final QDaiCashReqItem DAI_CASH_REQ_ITEM = QDaiCashReqItem.daiCashReqItem;

    public static BooleanExpression isExistingPredicate(Integer investTypeId, Integer cashRequirementItemTypeId,
        Date investDate) {
        BooleanExpression dateBetweenExp = DAI_CASH_REQ_ITEM.daiCashReq.investDate
            .between(DateUtils.getStartDate(investDate), DateUtils.getEndDate(investDate));
        BooleanExpression statusExpression = DAI_CASH_REQ_ITEM.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return DAI_CASH_REQ_ITEM.investType.investmentTypeId.eq(investTypeId)
            .and(DAI_CASH_REQ_ITEM.cashReqItem.cashRqmtItemTypeId.eq(cashRequirementItemTypeId)).and(dateBetweenExp)
            .and(statusExpression);
    }

    public static BooleanExpression isExistingPredicate(Integer investTypeId, Integer cashRequirementItemTypeId,
        Integer dailyCashRqmtId) {
        BooleanExpression statusExpression = DAI_CASH_REQ_ITEM.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return DAI_CASH_REQ_ITEM.investType.investmentTypeId.eq(investTypeId)
            .and(DAI_CASH_REQ_ITEM.cashReqItem.cashRqmtItemTypeId.eq(cashRequirementItemTypeId))
            .and(DAI_CASH_REQ_ITEM.daiCashReq.dailyCashRqmtId.eq(dailyCashRqmtId)).and(statusExpression);
    }
}
