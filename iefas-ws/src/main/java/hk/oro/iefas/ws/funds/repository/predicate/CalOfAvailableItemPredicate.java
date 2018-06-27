package hk.oro.iefas.ws.funds.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.funds.entity.QCalOfAvailableItem;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class CalOfAvailableItemPredicate {

    private static final QCalOfAvailableItem CAL_OF_AVAILABLE_ITEM = QCalOfAvailableItem.calOfAvailableItem;

    public static BooleanExpression isExistingPredicate(Integer investTypeId, Integer avaItemTypeId,
        Integer CalculationOfFundsAvailableId) {
        BooleanExpression statusExpression
            = (CAL_OF_AVAILABLE_ITEM.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE));
        return CAL_OF_AVAILABLE_ITEM.fundsAvailableItemType.fundsAvailableItemTypeId.eq(avaItemTypeId)
            .and(CAL_OF_AVAILABLE_ITEM.investmentType.investmentTypeId.eq(investTypeId))
            .and(
                CAL_OF_AVAILABLE_ITEM.fundsAvailableItemType.fundsAvailableItemTypeId.eq(CalculationOfFundsAvailableId))
            .and(statusExpression);
    }
}
