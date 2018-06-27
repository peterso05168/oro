package hk.oro.iefas.domain.casemgt.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.bank.entity.Currency;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CASE_ACCOUNT_INFO")
public class CaseAccountInfo extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_AC_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_AC_SEQ")
    @TableGenerator(name = "CASE_AC_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "CASE_AC_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer caseAcId;

    @Column(name = "CASE_AC_NUMBER", length = 14)
    private String caseAcNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    private Case caseInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_AC_TYPE_ID")
    private CaseAccountType caseAccountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_AC_SUB_TYPE_ID")
    private CaseAccountSubType caseAccountSubType;

    @Column(name = "EFF_END_DATE")
    private Date effEndDate;

    @Column(name = "EFF_START_DATE")
    private Date effStartDate;

    @Column(name = "INVESTMENT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal investmentAmount;

    @Column(name = "LAST_TRANSACTION_DATE")
    private Date lastTransactionDate;

    @Column(name = "LIQUID_CASH_AMOUNT", precision = 16, scale = 2)
    private BigDecimal liquidCashAmount;

    @Column(name = "ON_HOLD_AMOUNT_CR", precision = 16, scale = 2)
    private BigDecimal onHoldAmountCr;

    @Column(name = "ON_HOLD_AMOUNT_DR", precision = 16, scale = 2)
    private BigDecimal onHoldAmountDr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURCY_ID")
    private Currency currency;

    @Column(name = "STATUS", length = 3)
    private String status;

}
