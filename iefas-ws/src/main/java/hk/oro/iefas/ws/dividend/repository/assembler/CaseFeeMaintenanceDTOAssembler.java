package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.CaseFeeMaintenanceDTO;
import hk.oro.iefas.domain.dividend.entity.CaseFeeMain;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CaseFeeMaintenanceDTOAssembler extends AssemblerSupport<CaseFeeMaintenanceDTO, CaseFeeMain> {

    @Override
    public CaseFeeMaintenanceDTO toDTO(CaseFeeMain entity) {
        CaseFeeMaintenanceDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, CaseFeeMaintenanceDTO.class);
            dto.setCaseFeeMaintenanceId(entity.getCaseFeeMainId());
            dto.setCalculationType(entity.getCalType());
            if (entity.getCaseFeeType() != null) {
                dto.setCaseFeeTypeName(entity.getCaseFeeType().getCaseFeeTypeItem());
            }
        }
        return dto;
    }

}
