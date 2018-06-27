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
 * The persistent class for the SHR_BANK_TXF_ITEM database table.
 * 
 */
@Entity
@Table(name = "SHR_BANK_TXF_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrBankTxfItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "BANK_TRANSFER_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_TRANSFER_ITEM_SEQ")
    @TableGenerator(name = "BANK_TRANSFER_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BANK_TRANSFER_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer bankTransferItemId;

    @Column(name = "BATCH_ID", precision = 15)
    private Integer batchId;

    @Column(name = "BENEFICIARY_AC", length = 35)
    private String beneficiaryAc;

    @Column(name = "BENEFICIARY_REF", length = 100)
    private String beneficiaryRef;

    @Column(name = "CURCY_CODE", length = 3)
    private String curcyCode;

    @Column(name = "DEBIT_AC", length = 35)
    private String debitAc;

    @Column(name = "FROM_AC_ID", precision = 15)
    private Integer fromAcId;

    @Column(name = "INSTRUCTION_REF_NO", precision = 15)
    private Integer instructionRefNo;

    @Column(name = "ITEM_NO", precision = 15)
    private Integer itemNo;

    @Column(name = "ORIGINAOR_REF", length = 100)
    private String originaorRef;

    @Temporal(TemporalType.DATE)
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Column(name = "PAYMENT_FILE_ID", precision = 15)
    private Integer paymentFileId;

    @Column(name = "REJECTION_REASON", length = 60)
    private String rejectionReason;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "TO_AC_BANK_ID", length = 100)
    private String toAcBankId;

    @Column(name = "TO_AC_NAME", length = 100)
    private String toAcName;

    @Column(name = "TO_AC_NO", precision = 15)
    private Integer toAcNo;

    @Column(name = "TRANSFER_AMOUNT", precision = 16, scale = 2)
    private BigDecimal transferAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "TRANSFER_DATE")
    private Date transferDate;

    @Column(name = "VOUCHER_ITEM_ID", precision = 15)
    private Integer voucherItemId;

    // bi-directional many-to-one association to ShrPaymentType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_TYPE_ID")
    private PaymentType shrPaymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOUCHER_ID")
    private Voucher voucher;
}