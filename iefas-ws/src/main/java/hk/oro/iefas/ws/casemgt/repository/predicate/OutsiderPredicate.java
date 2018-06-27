package hk.oro.iefas.ws.casemgt.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.casemgt.dto.SearchOutsiderDetailCriteriaDTO;
import hk.oro.iefas.domain.casemgt.entity.QOutsider;

/**
 * @version $Revision: 2674 $ $Date: 2018-05-28 18:21:29 +0800 (週一, 28 五月 2018) $
 * @author $Author: garrett.han $
 */
public class OutsiderPredicate {
    private static QOutsider OUTSIDER = QOutsider.outsider;

    public static Predicate findByCriteria(SearchOutsiderDetailCriteriaDTO criteria) {
        BooleanExpression outsiderNameExpression = null;
        if (criteria.getOutsiderName() != null) {
            outsiderNameExpression = OUTSIDER.outsiderName.eq(criteria.getOutsiderName());
        }
        BooleanExpression outsiderStatusExpression;
        if (criteria.getStatus() != null) {
            outsiderStatusExpression = OUTSIDER.status.eq(criteria.getStatus());
        } else {
            outsiderStatusExpression = OUTSIDER.status.ne(CoreConstant.STATUS_DELETE);
        }
        BooleanExpression outsiderTypeIdExpression = null;
        if (criteria.getOutsiderTypeId() != null) {
            outsiderTypeIdExpression = OUTSIDER.outsiderType.outsiderTypeId.eq(criteria.getOutsiderTypeId());
        }
        return Expressions.allOf(outsiderNameExpression, outsiderStatusExpression, outsiderTypeIdExpression);

    }
}
