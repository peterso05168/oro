package hk.oro.iefas.domain.funds.dto;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentInstructionDetailDTO extends TxnDTO {

    private AccountInvestmentItemDTO accountInvestmentItem;
    private BigDecimal estatePoolInvestmentBalance;
    private BigDecimal accountBalance;

}
