package hk.oro.iefas.domain.shroff.dto;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2910 $ $Date: 2018-06-05 17:49:32 +0800 (週二, 05 六月 2018) $
 * @author $Author: george.wu $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ControlAccountDTO extends TxnDTO {

    private Integer ctlAcId;
    private String balanceNature;
    private String ctlAcCode;
    private String ctlAcName;
    private Integer curcyId;
    private BigDecimal onHoldAmountCr;
    private BigDecimal onHoldAmountDr;
    private String status;
    private ControlAccountTypeDTO shrCtlAcType;
    private BigDecimal balance;
    private BigDecimal liquidCashAmount;
    private BigDecimal investmentAmount;
    
}
