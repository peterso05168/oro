package hk.oro.iefas.domain.funds.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchCashRequirementCriteriaVO {

    private Integer searchCashRequirementCriteriaId;
    private Integer InvestmentTypeId;
    private Date fromDate;
    private Date toDate;
}
