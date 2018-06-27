package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjResultGro;
import hk.oro.iefas.domain.dividend.dto.AdjucationGroundDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class AdjucationGroundDTOAssembler extends AssemblerSupport<AdjucationGroundDTO, AdjResultGro> {

    @Override
    public AdjucationGroundDTO toDTO(AdjResultGro entity) {
        AdjucationGroundDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, AdjucationGroundDTO.class);
            if (entity.getGroundCode() != null) {
                dto.setGroundCodeId(entity.getGroundCode().getGroundCodeId());
                dto.setGroundCodeCode(entity.getGroundCode().getGroundCodeCode());
            }
        }
        return dto;
    }

}
