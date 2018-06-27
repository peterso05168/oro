package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.system.dto.SysRejectReasonDTO;
import hk.oro.iefas.domain.system.entity.SysRejectReason;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2786 $ $Date: 2018-05-31 17:43:02 +0800 (Thu, 31 May 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class SysRejectReasonDTOAssembler extends AssemblerSupport<SysRejectReasonDTO, SysRejectReason> {

    @Override
    public SysRejectReasonDTO toDTO(SysRejectReason entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, SysRejectReasonDTO.class);
        }
        return null;
    }

}
