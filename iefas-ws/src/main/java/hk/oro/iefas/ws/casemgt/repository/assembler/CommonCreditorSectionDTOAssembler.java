package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.CommonCreditorSectionDTO;
import hk.oro.iefas.domain.casemgt.entity.CommonCreditorSection;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class CommonCreditorSectionDTOAssembler
    extends AssemblerSupport<CommonCreditorSectionDTO, CommonCreditorSection> {
    @Override
    public CommonCreditorSectionDTO toDTO(CommonCreditorSection entity) {
        CommonCreditorSectionDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, CommonCreditorSectionDTO.class);
        }
        return dto;
    }

}
