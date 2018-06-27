/**
 * 
 */
package hk.oro.iefas.ws.system.service;

import java.util.List;

import hk.oro.iefas.domain.common.dto.ActionDTO;
import hk.oro.iefas.domain.system.dto.SysWorkFlowRuleDTO;

/**
 * @version $Revision: 3143 $ $Date: 2018-06-14 18:20:23 +0800 (週四, 14 六月 2018) $
 * @author $Author: marvel.ma $
 */
public interface SysWorkFlowRuleService {

    List<SysWorkFlowRuleDTO> findByPrivilegeCode(String privilegeCode);

    ActionDTO findAction(String privilegeCode, String beforeStatus);

    ActionDTO findIntialAction(String privilegeCode);

}
