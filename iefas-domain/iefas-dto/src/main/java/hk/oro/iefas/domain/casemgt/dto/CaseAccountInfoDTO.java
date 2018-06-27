package hk.oro.iefas.domain.casemgt.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.bank.dto.CurrencyDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2020 $ $Date: 2018-04-11 16:34:06 +0800 (週三, 11 四月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CaseAccountInfoDTO extends TxnDTO {

    private Integer caseAcId;
    private String caseAcNumber;
    private CaseDTO caseInfo;
    private CaseAccountTypeDTO caseAccountType;
    private CaseAccountSubTypeDTO caseAccountSubType;
    private Date effEndDate;
    private Date effStartDate;
    private BigDecimal investmentAmount;
    private Date lastTransactionDate;
    private BigDecimal liquidCashAmount;
    private BigDecimal onHoldAmountCr;
    private BigDecimal onHoldAmountDr;
    private CurrencyDTO currency;
    private String status;
}
