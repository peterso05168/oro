package hk.oro.iefas.domain.dividend.vo;

import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3042 $ $Date: 2018-06-11 20:51:27 +0800 (週一, 11 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchAdjudicationCriteriaVO {
    private CaseNumberVO caseNumber;
    private Integer caseId;
    private String status;
}
