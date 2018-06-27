package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjResultIntItem;
import hk.oro.iefas.domain.dividend.dto.AdjucationResultIntItemDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

@Component
public class AdjucationResultIntItemDTOAssembler
    extends AssemblerSupport<AdjucationResultIntItemDTO, AdjResultIntItem> {

    @Override
    public AdjucationResultIntItemDTO toDTO(AdjResultIntItem entity) {
        if (entity != null) {
            AdjucationResultIntItemDTO result = DataUtils.copyProperties(entity, AdjucationResultIntItemDTO.class);
            return result;
        }
        return null;
    }

}
