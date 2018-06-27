package hk.oro.iefas.ws.dividend.repository.predicate;

import java.util.Date;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.dividend.entity.QCaseFeeMain;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class CaseFeeMainPredicate {

    private static final QCaseFeeMain CASE_FEE_MAIN = QCaseFeeMain.caseFeeMain;

    public static BooleanExpression findByCaseFeeTypeId(Integer caseFeeTypeId) {
        return CASE_FEE_MAIN.status.eq(CoreConstant.STATUS_ACTIVE)
            .and(CASE_FEE_MAIN.caseFeeType.caseFeeTypeId.eq(caseFeeTypeId));
    }

    public static OrderSpecifier<Date> order() {
        return CASE_FEE_MAIN.periodFrom.asc();
    }

    public static BooleanExpression findByCaseFeeTypeNameAndDate(String caseFeeTypeName, Date date) {
        BooleanExpression statusExpression = CASE_FEE_MAIN.status.eq(CoreConstant.STATUS_ACTIVE);

        BooleanExpression caseFeeTypeExpression = null;
        if (caseFeeTypeName != null) {
            caseFeeTypeExpression = CASE_FEE_MAIN.caseFeeType.caseFeeTypeItem.eq(caseFeeTypeName);
        }

        BooleanExpression dateExpression = null;
        if (date != null) {
            dateExpression = CASE_FEE_MAIN.periodFrom.before(date).and(CASE_FEE_MAIN.periodTo.after(date));
        }

        return Expressions.allOf(statusExpression, caseFeeTypeExpression, dateExpression);
    }
}
