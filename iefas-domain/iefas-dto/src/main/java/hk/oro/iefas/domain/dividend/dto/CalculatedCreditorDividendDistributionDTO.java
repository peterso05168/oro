package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2830 $ $Date: 2018-06-02 12:07:55 +0800 (週六, 02 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CalculatedCreditorDividendDistributionDTO extends TxnDTO {
    private Integer calCredDisId;
    private AdjudicationTypeDTO adjudicationType;
    private BigDecimal amountPaid;
    private BigDecimal distAmount;
    private BigDecimal percent;
    private String status;
}
