package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.dividend.entity.QDivScheduleCred;

public class DivScheduleCredPredicate {

    private static final QDivScheduleCred qDivScheduleCred = QDivScheduleCred.divScheduleCred;

    public static BooleanExpression findByScheduleId(Integer divScheduleId) {
        BooleanExpression scheduleExpression = null;
        if (divScheduleId != null && divScheduleId.intValue() > 0) {
            scheduleExpression = qDivScheduleCred.divSchdId.intValue().eq(divScheduleId);
        }
        BooleanExpression statusExpression = qDivScheduleCred.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(scheduleExpression, statusExpression);
    }
}
