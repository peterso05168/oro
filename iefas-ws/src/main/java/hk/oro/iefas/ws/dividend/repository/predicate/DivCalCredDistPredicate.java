package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.dividend.entity.QDivCalCredDist;

public class DivCalCredDistPredicate {

    private static final QDivCalCredDist Q_CAL_CRED_DIST = QDivCalCredDist.divCalCredDist;

    public static BooleanExpression findBydivCalId(Integer divCalId) {
        if (divCalId != null && divCalId.intValue() > 0) {
            return Q_CAL_CRED_DIST.divCalId.eq(divCalId).and(Q_CAL_CRED_DIST.status.eq(CoreConstant.STATUS_ACTIVE));
        }
        return null;
    }
}
