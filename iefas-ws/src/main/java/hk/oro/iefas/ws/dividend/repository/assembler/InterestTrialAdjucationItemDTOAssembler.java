package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.adjudication.entity.AdjResultIntItem;
import hk.oro.iefas.domain.dividend.dto.InterestTrialAdjucationItemDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

@Component
public class InterestTrialAdjucationItemDTOAssembler
    extends AssemblerSupport<InterestTrialAdjucationItemDTO, AdjResultIntItem> {

    @Override
    public InterestTrialAdjucationItemDTO toDTO(AdjResultIntItem entity) {
        if (entity != null) {
            InterestTrialAdjucationItemDTO result = new InterestTrialAdjucationItemDTO();
            result.setAccountNumber(entity.getAccountNumber());
            result.setStatus(entity.getStatus());
            return result;
        }
        return null;
    }

}
