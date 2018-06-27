package hk.oro.iefas.domain.bank.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CalculationOfFundsAvailableDTO extends TxnDTO {

    private Integer calculationOfFundsAvailableId;
    private Date investmentDate;
    private String submittedBy;
    private String approvedBy;
    private BigDecimal availableAmount;
    private List<CalculationOfFundsAvailableItemDTO> calculationOfFundsAvailableItems;
    private String status;

}
