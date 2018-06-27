package hk.oro.iefas.domain.funds.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.bank.entity.BankDepositType;
import hk.oro.iefas.domain.bank.entity.BankInfo;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FUND_INV_ITEM")
public class InvItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "INVEST_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "INVEST_ITEM_SEQ")
    @TableGenerator(name = "INVEST_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "INVEST_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer accountInvestmentItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ACCOUNT_ID")
    private CaseAccountInfo caseInfo;

    @Column(name = "INVEST_NUMBER")
    private String investmentNumber;

    @Column(name = "ACCOUNT_INVEST_AMOUNT")
    private BigDecimal accountInvestmentAmount;

    @Column(name = "INT_RATE")
    private BigDecimal interestRate;

    @Column(name = "INVEST_DATE")
    private Date investmentDate;

    @Column(name = "MATURITY_DATE")
    private Date maturityDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVEST_TYPE_ID")
    private InvType investmentType;

    @Column(name = "INVEST_OPTION")
    private String investmentOption;

    @Column(name = "ROLL_OVER_OPTION")
    private String rollOverOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_DEPOSIT_TYPE_ID")
    private BankDepositType bankDepositType;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "ACCOUNT_WITHDRAW_AMOUNT")
    private BigDecimal accountWithdrawAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_INFO_ID")
    private BankInfo bankBasic;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "BANK_DEPO_NO")
    private String bankDepoNo;

    @Column(name = "OLD_INVEST_ITEM_ID")
    private BigDecimal oldInvestItemId;

}
