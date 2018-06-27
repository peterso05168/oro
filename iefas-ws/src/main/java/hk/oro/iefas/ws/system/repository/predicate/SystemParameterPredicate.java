/**
 * 
 */
package hk.oro.iefas.ws.system.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.system.entity.QSystemParameter;

/**
 * @version $Revision: 2570 $ $Date: 2018-05-24 14:35:00 +0800 (週四, 24 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class SystemParameterPredicate {

    public static Predicate findAll() {
        QSystemParameter systemparameter = QSystemParameter.systemParameter;
        return systemparameter.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }

    public static Predicate findDateFormat() {
        QSystemParameter systemparameter = QSystemParameter.systemParameter;
        BooleanExpression statusException = systemparameter.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        BooleanExpression paramDescExpression
            = systemparameter.parameterDesc.equalsIgnoreCase("Date format for system");
        return Expressions.allOf(paramDescExpression, statusException);
    }

    public static Predicate findTransactionKey() {
        QSystemParameter systemparameter = QSystemParameter.systemParameter;
        BooleanExpression statusException = systemparameter.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
        BooleanExpression paramDescExpression = systemparameter.parameterDesc.equalsIgnoreCase("transaction key");
        return Expressions.allOf(paramDescExpression, statusException);
    }
}
