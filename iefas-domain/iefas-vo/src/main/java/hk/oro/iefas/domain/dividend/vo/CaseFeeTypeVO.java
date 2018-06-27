package hk.oro.iefas.domain.dividend.vo;

import java.util.ArrayList;
import java.util.List;

import hk.oro.iefas.domain.common.vo.CaseTypeVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
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
public class CaseFeeTypeVO extends TxnVO {

    private Integer caseFeeTypeId;
    private CaseTypeVO caseType;
    private String caseFeeTypeName;
    private String caseFeeTypeDesc;
    private List<CaseFeeMaintenanceVO> caseFeeMaintenances = new ArrayList<>();
    private List<CaseFeeToBeChargedVO> caseFeeToBeChargeds = new ArrayList<>();
    private String status;
}
