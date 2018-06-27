package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.casemgt.entity.CaseType;
import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CaseTypeDTOAssembler extends AssemblerSupport<CaseTypeDTO, CaseType> {

    @Override
    public CaseTypeDTO toDTO(CaseType t) {
        CaseTypeDTO caseTypeDTO = null;
        if (t != null) {
            caseTypeDTO = new CaseTypeDTO(t.getCaseTypeId(), t.getCaseTypeDesc(), t.getCaseTypeCode(), t.getStatus());
        }
        return caseTypeDTO;
    }

}
