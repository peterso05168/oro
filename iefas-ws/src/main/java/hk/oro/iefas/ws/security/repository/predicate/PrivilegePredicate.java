package hk.oro.iefas.ws.security.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.domain.security.dto.SearchPrivilegeCriteriaDTO;
import hk.oro.iefas.domain.security.entity.QDivisionPrivilege;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class PrivilegePredicate {

    public static Predicate findByCriteria(SearchPrivilegeCriteriaDTO criteria) {
        QDivisionPrivilege p = QDivisionPrivilege.divisionPrivilege;

        BooleanExpression idExpression = null;
        if (criteria.getDivisionId() != null) {
            idExpression = p.division.divisionId.eq(criteria.getDivisionId());
        }

        return Expressions.allOf(idExpression);
    }

}
