package hk.oro.iefas.ws.adjudication.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.GroCode;
import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.domain.dividend.dto.GroundCodeDTO;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2342 $ $Date: 2018-05-07 15:00:03 +0800 (週一, 07 五月 2018) $
 * @author $Author: noah.liang $
 */

@Component
public class GroundCodeDTOAssembler extends PagingAssemblerSupport<GroundCodeDTO, GroCode> {

    @Override
    public GroundCodeDTO toDTO(GroCode entity) {
        GroundCodeDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, GroundCodeDTO.class);
            if (entity.getNatureOfClaim() != null && !"".equals(entity.getNatureOfClaim())) {
                String nature = null;
                switch (entity.getNatureOfClaim()) {
                    case DividendConstant.PREFERENTIAL:
                        nature = DividendConstant.ADJTYPE_PRE;
                        break;
                    case DividendConstant.ORDINARY:
                        nature = DividendConstant.ADJTYPE_ORD;
                        break;
                    case DividendConstant.INTEREST:
                        nature = DividendConstant.ADJTYPE_INT;
                        break;
                    default:
                        break;
                }
                dto.setNatureOfClaim(nature);
            }
            CaseTypeDTO caseTypeDTO = null;
            if (entity.getCaseType() != null) {
                caseTypeDTO = DataUtils.copyProperties(entity.getCaseType(), CaseTypeDTO.class);
            }
            dto.setCaseType(caseTypeDTO);
        }
        return dto;
    }

}
