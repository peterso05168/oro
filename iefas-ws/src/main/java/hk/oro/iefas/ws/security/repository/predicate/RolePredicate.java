package hk.oro.iefas.ws.security.repository.predicate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.organization.dto.SearchRoleCriteriaDTO;
import hk.oro.iefas.domain.security.entity.QRole;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class RolePredicate {

    private static final QRole ROLE = QRole.role;

    public static Predicate findByCriteria(SearchRoleCriteriaDTO criteria) {

        BooleanExpression idExpression = null;
        if (criteria.getDivisionId() != null) {
            idExpression = ROLE.division.divisionId.eq(criteria.getDivisionId());
        }

        BooleanExpression nameExpression = null;
        if (CommonUtils.isNotBlank(criteria.getRoleName())) {
            nameExpression = ROLE.roleName.toLowerCase().contains(criteria.getRoleName().toLowerCase());
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotBlank(criteria.getStatus())) {
            statusExpression = ROLE.status.equalsIgnoreCase(criteria.getStatus());
        }

        return Expressions.allOf(idExpression, nameExpression, statusExpression);
    }

    public static Predicate findAll() {
        BooleanExpression statusException = ROLE.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return statusException;
    }

    public static OrderSpecifier<Integer> order() {
        return ROLE.roleId.asc();
    }

}
