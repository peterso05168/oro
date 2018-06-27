package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.ControlAccountTypeDTO;
import hk.oro.iefas.domain.shroff.entity.ShrCtlAcType;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class ControlAccountTypeDTOAssembler extends PagingAssemblerSupport<ControlAccountTypeDTO, ShrCtlAcType> {
    @Override
    public ControlAccountTypeDTO toDTO(ShrCtlAcType entity) {
        ControlAccountTypeDTO controlAccountTypeDTO = null;
        if (entity != null) {
            controlAccountTypeDTO = DataUtils.copyProperties(entity, ControlAccountTypeDTO.class);
        }
        return controlAccountTypeDTO;
    }
}
