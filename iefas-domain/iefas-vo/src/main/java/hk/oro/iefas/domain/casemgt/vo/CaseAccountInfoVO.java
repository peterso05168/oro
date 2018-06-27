package hk.oro.iefas.domain.casemgt.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.bank.vo.CurrencyVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
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
public class CaseAccountInfoVO extends TxnVO {

    private Integer caseAcId;
    private String caseAcNumber;
    private CaseVO caseInfo;
    private CaseAccountTypeVO caseAccountType;
    private CaseAccountSubTypeVO caseAccountSubType;
    private Date effEndDate;
    private Date effStartDate;
    private BigDecimal investmentAmount;
    private Date lastTransactionDate;
    private BigDecimal liquidCashAmount;
    private BigDecimal onHoldAmountCr;
    private BigDecimal onHoldAmountDr;
    private CurrencyVO currency;
    private String status;
}
