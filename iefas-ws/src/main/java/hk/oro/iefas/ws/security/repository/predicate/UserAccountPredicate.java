/**
 * 
 */
package hk.oro.iefas.ws.security.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.organization.entity.QDivision;
import hk.oro.iefas.domain.organization.entity.QPost;
import hk.oro.iefas.domain.organization.entity.QUserProfile;
import hk.oro.iefas.domain.security.dto.SearchUserCriteriaDTO;
import hk.oro.iefas.domain.security.entity.QUserAccount;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class UserAccountPredicate {

    public static Predicate findByLoginName(String loginName) {
        return QUserAccount.userAccount.loginName.eq(loginName);
    }

    public static Predicate searchUserList(SearchUserCriteriaDTO criteria) {
        QUserAccount useraccount = QUserAccount.userAccount;
        QUserProfile userProfile = useraccount.userProfile;
        QDivision division = userProfile.division;
        QPost post = userProfile.post;
        BooleanExpression loginNameExpression = null;
        if (CommonUtils.isNotBlank(criteria.getLoginName())) {
            loginNameExpression = useraccount.loginName.likeIgnoreCase("%" + criteria.getLoginName() + "%");
        }

        BooleanExpression emailAddrExpression = null;
        if (CommonUtils.isNotBlank(criteria.getEmailAddress())) {
            emailAddrExpression = userProfile.emailAddress.likeIgnoreCase("%" + criteria.getEmailAddress() + "%");
        }

        BooleanExpression engNameExpression = null;
        if (CommonUtils.isNotBlank(criteria.getEnglishName())) {
            engNameExpression = userProfile.engName.likeIgnoreCase("%" + criteria.getEnglishName() + "%");
        }

        BooleanExpression divisionExpression = null;
        if (criteria.getDivisionId() != null) {
            divisionExpression = division.divisionId.eq(criteria.getDivisionId());
        }

        BooleanExpression postExpression = null;
        if (criteria.getPostId() != null) {
            postExpression = post.postId.eq(criteria.getPostId());
        }

        BooleanExpression statusExpression = null;
        if (CommonUtils.isNotBlank(criteria.getRecordStatus())) {
            statusExpression = useraccount.status.eq(criteria.getRecordStatus());
        }

        BooleanExpression divisionsExpression = null;
        if (CommonUtils.isNotEmpty(criteria.getDivisionIds())) {
            divisionsExpression = userProfile.division.divisionId.in(criteria.getDivisionIds());
        }
        return Expressions.allOf(loginNameExpression, emailAddrExpression, engNameExpression, divisionExpression,
            postExpression, statusExpression, divisionsExpression);
    }
}
