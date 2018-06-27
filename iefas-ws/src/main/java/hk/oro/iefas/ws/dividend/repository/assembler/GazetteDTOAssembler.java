package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.GazetteDTO;
import hk.oro.iefas.domain.dividend.entity.Gazette;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */

@Component
public class GazetteDTOAssembler extends PagingAssemblerSupport<GazetteDTO, Gazette> {

    @Override
    public GazetteDTO toDTO(Gazette entity) {
        if (entity == null) {
            return null;
        }
        GazetteDTO dto = DataUtils.copyProperties(entity, GazetteDTO.class);
        return dto;
    }
}
