package hk.oro.iefas.ws.funds.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.dto.CalculationOfFundsAvailableDTO;
import hk.oro.iefas.domain.funds.entity.CalOfAvailable;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class CalculationOfFundsAvailableDTOAssembler
    extends PagingAssemblerSupport<CalculationOfFundsAvailableDTO, CalOfAvailable> {

    @Override
    public CalculationOfFundsAvailableDTO toDTO(CalOfAvailable calOfAvailable) {
        if (calOfAvailable != null) {
            CalculationOfFundsAvailableDTO dto
                = DataUtils.copyProperties(calOfAvailable, CalculationOfFundsAvailableDTO.class);
            return dto;
        }
        return null;
    }
}
