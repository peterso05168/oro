/**
 * 
 */
package hk.oro.iefas.ws.system.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.system.entity.QSysNotification;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
public class SysNotificationPredicate {

    public static Predicate findAll() {
        QSysNotification sysNotification = QSysNotification.sysNotification;
        return sysNotification.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }

    public static Predicate findEffective() {
        QSysNotification sysNotification = QSysNotification.sysNotification;
        BooleanExpression statusException = sysNotification.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        BooleanExpression dateRangeException = DateExpression.currentDate()
            .between(sysNotification.notificationEffStartDate, sysNotification.notificationEffEndDate);
        return Expressions.allOf(dateRangeException, statusException);
    }
}
