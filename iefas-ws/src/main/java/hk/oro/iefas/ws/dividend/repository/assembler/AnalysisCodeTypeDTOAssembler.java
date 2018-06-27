package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeTypeDTO;
import hk.oro.iefas.domain.shroff.entity.AnalysisCodeType;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class AnalysisCodeTypeDTOAssembler extends AssemblerSupport<AnalysisCodeTypeDTO, AnalysisCodeType> {

    @Override
    public AnalysisCodeTypeDTO toDTO(AnalysisCodeType entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, AnalysisCodeTypeDTO.class);
        }
        return null;
    }

}
