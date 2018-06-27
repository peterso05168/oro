package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.CaseAccountTypeDTO;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountType;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CaseAccountTypeDTOAssembler extends AssemblerSupport<CaseAccountTypeDTO, CaseAccountType> {

    @Override
    public CaseAccountTypeDTO toDTO(CaseAccountType t) {
        CaseAccountTypeDTO caseTypeDTO = null;
        if (t != null) {
            caseTypeDTO = DataUtils.copyProperties(t, CaseAccountTypeDTO.class);
        }
        return caseTypeDTO;
    }

}
