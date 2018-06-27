package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.adjudication.entity.AdjIntResultGro;
import hk.oro.iefas.domain.adjudication.entity.GroCode;
import hk.oro.iefas.domain.dividend.dto.AdjIntResultGroDTO;
import hk.oro.iefas.domain.dividend.dto.GroundCodeDTO;
import hk.oro.iefas.ws.adjudication.repository.assembler.GroundCodeDTOAssembler;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

@Component
public class AdjIntResultGroDTOAssembler extends AssemblerSupport<AdjIntResultGroDTO, AdjIntResultGro> {

    @Autowired
    private GroundCodeDTOAssembler groundCodeDTOAssembler;

    @Override
    public AdjIntResultGroDTO toDTO(AdjIntResultGro entity) {
        if (entity != null) {
            AdjIntResultGroDTO result = new AdjIntResultGroDTO();
            result.setAdjIntResultGroId(entity.getAdjIntResultGroId());
            GroCode groCode = entity.getAdjGroCode();
            if (groCode != null) {
                GroundCodeDTO groundCodeDTO = groundCodeDTOAssembler.toDTO(groCode);
                result.setGroundCode(groundCodeDTO);
            }
            result.setStatus(entity.getStatus());
            return result;
        }
        return null;
    }

}
