package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.CaseAccountInfoDTO;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CaseAccountInfoDTOAssembler extends AssemblerSupport<CaseAccountInfoDTO, CaseAccountInfo> {

    @Override
    public CaseAccountInfoDTO toDTO(CaseAccountInfo caseAccount) {
        CaseAccountInfoDTO dto = null;
        if (caseAccount != null) {
            dto = DataUtils.copyProperties(caseAccount, CaseAccountInfoDTO.class);
        }
        return dto;
    }

}
