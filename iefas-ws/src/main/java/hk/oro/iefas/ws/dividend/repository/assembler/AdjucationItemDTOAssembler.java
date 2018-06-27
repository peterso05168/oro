package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjResultItem;
import hk.oro.iefas.domain.dividend.dto.AdjucationItemDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class AdjucationItemDTOAssembler extends AssemblerSupport<AdjucationItemDTO, AdjResultItem> {
    @Autowired
    private AdjTypeDTOAssembler adjTypeDTOAssembler;

    @Override
    public AdjucationItemDTO toDTO(AdjResultItem entity) {
        AdjucationItemDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, AdjucationItemDTO.class);
            if (entity.getAdjType() != null) {
                dto.setAdjudicationType(adjTypeDTOAssembler.toDTO(entity.getAdjType()));
            }
        }
        return dto;
    }

}
