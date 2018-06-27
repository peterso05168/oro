/**
 * 
 */
package hk.oro.iefas.web.ledger.voucherhandling.common.service;

import java.util.List;

import hk.oro.iefas.domain.common.vo.ActionVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;

/**
 * @version $Revision: 3143 $ $Date: 2018-06-14 18:20:23 +0800 (週四, 14 六月 2018) $
 * @author $Author: marvel.ma $
 */
public interface SysWorkFlowRuleClientService {

    List<SysWorkFlowRuleVO> findByPrivilegeCode(String privilegeCode);

    ActionVO findIntialAction(String privilegeCode);
}
