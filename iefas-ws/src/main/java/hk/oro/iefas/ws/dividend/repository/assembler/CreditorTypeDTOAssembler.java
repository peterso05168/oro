package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.CredType;
import hk.oro.iefas.domain.dividend.dto.CreditorTypeDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 3112 $ $Date: 2018-06-13 15:48:09 +0800 (週三, 13 六月 2018) $
 * @author $Author: noah.liang $
 */
@Component
public class CreditorTypeDTOAssembler extends AssemblerSupport<CreditorTypeDTO, CredType> {

    @Override
    public CreditorTypeDTO toDTO(CredType entity) {
        CreditorTypeDTO dto = new CreditorTypeDTO();
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, CreditorTypeDTO.class);
        }
        return dto;
    }

    public CredType toEntity(CreditorTypeDTO creditorType) {
        if (creditorType != null) {
            CredType credType = DataUtils.copyProperties(creditorType, CredType.class);
            return credType;
        }
        return null;
    }

}
