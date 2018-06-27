package hk.oro.iefas.ws.organization.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.organization.dto.SearchPostCriteriaDTO;
import hk.oro.iefas.domain.security.entity.QPostRole;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class PostRolePredicate {

    public static Predicate findByCriteria(SearchPostCriteriaDTO criteria) {
        QPostRole postRole = QPostRole.postRole;
        BooleanExpression postExpression = null;
        if (criteria.getPostId() != null && criteria.getPostId() != 0) {
            postExpression = postRole.post.postId.eq(criteria.getPostId());
        }

        BooleanExpression divisionExpression = null;
        if (criteria.getDivisionId() != null && criteria.getDivisionId() != 0) {
            divisionExpression = postRole.post.division.divisionId.eq(criteria.getDivisionId());
        }
        BooleanExpression rankExpression = null;
        if (criteria.getRankId() != null && criteria.getRankId() != 0) {
            rankExpression = postRole.post.rank.rankId.eq(criteria.getRankId());
        }

        BooleanExpression nameExpression = null;
        if (CommonUtils.isNotBlank(criteria.getPostTitle())) {
            nameExpression = postRole.post.postTitle.toLowerCase().contains(criteria.getPostTitle().toLowerCase());
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotBlank(criteria.getStatus())) {
            statusExpression = postRole.status.equalsIgnoreCase(criteria.getStatus());
        }
        return Expressions.allOf(postExpression, divisionExpression, rankExpression, nameExpression, statusExpression);
    }

    public static Predicate findByPostAndRole(Integer postId, Integer roleId, String status, String postStatus) {
        QPostRole postRole = QPostRole.postRole;
        BooleanExpression postExpression = null;
        if (postId != null && postId != 0) {
            postExpression = postRole.post.postId.eq(postId);
        }

        BooleanExpression roleExpression = null;
        if (roleId != null && roleId != 0) {
            roleExpression = postRole.role.roleId.eq(roleId);
        }

        BooleanExpression postRoleStatusExpression = null;
        if (CommonUtils.isNotBlank(status)) {
            postRoleStatusExpression = postRole.status.equalsIgnoreCase(status);
        }

        BooleanExpression postStatusExpression = null;
        if (CommonUtils.isNotBlank(postStatus)) {
            postStatusExpression = postRole.post.status.equalsIgnoreCase(postStatus);
        }

        return Expressions.allOf(postExpression, roleExpression, postRoleStatusExpression, postStatusExpression);
    }
}
