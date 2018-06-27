package hk.oro.iefas.domain.bank.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2297 $ $Date: 2018-05-02 18:27:28 +0800 (週三, 02 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CalculationOfFundsAvailableVO extends TxnVO {

    private Integer calculationOfFundsAvailableId;
    private Date investmentDate;
    private String submittedBy;
    private String approvedBy;
    private BigDecimal availableAmount;
    private List<CalculationOfFundsAvailableItemVO> calculationOfFundsAvailableItems;
    private String status;

}
