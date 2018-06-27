package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.DepositCardDTO;
import hk.oro.iefas.domain.casemgt.entity.CaseDepositCard;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class DepositCardDTOAssembler extends AssemblerSupport<DepositCardDTO, CaseDepositCard> {

    @Override
    public DepositCardDTO toDTO(CaseDepositCard depositCard) {
        DepositCardDTO dto = null;
        if (depositCard != null) {
            dto = DataUtils.copyProperties(depositCard, DepositCardDTO.class);
        }
        return dto;
    }

}
