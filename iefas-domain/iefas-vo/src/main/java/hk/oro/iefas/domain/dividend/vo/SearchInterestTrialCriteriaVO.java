package hk.oro.iefas.domain.dividend.vo;

import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3163 $ $Date: 2018-06-15 16:09:30 +0800 (週五, 15 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchInterestTrialCriteriaVO {
    private CaseNumberVO caseNumber;
    private Integer caseId;
}
