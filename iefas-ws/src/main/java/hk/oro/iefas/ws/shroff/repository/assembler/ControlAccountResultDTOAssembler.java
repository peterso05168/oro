package hk.oro.iefas.ws.shroff.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.ControlAccountResultDTO;
import hk.oro.iefas.domain.shroff.entity.ShrCtlAc;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class ControlAccountResultDTOAssembler extends PagingAssemblerSupport<ControlAccountResultDTO, ShrCtlAc> {
    public List<ControlAccountResultDTO> toDTOs(List<ShrCtlAc> controlAccountEntities) {
        List<ControlAccountResultDTO> controlAccountDTOs = new ArrayList<ControlAccountResultDTO>();
        for (ShrCtlAc shrCtlAc : controlAccountEntities) {
            controlAccountDTOs.add(DataUtils.copyProperties(shrCtlAc, ControlAccountResultDTO.class));
        }
        return controlAccountDTOs;
    }

    @Override
    public ControlAccountResultDTO toDTO(ShrCtlAc entity) {
        ControlAccountResultDTO controlAccountResultDTO = null;
        if (entity != null) {
            controlAccountResultDTO = new ControlAccountResultDTO();
            controlAccountResultDTO.setCtlAcId(entity.getCtlAcId());
            controlAccountResultDTO.setBalanceNature(entity.getBalanceNature());
            controlAccountResultDTO.setCtlAcCode(entity.getCtlAcCode());
            controlAccountResultDTO.setCtlAcName(entity.getCtlAcName());
            if (entity.getShrCtlAcType() != null) {
                controlAccountResultDTO.setCtlAcTypeName(entity.getShrCtlAcType().getCtlAcTypeName());
            }
            controlAccountResultDTO.setStatus(entity.getStatus());
        }
        return controlAccountResultDTO;
    }
}
