package hk.oro.iefas.domain.dividend.vo;

import hk.oro.iefas.domain.common.vo.CaseNumberVO;
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
public class SearchWilonAndSeverancePayCriteriaVO extends TxnVO {

    private CaseNumberVO caseNumber = new CaseNumberVO();
    private Integer creditorId;
}
