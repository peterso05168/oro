package hk.oro.iefas.domain.shroff.vo;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2807 $ $Date: 2018-06-01 10:58:03 +0800 (週五, 01 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ControlAccountVO extends TxnVO {

    private Integer ctlAcId;
    private String balanceNature;
    private String ctlAcCode;
    private String ctlAcName;
    private Integer curcyId;
    private BigDecimal onHoldAmountCr;
    private BigDecimal onHoldAmountDr;
    private String status;
    private ControlAccountTypeVO shrCtlAcType;
    private BigDecimal balance;
    private BigDecimal liquidCashAmount;
    private BigDecimal investmentAmount;
}
