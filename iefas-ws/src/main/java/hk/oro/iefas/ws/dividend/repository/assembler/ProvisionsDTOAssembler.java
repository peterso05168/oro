package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.ProvisionsDTO;
import hk.oro.iefas.domain.dividend.entity.DivProvision;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class ProvisionsDTOAssembler extends AssemblerSupport<ProvisionsDTO, DivProvision> {

    @Override
    public ProvisionsDTO toDTO(DivProvision entity) {
        ProvisionsDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, ProvisionsDTO.class);
        }
        return dto;
    }
}
