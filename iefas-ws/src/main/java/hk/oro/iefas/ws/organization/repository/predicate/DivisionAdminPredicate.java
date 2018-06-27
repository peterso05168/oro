package hk.oro.iefas.ws.organization.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.organization.dto.SearchDivisionCriteriaDTO;
import hk.oro.iefas.domain.organization.entity.QDivisionAdmin;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class DivisionAdminPredicate {

    public static Predicate findByPost(Integer postId) {
        QDivisionAdmin divisionAdmin = QDivisionAdmin.divisionAdmin;
        BooleanExpression idExpression = divisionAdmin.post.postId.eq(postId);

        BooleanExpression statusExpression = divisionAdmin.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(idExpression, statusExpression);
    }

    public static Predicate findByDivision(Integer divisionId) {
        QDivisionAdmin divisionAdmin = QDivisionAdmin.divisionAdmin;

        BooleanExpression idExpression = null;
        if (divisionId != null) {
            idExpression = divisionAdmin.division.divisionId.eq(divisionId);
        }

        BooleanExpression statusExpression = divisionAdmin.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(idExpression, statusExpression);
    }

    public static Predicate findByCriteria(SearchDivisionCriteriaDTO criteria) {
        QDivisionAdmin divisionAdmin = QDivisionAdmin.divisionAdmin;

        BooleanExpression divisionExpression = null;
        if (criteria.getDivisionId() != null) {
            divisionExpression = divisionAdmin.division.divisionId.eq(criteria.getDivisionId());
        }

        BooleanExpression postExpression = null;
        if (criteria.getPostId() != null) {
            postExpression = divisionAdmin.post.postId.eq(criteria.getPostId());
        }

        // BooleanExpression statusExpression = divisionAdmin.status.eq(CoreConstant.STATUS_ACTIVE);

        return Expressions.allOf(divisionExpression, postExpression);
    }
}
