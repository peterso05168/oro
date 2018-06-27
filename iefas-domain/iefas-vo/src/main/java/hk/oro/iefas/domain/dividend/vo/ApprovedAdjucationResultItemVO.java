package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ApprovedAdjucationResultItemVO {

    private Integer approvedAdjucationResultItemId;
    private AdjudicationTypeVO adjudicationType;
    private BigDecimal admittedAmount;
    private BigDecimal amountPaid;
    private BigDecimal percentPaid;
    private String status;
    // -----------Page param----------
    private BigDecimal amountToBeDistributed;
    private BigDecimal percentageToBeDistributed;
}
