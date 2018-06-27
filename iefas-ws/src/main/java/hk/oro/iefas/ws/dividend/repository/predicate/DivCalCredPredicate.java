package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.dividend.entity.QDivCalCred;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
public class DivCalCredPredicate {

    private static final QDivCalCred DIV_CAL_CRED = QDivCalCred.divCalCred;

    public static BooleanExpression findByDivCal(Integer divCalId) {
        BooleanExpression statusPredicate = DIV_CAL_CRED.status.eq(CoreConstant.STATUS_ACTIVE);

        BooleanExpression divCalPredicate = null;
        if (divCalId != null) {
            divCalPredicate = DIV_CAL_CRED.divCalId.eq(divCalId);
        }
        return Expressions.allOf(divCalPredicate, statusPredicate);
    }

    public static BooleanExpression findByCredTypeId(Integer credTypeId) {
        if (credTypeId != null && credTypeId.intValue() > 0) {
            return DIV_CAL_CRED.credType.creditorTypeId.eq(credTypeId)
                .and(DIV_CAL_CRED.status.eq(CoreConstant.STATUS_ACTIVE));
        }
        return null;
    }
}
