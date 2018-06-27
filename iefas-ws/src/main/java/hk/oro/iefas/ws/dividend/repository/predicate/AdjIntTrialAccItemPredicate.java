package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.adjudication.entity.QAdjIntTrialAccItem;

public class AdjIntTrialAccItemPredicate {

    private static final QAdjIntTrialAccItem Q_ADJ_INT_TRIAL_ACC_ITEM = QAdjIntTrialAccItem.adjIntTrialAccItem;

    public static BooleanExpression findByIntTrAdjId(Integer intTrAdjId) {
        BooleanExpression adjIntTrialAdjResIdExpression = null;
        if (intTrAdjId != null && intTrAdjId.intValue() > 0) {
            adjIntTrialAdjResIdExpression = Q_ADJ_INT_TRIAL_ACC_ITEM.adjIntTrAdjId.eq(intTrAdjId);
        }
        BooleanExpression statusExpression
            = Q_ADJ_INT_TRIAL_ACC_ITEM.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(adjIntTrialAdjResIdExpression, statusExpression);
    }
}
