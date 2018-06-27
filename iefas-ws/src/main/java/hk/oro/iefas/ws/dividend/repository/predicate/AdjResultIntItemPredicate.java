package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.adjudication.entity.QAdjResultIntItem;

public class AdjResultIntItemPredicate {

    public final static QAdjResultIntItem Q_ADJ_RESULT_INT_ITEM = QAdjResultIntItem.adjResultIntItem;

    public static BooleanExpression findByadjIntTrialAdjResultId(Integer adjIntTrialAdjResultId) {
        BooleanExpression adjIntTrialAdjResultIdExpression = null;
        if (adjIntTrialAdjResultId != null && adjIntTrialAdjResultId.intValue() > 0) {
            adjIntTrialAdjResultIdExpression = Q_ADJ_RESULT_INT_ITEM.adjIntTrAdjId.eq(adjIntTrialAdjResultId);
        }
        BooleanExpression statusExpression = Q_ADJ_RESULT_INT_ITEM.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(adjIntTrialAdjResultIdExpression, statusExpression);
    }

}
