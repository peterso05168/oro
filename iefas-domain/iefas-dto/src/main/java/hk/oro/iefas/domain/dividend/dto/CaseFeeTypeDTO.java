package hk.oro.iefas.domain.dividend.dto;

import java.util.ArrayList;
import java.util.List;

import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CaseFeeTypeDTO extends TxnDTO {

    private Integer caseFeeTypeId;
    private CaseTypeDTO caseType;
    private String caseFeeTypeName;
    private String caseFeeTypeDesc;
    private List<CaseFeeMaintenanceDTO> caseFeeMaintenances = new ArrayList<>();
    private List<CaseFeeToBeChargedDTO> caseFeeToBeChargeds = new ArrayList<>();
    private String status;
}
