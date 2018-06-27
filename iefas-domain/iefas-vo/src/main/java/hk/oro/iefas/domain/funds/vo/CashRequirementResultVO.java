package hk.oro.iefas.domain.funds.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@RequiredArgsConstructor
public class CashRequirementResultVO extends TxnVO {

    private Integer cashRequirementResultId;
    private InvestmentTypeVO investmentType;
    private Date rqmtPeriodFrom;
    private Date rqmtPeriodTo;
    private BigDecimal yearRequirement;
    private BigDecimal dailyRequirement;
    private String status;
}
