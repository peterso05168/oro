package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.casemgt.entity.OutsiderType;
import hk.oro.iefas.domain.common.dto.OutsiderTypeDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Component
public class OutsiderTypeDTOAssembler extends AssemblerSupport<OutsiderTypeDTO, OutsiderType> {

    @Override
    public OutsiderTypeDTO toDTO(OutsiderType t) {
        OutsiderTypeDTO outsiderTypeDTO = null;
        if (t != null) {
            outsiderTypeDTO = new OutsiderTypeDTO();
            outsiderTypeDTO.setOutsiderTypeId(t.getOutsiderTypeId());
            outsiderTypeDTO.setOutsiderTypeName(t.getOutsiderTypeName());
            outsiderTypeDTO.setOutsiderTypeDesc(t.getOutsiderTypeDesc());
            outsiderTypeDTO.setStatus(t.getStatus());
            outsiderTypeDTO.setOutsiderTypeCode(t.getOutsiderTypeCode());

        }
        return outsiderTypeDTO;
    }

}
