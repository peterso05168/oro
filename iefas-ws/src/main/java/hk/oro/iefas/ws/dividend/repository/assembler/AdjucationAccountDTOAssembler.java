package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjAccNumber;
import hk.oro.iefas.domain.dividend.dto.AdjucationAccountDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class AdjucationAccountDTOAssembler extends AssemblerSupport<AdjucationAccountDTO, AdjAccNumber> {

    @Override
    public AdjucationAccountDTO toDTO(AdjAccNumber entity) {
        AdjucationAccountDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, AdjucationAccountDTO.class);
        }
        return dto;
    }

}
