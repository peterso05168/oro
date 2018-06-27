/**
 * 
 */
package hk.oro.iefas.ws.system.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.system.dto.SysWorkFlowRuleDTO;
import hk.oro.iefas.domain.system.entity.SysWorkFlowRule;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class SysWorkFlowRuleDTOAssembler extends AssemblerSupport<SysWorkFlowRuleDTO, SysWorkFlowRule> {

    @Override
    public SysWorkFlowRuleDTO toDTO(SysWorkFlowRule entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, SysWorkFlowRuleDTO.class);
        }
        return null;
    }

}
