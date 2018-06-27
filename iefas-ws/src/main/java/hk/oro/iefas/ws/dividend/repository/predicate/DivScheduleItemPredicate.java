package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.dividend.entity.QDivScheduleItem;

public class DivScheduleItemPredicate {

    private static final QDivScheduleItem Q_DIV_SCHEDULE_ITEM = QDivScheduleItem.divScheduleItem;

    public static BooleanExpression findByAdjResultId(Integer adjResultId) {
        if (adjResultId != null && adjResultId.intValue() > 0) {
            return Q_DIV_SCHEDULE_ITEM.adjResultId.eq(adjResultId);
        }
        return null;
    }

    public static BooleanExpression findByDivScheduleId(Integer divScheduleId) {
        if (divScheduleId != null && divScheduleId.intValue() > 0) {
            return Q_DIV_SCHEDULE_ITEM.divSchedule.divSchdId.eq(divScheduleId)
                .and(Q_DIV_SCHEDULE_ITEM.status.eq(CoreConstant.STATUS_INACTIVE).not());
        }
        return null;
    }
}
