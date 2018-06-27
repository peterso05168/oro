/**
 * 
 */
package hk.oro.iefas.ws.organization.repository.predicate;

import com.querydsl.core.types.Predicate;

import hk.oro.iefas.domain.organization.entity.QUserDelegation;
import hk.oro.iefas.domain.organization.entity.QUserProfile;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class UserDelegationPredicate {

    public static Predicate findByDelegateFrom(Integer postId) {
        QUserDelegation userdelegation = QUserDelegation.userDelegation;
        QUserProfile userprofile = QUserProfile.userProfile;
        return userdelegation.delegateFrom.eq(postId).and(userdelegation.delegateTo.eq(userprofile.post));
    }
}
