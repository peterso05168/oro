package hk.oro.iefas.ws.casemgt.repository.assembler;

import hk.oro.iefas.core.util.BusinessUtils;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.casemgt.entity.CaseType;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;

/**
 * @version $Revision: 3208 $ $Date: 2018-06-20 10:50:21 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
public class CaseNumberDTOAssembler {

    public static CaseNumberDTO toDTO(Case caseInfo) {
        CaseNumberDTO caseNumberDTO = null;
        if (caseInfo != null) {
            caseNumberDTO = new CaseNumberDTO();
            caseNumberDTO.setCaseSequence(BusinessUtils.addZeroToCaseNo(caseInfo.getCaseNo()));
            caseNumberDTO.setCaseYear(String.valueOf(caseInfo.getCaseYear()));
            CaseType caseType = caseInfo.getCaseType();
            if (caseType != null) {
                caseNumberDTO.setCaseType(caseType.getCaseTypeDesc());
            }
        }
        return caseNumberDTO;
    }
}
