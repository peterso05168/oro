package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2908 $ $Date: 2018-06-05 17:35:06 +0800 (週二, 05 六月 2018) $
 * @author $Author: noah.liang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InterestTrialAdjucationItemDTO {
    //time out
    private Integer interestTrialAdjucationItemId;
    private String accountNumber;
    private BigDecimal admittedAmount;
    private BigDecimal interestRate;
    private String status;
}
