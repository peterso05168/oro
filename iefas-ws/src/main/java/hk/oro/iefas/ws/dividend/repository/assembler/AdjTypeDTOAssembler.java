package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjType;
import hk.oro.iefas.domain.dividend.dto.AdjudicationTypeDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 3112 $ $Date: 2018-06-13 15:48:09 +0800 (週三, 13 六月 2018) $
 * @author $Author: noah.liang $
 */
@Component
public class AdjTypeDTOAssembler extends AssemblerSupport<AdjudicationTypeDTO, AdjType> {

    @Override
    public AdjudicationTypeDTO toDTO(AdjType entity) {
        AdjudicationTypeDTO adjdto = null;
        if (entity != null) {
            adjdto = DataUtils.copyProperties(entity, AdjudicationTypeDTO.class);
            adjdto.setAdjudicationTypeId(entity.getAdjTypeId());
            adjdto.setAdjudicationTypeName(entity.getAdjTypeDesc());
        }
        return adjdto;
    }

    public AdjType toEntity(AdjudicationTypeDTO adjudicationTypeDTO) {
        AdjType adjType = null;
        if (adjudicationTypeDTO != null) {
            adjType = new AdjType();
            adjType.setAdjTypeId(adjudicationTypeDTO.getAdjudicationTypeId());
            adjType.setAdjTypeDesc(adjudicationTypeDTO.getAdjudicationTypeName());
        }
        return adjType;
    }
}
