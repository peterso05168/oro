package hk.oro.iefas.ws.dividend.repository.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.dividend.entity.QDivCaseFeeCal;

public class DivCaseFeeCalPredicate {

    private static final QDivCaseFeeCal DIV_CASE_FEE_CAL = QDivCaseFeeCal.divCaseFeeCal;

    public static BooleanExpression exists(Integer divCalId, String caseFeeTypeName) {
        BooleanExpression statusPredicate = DIV_CASE_FEE_CAL.status.eq(CoreConstant.STATUS_ACTIVE);

        BooleanExpression divCalPredicate = null;
        if (divCalId != null) {
            divCalPredicate = DIV_CASE_FEE_CAL.divCalId.eq(divCalId);
        }

        BooleanExpression caseFeeTypePredicate = null;
        if (caseFeeTypeName != null) {
            caseFeeTypePredicate = DIV_CASE_FEE_CAL.divCaseFeeType.caseFeeTypeItem.eq(caseFeeTypeName);
        }

        return Expressions.allOf(divCalPredicate, caseFeeTypePredicate, statusPredicate);
    }
}
