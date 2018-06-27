/**
 * 
 */
package hk.oro.iefas.ws.system.repository.predicate;

import com.querydsl.core.types.Predicate;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.system.entity.QOroInfo;

/**
 * @version $Revision: 2829 $ $Date: 2018-06-02 11:00:32 +0800 (週六, 02 六月 2018) $
 * @author $Author: marvel.ma $
 */
public class OroInformationPredicate {

    public static Predicate findAll() {
        QOroInfo oroInfo = QOroInfo.oroInfo;
        return oroInfo.status.equalsIgnoreCase(CoreConstant.STATUS_ACTIVE);
    }
}
