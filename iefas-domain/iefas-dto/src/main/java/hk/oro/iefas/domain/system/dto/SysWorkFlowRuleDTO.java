/**
 * 
 */
package hk.oro.iefas.domain.system.dto;

import hk.oro.iefas.domain.common.dto.ApplicationCodeTableDTO;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWorkFlowRuleDTO {

    private Integer ruleId;
    private PrivilegeDTO privilege;
    private ApplicationCodeTableDTO beforeStatus;
    private ApplicationCodeTableDTO action;
    private ApplicationCodeTableDTO afterStatus;
    private ApplicationCodeTableDTO actionDone;
    private ApplicationCodeTableDTO actionToBeTaken;

}
