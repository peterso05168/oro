package hk.oro.iefas.ws.casemgt.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.casemgt.entity.QCaseType;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class CaseTypePredicate {

    private static final QCaseType CASE_TYPE = QCaseType.caseType;

    public static BooleanExpression findAll() {
        return CASE_TYPE.status.eq(CoreConstant.STATUS_ACTIVE);
    }

    public static BooleanExpression findByCaseTypeCode(String caseTypeCode) {
        return CASE_TYPE.status.eq(CoreConstant.STATUS_ACTIVE).and(CASE_TYPE.caseTypeCode.eq(caseTypeCode));
    }

    public static BooleanExpression findByDividendRequired(String divRequired) {
        return CASE_TYPE.status.eq(CoreConstant.STATUS_ACTIVE).and(CASE_TYPE.divRequired.eq(divRequired));
    }

    public static OrderSpecifier<String> order() {
        return CASE_TYPE.caseTypeDesc.asc();
    }
}
