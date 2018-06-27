package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
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
public class CaseFeeDTO extends TxnDTO {

    private Integer caseFeeId;
    private CaseFeeTypeDTO caseFeeType;
    private CaseDTO casemgt;
    private Integer scheduleId;
    private Date issuesDate;
    private BigDecimal feeAmount;
    private BigDecimal amountPaid;
    private String status;
}
