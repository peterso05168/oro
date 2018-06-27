package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.CredType;
import hk.oro.iefas.domain.dividend.dto.DividendCalculationCreditorDTO;
import hk.oro.iefas.domain.dividend.entity.DivCalCred;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class DividendCalculationCreditorDTOAssembler
    extends AssemblerSupport<DividendCalculationCreditorDTO, DivCalCred> {

    @Override
    public DividendCalculationCreditorDTO toDTO(DivCalCred entity) {
        DividendCalculationCreditorDTO dto = new DividendCalculationCreditorDTO();
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, DividendCalculationCreditorDTO.class);
            CredType credType = entity.getCredType();
            if (credType != null) {
                dto.setCreditorTypeId(credType.getCreditorTypeId());
                dto.setCreditorTypeName(credType.getCreditorTypeName());
            }
        }
        return dto;
    }

}
