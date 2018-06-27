package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeDTO;
import hk.oro.iefas.domain.shroff.entity.AnalysisCode;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class AnalysisCodeDTOAssembler extends AssemblerSupport<AnalysisCodeDTO, AnalysisCode> {

    @Override
    public AnalysisCodeDTO toDTO(AnalysisCode entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, AnalysisCodeDTO.class);
        }
        return null;
    }

}
