package hk.oro.iefas.ws.casemgt.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.casemgt.entity.QCaseAccountType;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class CaseAccountTypePredicate {

    private static final QCaseAccountType CASE_AC_TYPE = QCaseAccountType.caseAccountType;

    public static BooleanExpression findAll() {
        return CASE_AC_TYPE.status.eq(CoreConstant.STATUS_ACTIVE);
    }

    public static OrderSpecifier<String> order() {
        return CASE_AC_TYPE.caseAcTypeName.asc();
    }
}
