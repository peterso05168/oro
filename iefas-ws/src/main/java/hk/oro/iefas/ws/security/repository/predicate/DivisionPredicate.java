package hk.oro.iefas.ws.security.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.organization.dto.SearchDivisionCriteriaDTO;
import hk.oro.iefas.domain.organization.entity.QDivision;

/**
 * @version $Revision: 2733 $ $Date: 2018-05-30 16:15:49 +0800 (週三, 30 五月 2018) $
 * @author $Author: dante.fang $
 */
public class DivisionPredicate {

    public static Predicate findAll() {
        QDivision division = QDivision.division;
        BooleanExpression statusException = division.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        return statusException;
    }

    public static Predicate findByCriteria(SearchDivisionCriteriaDTO criteria) {
        QDivision division = QDivision.division;

        BooleanExpression idExpression = null;
        if (criteria.getDivisionId() != null) {
            idExpression = division.divisionId.eq(criteria.getDivisionId());
        }

        BooleanExpression nameExpression = null;
        if (criteria.getDivisionName() != null) {
            nameExpression = division.divisionName.like("%" + criteria.getDivisionName() + "%");
        }

        BooleanExpression statusExpression = division.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(idExpression, nameExpression, statusExpression);
    }
}
