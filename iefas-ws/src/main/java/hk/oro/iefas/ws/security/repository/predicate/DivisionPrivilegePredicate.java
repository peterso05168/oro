package hk.oro.iefas.ws.security.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeCriteriaDTO;
import hk.oro.iefas.domain.security.entity.QDivisionPrivilege;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class DivisionPrivilegePredicate {

    public static Predicate findByCriteria(SearchPrivilegeCriteriaDTO criteria) {
        QDivisionPrivilege dp = QDivisionPrivilege.divisionPrivilege;

        BooleanExpression idExpression = null;
        if (criteria.getDivisionId() != null) {
            idExpression = dp.division.divisionId.eq(criteria.getDivisionId());
        }
        BooleanExpression statusExpression = dp.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(idExpression, statusExpression);
    }

    public static Predicate findByDivisionAndPrivilege(SearchPrivilegeCriteriaDTO criteria) {
        QDivisionPrivilege dp = QDivisionPrivilege.divisionPrivilege;

        BooleanExpression divisionExpression = null;
        if (criteria.getDivisionId() != null) {
            divisionExpression = dp.division.divisionId.eq(criteria.getDivisionId());
        }

        BooleanExpression privilegeExpression = null;
        if (criteria.getPrivilegeId() != null) {
            privilegeExpression = dp.privilege.privilegeId.eq(criteria.getPrivilegeId());
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotBlank(criteria.status)) {
            statusExpression = dp.status.equalsIgnoreCase(criteria.status);
        }

        return Expressions.allOf(divisionExpression, statusExpression, privilegeExpression);
    }
}
