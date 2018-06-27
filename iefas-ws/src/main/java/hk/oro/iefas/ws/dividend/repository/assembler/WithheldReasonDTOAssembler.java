package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.dividend.dto.WithheldReasonDTO;
import hk.oro.iefas.domain.dividend.entity.DivWithheldReasonType;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 3112 $ $Date: 2018-06-13 15:48:09 +0800 (週三, 13 六月 2018) $
 * @author $Author: noah.liang $
 */

@Component
public class WithheldReasonDTOAssembler extends PagingAssemblerSupport<WithheldReasonDTO, DivWithheldReasonType> {

    @Override
    public WithheldReasonDTO toDTO(DivWithheldReasonType entity) {
        if (entity != null) {
            WithheldReasonDTO withheldReasonDTO = new WithheldReasonDTO();
            withheldReasonDTO.setWithheldReasonId(entity.getWithheldRsnTypeId());
            withheldReasonDTO.setWithheldReasonCode(entity.getWithheldRsnCode());
            withheldReasonDTO.setWithheldReasonDescription(entity.getWithheldRsnDesc());
            withheldReasonDTO.setWithheldReasonDescriptionChinese(entity.getWithheldRsnDescChi());
            withheldReasonDTO.setStatus(entity.getStatus());
            return withheldReasonDTO;
        }
        return null;
    }

    public DivWithheldReasonType toEntity(WithheldReasonDTO withheldReasonDTO) {
        if (withheldReasonDTO != null) {
            DivWithheldReasonType divWithheldReasonType = new DivWithheldReasonType();
            divWithheldReasonType.setWithheldRsnTypeId(withheldReasonDTO.getWithheldReasonId());
            divWithheldReasonType.setWithheldRsnCode(withheldReasonDTO.getWithheldReasonCode());
            divWithheldReasonType.setWithheldRsnDesc(withheldReasonDTO.getWithheldReasonDescription());
            divWithheldReasonType.setWithheldRsnDescChi(withheldReasonDTO.getWithheldReasonDescriptionChinese());
            divWithheldReasonType.setStatus(withheldReasonDTO.getStatus());
            return divWithheldReasonType;
        }
        return null;
    }

}
