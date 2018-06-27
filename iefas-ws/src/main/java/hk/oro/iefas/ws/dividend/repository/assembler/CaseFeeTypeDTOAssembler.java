package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.CaseFeeTypeDTO;
import hk.oro.iefas.domain.dividend.entity.CaseFeeType;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CaseFeeTypeDTOAssembler extends PagingAssemblerSupport<CaseFeeTypeDTO, CaseFeeType> {

    @Override
    public CaseFeeTypeDTO toDTO(CaseFeeType entity) {
        CaseFeeTypeDTO caseFeeTypeDTO = null;
        if (entity != null) {
            caseFeeTypeDTO = DataUtils.copyProperties(entity, CaseFeeTypeDTO.class);
            caseFeeTypeDTO.setCaseFeeTypeName(entity.getCaseFeeTypeItem());
        }
        return caseFeeTypeDTO;
    }
}
