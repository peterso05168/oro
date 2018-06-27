package hk.oro.iefas.domain.dividend.dto;

import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
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
public class SearchInterestTrialCriteriaDTO {
    private CaseNumberDTO caseNumber;
    private Integer caseId;
}
