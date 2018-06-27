package hk.oro.iefas.domain.funds.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.dto.TxnDTO;
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
public class DailyCashRequirementGenerateDTO extends TxnDTO {

    private Date effectiveFrom;
    private Date effectiveTo;
    private InvestmentTypeDTO investmentType;
    private BigDecimal amount;
    private Integer nature;
    private int mode;
}
