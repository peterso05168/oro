package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjType;
import hk.oro.iefas.domain.dividend.dto.CalculatedCreditorDividendDistributionDTO;
import hk.oro.iefas.domain.dividend.entity.DivCalCredDist;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2786 $ $Date: 2018-05-31 17:43:02 +0800 (週四, 31 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CalculatedCreditorDividendDistributionDTOAssembler
    extends AssemblerSupport<CalculatedCreditorDividendDistributionDTO, DivCalCredDist> {
    @Autowired
    private AdjTypeDTOAssembler adjTypeDTOAssembler;

    @Override
    public CalculatedCreditorDividendDistributionDTO toDTO(DivCalCredDist entity) {
        CalculatedCreditorDividendDistributionDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, CalculatedCreditorDividendDistributionDTO.class);
            AdjType adjType = entity.getAdjType();
            if (adjType != null) {
                dto.setAdjudicationType(adjTypeDTOAssembler.toDTO(adjType));
            }
        }
        return dto;
    }
}
