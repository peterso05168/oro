package hk.oro.iefas.ws.organization.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.organization.dto.SearchPostCriteriaDTO;
import hk.oro.iefas.domain.organization.entity.QPost;

/**
 * @version $Revision: 3253 $ $Date: 2018-06-21 16:13:28 +0800 (週四, 21 六月 2018) $
 * @author $Author: dante.fang $
 */
public class PostPredicate {

    public static Predicate findByCriteria(SearchPostCriteriaDTO criteria) {
        QPost post = QPost.post;
        BooleanExpression divisionExpression = null;
        if (criteria.getDivisionId() != null && criteria.getDivisionId() != 0) {
            divisionExpression = post.division.divisionId.eq(criteria.getDivisionId());
        }
        BooleanExpression rankExpression = null;
        if (criteria.getRankId() != null && criteria.getRankId() != 0) {
            rankExpression = post.rank.rankId.eq(criteria.getRankId());
        }

        BooleanExpression nameExpression = null;
        if (CommonUtils.isNotBlank(criteria.getPostTitle())) {
            nameExpression = post.postTitle.toLowerCase().contains(criteria.getPostTitle().toLowerCase());
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotBlank(criteria.getStatus())) {
            statusExpression = post.status.equalsIgnoreCase(criteria.getStatus());
        }
        return Expressions.allOf(divisionExpression, rankExpression, nameExpression, statusExpression);
    }

    public static Predicate findByDivisionId(Integer divisionId) {
        return QPost.post.division.divisionId.eq(divisionId);
    }

    public static Predicate findByDivisionIds(Integer... divisionIds) {
        return QPost.post.division.divisionId.in(divisionIds);
    }
}
