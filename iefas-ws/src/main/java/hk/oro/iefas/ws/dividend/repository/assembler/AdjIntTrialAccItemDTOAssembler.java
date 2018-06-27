package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjIntTrialAccItem;
import hk.oro.iefas.domain.dividend.dto.AdjIntTrialAccItemDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

@Component
public class AdjIntTrialAccItemDTOAssembler extends AssemblerSupport<AdjIntTrialAccItemDTO, AdjIntTrialAccItem> {

    @Override
    public AdjIntTrialAccItemDTO toDTO(AdjIntTrialAccItem entity) {
        if (entity != null) {
            AdjIntTrialAccItemDTO result = DataUtils.copyProperties(entity, AdjIntTrialAccItemDTO.class);
            return result;
        }
        return null;
    }

}
