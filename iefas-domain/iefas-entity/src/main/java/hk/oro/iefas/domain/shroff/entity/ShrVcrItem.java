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

import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_VCR_ITEM database table.
 * 
 */
@Entity
@Table(name = "SHR_VCR_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrVcrItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "VOUCHER_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "VOUCHER_ITEM_SEQ")
    @TableGenerator(name = "VOUCHER_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "VOUCHER_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer voucherItemId;

    @Column(name = "ANALYSIS_CODE", length = 7)
    private String analysisCode;

    @Column(name = "ANALYSIS_CODE_ID", precision = 15)
    private Integer analysisCodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_AC_ID")
    private CaseAccountInfo caseAccount;

    @Temporal(TemporalType.DATE)
    @Column(name = "CHEQUE_DATE")
    private Date chequeDate;

    @Column(name = "CHEQUE_NO")
    private String chequeNo;

    @Column(name = "DEBIT_CREDIT", length = 2)
    private String debitCredit;

    @Column(length = 100)
    private String nature;

    @Column(length = 100)
    private String receiver;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "VOUCHER_AMOUNT", precision = 16, scale = 2)
    private BigDecimal voucherAmount;

    @Column(name = "VOUCHER_ID", precision = 15)
    private Integer voucherId;

    @Column(name = "VOUCHER_ITEM_NO", precision = 15)
    private Integer voucherItemNo;

    // bi-directional many-to-one association to ShrCheque
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHEQUE_ID")
    private ShrCheque shrCheque;

}