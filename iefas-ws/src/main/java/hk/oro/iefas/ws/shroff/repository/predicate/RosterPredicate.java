package hk.oro.iefas.ws.shroff.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.shroff.dto.SearchRosterDTO;
import hk.oro.iefas.domain.shroff.entity.QShrRoster;

/**
 * @version $Revision: 2809 $ $Date: 2018-06-01 11:25:16 +0800 (週五, 01 六月 2018) $
 * @author $Author: garrett.han $
 */
public class RosterPredicate {
    public static Predicate findByCriteria(SearchRosterDTO criteria) {
        QShrRoster roster = QShrRoster.shrRoster;
        BooleanExpression dateExpression = null;
        if (criteria.getOnDutyDate() != null) {
            dateExpression = roster.onDutyDate.eq(criteria.getOnDutyDate());
        }
        BooleanExpression groupNameExpression = null;
        BooleanExpression groupBNameExpression;
        BooleanExpression groupANameExpression;
        String groupName = criteria.getGroupName();
        if (groupName != null) {
            groupBNameExpression = roster.groupBPost.postTitle.eq(groupName);
            groupANameExpression = roster.groupAPost.postTitle.eq(groupName);
            groupNameExpression = groupBNameExpression.or(groupANameExpression);
        }
        BooleanExpression statusExpression = null;
        if (criteria.getStatus() != null) {
            statusExpression = roster.status.eq(criteria.getStatus());
        } else {
            statusExpression = roster.status.ne(CoreConstant.STATUS_DELETE);
        }
        return Expressions.allOf(dateExpression, groupNameExpression, statusExpression);
    }
}
