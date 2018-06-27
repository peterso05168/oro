/**
 * 
 */
package hk.oro.iefas.ws.organization.service;

import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.organization.dto.DelegatedDTO;
import hk.oro.iefas.domain.organization.dto.DelegationDTO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface UserDelegationService {

    List<DelegatedDTO> findByDelegateTo(Integer postId, Date currentDate);

    List<DelegationDTO> findByDelegateFrom(Integer postId);

    Integer saveDelegation(DelegationDTO delegationDTO);
}
