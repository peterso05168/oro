package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.CaseFeeForDividendCalculationDTO;
import hk.oro.iefas.domain.dividend.entity.DivCaseFeeCal;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class CaseFeeForDividendCalculationDTOAssembler
    extends AssemblerSupport<CaseFeeForDividendCalculationDTO, DivCaseFeeCal> {

    @Override
    public CaseFeeForDividendCalculationDTO toDTO(DivCaseFeeCal entity) {
        CaseFeeForDividendCalculationDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, CaseFeeForDividendCalculationDTO.class);
        }
        return dto;
    }
}
