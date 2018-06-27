/**
 * 
 */
package hk.oro.iefas.ws.system.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.system.dto.SysWfInitialStatusDTO;
import hk.oro.iefas.domain.system.entity.SysWfInitialStatus;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class SysWfInitialStatusDTOAssembler extends AssemblerSupport<SysWfInitialStatusDTO, SysWfInitialStatus> {

    @Override
    public SysWfInitialStatusDTO toDTO(SysWfInitialStatus entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, SysWfInitialStatusDTO.class);
        }
        return null;
    }

}
