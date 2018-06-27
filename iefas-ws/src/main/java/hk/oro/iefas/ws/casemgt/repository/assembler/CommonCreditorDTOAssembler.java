package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.CommonCreditorDTO;
import hk.oro.iefas.domain.casemgt.entity.CommonCreditor;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CommonCreditorDTOAssembler extends PagingAssemblerSupport<CommonCreditorDTO, CommonCreditor> {
    @Override
    public CommonCreditorDTO toDTO(CommonCreditor entity) {
        CommonCreditorDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, CommonCreditorDTO.class);
        }
        return dto;
    }
}
