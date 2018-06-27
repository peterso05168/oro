package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.adjudication.entity.QAppAdjResultItem;

public class AppAdjResultItemPredicate {

    private final static QAppAdjResultItem Q_APP_ADJ_RESULT_ITEM = QAppAdjResultItem.appAdjResultItem;

    public static BooleanExpression findByAdjResultId(Integer adjResultId) {
        BooleanExpression adjResultExpression = null;
        if (adjResultId != null && adjResultId.intValue() > 0) {
            adjResultExpression = Q_APP_ADJ_RESULT_ITEM.adjResultId.eq(adjResultId);
        }
        BooleanExpression statusExpression = Q_APP_ADJ_RESULT_ITEM.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(adjResultExpression, statusExpression);
    }

    public static BooleanExpression findByAdjResultIdAndAdjType(Integer adjResultId, String type) {
        BooleanExpression adjResultExpression = null;
        if (adjResultId != null && adjResultId.intValue() > 0) {
            adjResultExpression = Q_APP_ADJ_RESULT_ITEM.adjResultId.eq(adjResultId);
        }
        BooleanExpression typeExpression = null;
        if (type != null) {
            typeExpression = Q_APP_ADJ_RESULT_ITEM.adjType.adjTypeDesc.equalsIgnoreCase(type);
        }
        BooleanExpression statusExpression = Q_APP_ADJ_RESULT_ITEM.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return Expressions.allOf(adjResultExpression, typeExpression, statusExpression);
    }
}
