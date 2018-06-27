/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.user.service;

import java.util.List;

import hk.oro.iefas.domain.organization.vo.DelegatedVO;
import hk.oro.iefas.domain.organization.vo.DelegationVO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface UserDelegationClientService {

    List<DelegatedVO> findByDelegateTo(Integer postId);

    List<DelegationVO> findByDelegateFrom(Integer postId);

    Integer saveDelegation(DelegationVO delegationVO);

}
