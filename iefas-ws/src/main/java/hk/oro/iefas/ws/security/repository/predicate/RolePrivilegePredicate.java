package hk.oro.iefas.ws.security.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeCriteriaDTO;
import hk.oro.iefas.domain.security.entity.QRolePrivilege;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class RolePrivilegePredicate {

    public static Predicate findByCriteria(SearchPrivilegeCriteriaDTO criteria) {
        QRolePrivilege rolePrivilege = QRolePrivilege.rolePrivilege;

        BooleanExpression roleExpression = null;
        if (criteria.getRoleId() != null) {
            roleExpression = rolePrivilege.role.roleId.eq(criteria.getRoleId());
        }

        BooleanExpression privilegeExpression = null;
        if (criteria.getPrivilegeId() != null) {
            privilegeExpression = rolePrivilege.privilege.privilegeId.eq(criteria.getPrivilegeId());
        }

        BooleanExpression statusException = null;
        if (CommonUtils.isNotBlank(criteria.getStatus())) {
            statusException = rolePrivilege.status.equalsIgnoreCase(criteria.getStatus());
        }

        return Expressions.allOf(roleExpression, privilegeExpression, statusException);
    }

}
