package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.OutsiderDTO;
import hk.oro.iefas.domain.casemgt.entity.Outsider;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class OutsiderDTOAssembler extends PagingAssemblerSupport<OutsiderDTO, Outsider> {

    @Override
    public OutsiderDTO toDTO(Outsider outsider) {
        OutsiderDTO outsiderDTO = null;
        if (outsider != null) {
            outsiderDTO = DataUtils.copyProperties(outsider, OutsiderDTO.class);
        }
        return outsiderDTO;
    }

}
