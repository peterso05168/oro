package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.VoucherAttachmentDTO;
import hk.oro.iefas.domain.shroff.entity.ShrVcrAttachment;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class VoucherAttachmentDTOAssembler extends AssemblerSupport<VoucherAttachmentDTO, ShrVcrAttachment> {

    @Override
    public VoucherAttachmentDTO toDTO(ShrVcrAttachment entity) {
        if (entity != null) {
            VoucherAttachmentDTO dto = DataUtils.copyProperties(entity, VoucherAttachmentDTO.class);
            if (entity.getAttachment() != null) {
                dto.setAttachmentId(entity.getAttachment().getAttachmentId());
            }
            return dto;
        }
        return null;
    }

}
