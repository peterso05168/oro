package hk.oro.iefas.domain.funds.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.bank.dto.BankBasicDTO;
import hk.oro.iefas.domain.bank.dto.BankDepositTypeDTO;
import hk.oro.iefas.domain.common.dto.AccountNumberDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3286 $ $Date: 2018-06-25 17:48:56 +0800 (週一, 25 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AccountInvestmentItemDTO extends TxnDTO {

    private Integer accountInvestmentItemId;
    private AccountNumberDTO caseAccount;
    private AccountNumberDTO controlAccount;
    private String investmentNumber;
    private BigDecimal accountInvestmentAmount;
    private BigDecimal interestRate;
    private Date investmentDate;
    private Date maturityDate;
    private InvestmentTypeDTO investmentType;
    private String investmentOption;
    private String rollOverOption;
    private BankDepositTypeDTO bankDepositType;
    private String remark;
    private BankBasicDTO bankBasic;
    private String joinPoolOption;
    private String status;

}
