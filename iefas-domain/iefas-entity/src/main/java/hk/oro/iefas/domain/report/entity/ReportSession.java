package hk.oro.iefas.domain.report.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.casemgt.entity.CaseType;
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
@Table(name = "REPORT_SESSION")
public class ReportSession extends UpdateTxnKeyable {

    @Id
    @Column(name = "REPORT_SESSION_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "REPORT_SESSION_SEQ")
    @TableGenerator(name = "REPORT_SESSION_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "REPORT_SESSION_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer reportSessionId;

    @Column(name = "REPORT_SESSION_STATUS")
    private String reportSessionStatus;

    @Column(name = "REPORT_FORMAT")
    private String reportFormat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_TYPE_ID_FROM")
    private CaseType caseTypeFrom;

    @Column(name = "CASE_NO_FROM")
    private String caseNoFrom;

    @Column(name = "CASE_YEAR_FROM")
    private String caseYearFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_TYPE_ID_TO")
    private CaseType caseTypeTo;

    @Column(name = "CASE_NO_TO")
    private String caseNoTo;

    @Column(name = "CASE_YEAR_TO")
    private String caseYearTo;

    @Column(name = "DATE_FROM")
    private Date dateFrom;

    @Column(name = "DATE_To")
    private Date dateTo;

    @Column(name = "DATE_REFERENCE_1")
    private Date dateReference1;

    @Column(name = "DATE_REFERENCE_2")
    private Date dateReference2;

    @Column(name = "DATE_REFERENCE_3")
    private Date dateReference3;

    @Column(name = "DATE_REFERENCE_4")
    private Date dateReference4;

    @Column(name = "ESTATE_BALANCE_FROM")
    private BigDecimal estateBalanceFrom;

    @Column(name = "ESTATE_BALANCE_TO")
    private BigDecimal estateBalanceTo;

    @Column(name = "CASH_BALANCE_FROM")
    private BigDecimal cashBalanceFrom;

    @Column(name = "CASH_BALANCE_TO")
    private BigDecimal cashBalanceTo;

    @Column(name = "INVESTMENT_BALANCE_FROM")
    private BigDecimal investmentBalanceFrom;

    @Column(name = "INVESTMENT_BALANCE_To")
    private BigDecimal investmentBalanceTo;

    @Column(name = "AMOUNT_REFERENCE_1")
    private BigDecimal amountReference1;

    @Column(name = "AMOUNT_REFERENCE_2")
    private BigDecimal amountReference2;

    @Column(name = "AMOUNT_REFERENCE_3")
    private BigDecimal amountReference3;

    @Column(name = "AMOUNT_REFERENCE_4")
    private BigDecimal amountReference4;

    @Column(name = "AMOUNT_REFERENCE_5")
    private BigDecimal amountReference5;

    @Column(name = "REFERENCE_FROM")
    private String referenceFrom;

    @Column(name = "REFERENCE_TO")
    private String referenceTo;

    @Column(name = "REFERENCE_1")
    private String reference1;

    @Column(name = "REFERENCE_2")
    private String reference2;

    @Column(name = "REFERENCE_3")
    private String reference3;

    @Column(name = "REFERENCE_4")
    private String reference4;

    @Column(name = "REFERENCE_5")
    private String reference5;

    @Column(name = "NUMERIC_FROM")
    private BigDecimal numericFrom;

    @Column(name = "NUMERIC_TO")
    private BigDecimal numericTo;

    @Column(name = "ANALYSISCODEBAL_FROM")
    private BigDecimal analysisCodebalFrom;

    @Column(name = "ANALYSISCODEBAL_TO")
    private BigDecimal analysisCodebalTo;

    @Column(name = "INV_STA_PLACED")
    private String invStaPlaced;

    @Column(name = "INV_STA_UPLIFTED")
    private String invStaUplifted;

    @Column(name = "INV_STA_RENEWED")
    private String invStaRenewed;

    @Column(name = "INV_STA_ROLLED_OVER")
    private String invStaRolledOver;

    @Column(name = "IS_DIV_ACC")
    private String isDivAcc;

    @Column(name = "IS_BEA")
    private String isBea;

    @Column(name = "IS_REV")
    private String isRev;

    @Column(name = "IS_PAID")
    private String isPaid;

    @Column(name = "IS_UNPAID")
    private String isUnpaid;

    @Column(name = "PERIOD")
    private String period;

    @Column(name = "CREDITOR_TYPE")
    private String creditorType;

    @Column(name = "PROOF_NO_START")
    private String proofNoStart;

    @Column(name = "PROOF_NO_END")
    private String proofNoEnd;

    @Column(name = "NATURE_OF_CLAIM")
    private String natureOfClaim;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "REPORT_ID")
    private String reportId;

    @Lob
    @Column(name = "SQL1")
    private String sql1;

    @Lob
    @Column(name = "SQL2")
    private String sql2;

    @Lob
    @Column(name = "SQL3")
    private String sql3;

    @Column(name = "STATUS", length = 3)
    private String status;
}
