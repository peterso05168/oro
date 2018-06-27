package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.CommonCreditorBasicDTO;
import hk.oro.iefas.domain.casemgt.entity.CommonCreditor;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CommonCreditorBasicDTOAssembler extends PagingAssemblerSupport<CommonCreditorBasicDTO, CommonCreditor> {
    @Override
    public CommonCreditorBasicDTO toDTO(CommonCreditor entity) {
        CommonCreditorBasicDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, CommonCreditorBasicDTO.class);
        }
        return dto;
    }
}
