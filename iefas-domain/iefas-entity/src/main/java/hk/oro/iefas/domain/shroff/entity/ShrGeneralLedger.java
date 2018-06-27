package hk.oro.iefas.domain.shroff.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_GENERAL_LEDGER database table.
 * 
 */
@Entity
@Table(name = "SHR_GENERAL_LEDGER")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrGeneralLedger extends UpdateTxnKeyable {

    @Id
    @Column(name = "GENERAL_LEDGER_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GENERAL_LEDGER_SEQ")
    @TableGenerator(name = "GENERAL_LEDGER_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "GENERAL_LEDGER_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer generalLedgerId;

    @Column(name = "ANALYSIS_CODE", length = 7)
    private String analysisCode;

    @Column(precision = 16, scale = 2)
    private BigDecimal balance;

    @Column(name = "BANK_AC_ID", precision = 15)
    private Integer bankAcId;

    @Column(name = "CASE_AC_ID", precision = 15)
    private Integer caseAcId;

    @Column(name = "CHEQUE_NO", precision = 15)
    private String chequeNo;

    @Column(name = "CONTROL_AC_ID", precision = 15)
    private Integer controlAcId;

    @Column(precision = 16, scale = 2)
    private BigDecimal credit;

    @Column(precision = 16, scale = 2)
    private BigDecimal debit;

    @Column(length = 100)
    private String particulars;

    @Temporal(TemporalType.DATE)
    @Column(name = "POSTING_DATE")
    private Date postingDate;

    @Column(name = "REF_NAME", length = 100)
    private String refName;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "WORKING_DATE")
    private Date workingDate;

    // bi-directional many-to-one association to ShrVcrInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOUCHER_ID")
    private Voucher shrVcrInfo;

}