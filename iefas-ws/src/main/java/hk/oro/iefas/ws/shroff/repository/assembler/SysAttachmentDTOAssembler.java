package hk.oro.iefas.ws.shroff.repository.assembler;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.SysAttachmentDTO;
import hk.oro.iefas.domain.system.entity.SysAttachment;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class SysAttachmentDTOAssembler extends AssemblerSupport<SysAttachmentDTO, SysAttachment> {

    @Override
    public SysAttachmentDTO toDTO(SysAttachment entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, SysAttachmentDTO.class, Arrays.asList("content"));
        }
        return null;
    }

}
