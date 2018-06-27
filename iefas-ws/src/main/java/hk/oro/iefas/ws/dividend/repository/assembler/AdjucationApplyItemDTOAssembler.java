package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjApplyItem;
import hk.oro.iefas.domain.bank.dto.CurrencyBasicInfoDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationApplyItemDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class AdjucationApplyItemDTOAssembler extends AssemblerSupport<AdjucationApplyItemDTO, AdjApplyItem> {

    @Override
    public AdjucationApplyItemDTO toDTO(AdjApplyItem entity) {
        AdjucationApplyItemDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, AdjucationApplyItemDTO.class);
            if (entity.getCurrency() != null) {
                dto.setCurrencyBasicInfo(DataUtils.copyProperties(entity.getCurrency(), CurrencyBasicInfoDTO.class));
            }
        }
        return dto;
    }

}
