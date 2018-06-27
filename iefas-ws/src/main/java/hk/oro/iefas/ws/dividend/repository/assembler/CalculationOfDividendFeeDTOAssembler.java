package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.CalculationOfDividendFeeDTO;
import hk.oro.iefas.domain.dividend.entity.DivCalOfDiv;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CalculationOfDividendFeeDTOAssembler extends AssemblerSupport<CalculationOfDividendFeeDTO, DivCalOfDiv> {

    @Override
    public CalculationOfDividendFeeDTO toDTO(DivCalOfDiv entity) {
        CalculationOfDividendFeeDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, CalculationOfDividendFeeDTO.class);
        }
        return dto;
    }
}
