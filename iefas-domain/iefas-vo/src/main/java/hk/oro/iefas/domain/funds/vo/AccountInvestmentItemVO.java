package hk.oro.iefas.domain.funds.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.bank.vo.BankBasicVO;
import hk.oro.iefas.domain.bank.vo.BankDepositTypeVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
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
public class AccountInvestmentItemVO extends TxnVO {

    private Integer accountInvestmentItemId;
    // private CaseAccountBasicVO caseInfo;
    // ControlAccountBasicVO
    private String investmentNumber;
    private BigDecimal accountInvestmentAmount;
    private BigDecimal interestRate;
    private Date investmentDate;
    private Date maturityDate;
    private InvestmentTypeVO investmentType;
    private String investmentOption;
    private String rollOverOption;
    private BankDepositTypeVO bankDepositType;
    private String remark;
    private BankBasicVO bankBasic;
    private String joinPoolOption;
    private String status;
}
