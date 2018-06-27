package hk.oro.iefas.domain.shroff.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hk.oro.iefas.domain.bank.entity.Currency;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_CHEQUE database table.
 * 
 */
@Entity
@Table(name = "SHR_CHEQUE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrCheque extends UpdateTxnKeyable {

    @Id
    @Column(name = "CHEQUE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CHEQUE_SEQ")
    @TableGenerator(name = "CHEQUE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "CHEQUE_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer chequeId;

    @Column(name = "CHEQUE_AMOUNT", precision = 16, scale = 2)
    private BigDecimal chequeAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "CHEQUE_DATE")
    private Date chequeDate;

    @Column(name = "CHEQUE_NO")
    private String chequeNo;

    @Column(name = "CHEQUE_TYPE_ID", precision = 15)
    private Integer chequeTypeId;

    @Column(name = "GROUP_A_APPROVER_ID", precision = 15)
    private Integer groupaApproverId;

    @Column(name = "GROUP_B_APPROVER_ID", precision = 15)
    private Integer groupbApproverId;

    @Column(name = "BANK_CODE")
    private String bankCode;

    @ManyToOne
    @JoinColumn(name = "CURCY_ID")
    private Currency curcy;

    @Column(name = "DEBTOR", length = 100)
    private String debtor;

    @Column(name = "FILE_ITEM_NO", precision = 15)
    private Integer fileItemNo;

    @Column(name = "PAYEE", length = 100)
    private String payee;

    @Temporal(TemporalType.DATE)
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Column(name = "PARENT_CHEQUE_ID", precision = 15)
    private Integer parentChequeId;

    @Column(name = "PAYMENT_FILE_ID", precision = 15)
    private Integer paymentFileId;

    @Temporal(TemporalType.DATE)
    @Column(name = "RECEIPT_DATE")
    private Date receiptDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "REVERSAL_DATE")
    private Date reversalDate;

    @Column(name = "REMARK", length = 200)
    private String remark;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to CaseInfo
    @ManyToOne
    @JoinColumn(name = "CASE_ID")
    private Case caseInfo;

    // bi-directional many-to-one association to ShrBulkRvlItem
    @OneToMany(mappedBy = "bulkReversalItemId", fetch = FetchType.LAZY)
    private List<ShrBulkRvlItem> shrBulkRvlItems;

    // bi-directional many-to-one association to ShrVcrInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOUCHER_ID")
    private Voucher shrVcrInfo;

    // bi-directional many-to-one association to ShrVcrItem
    @OneToMany(mappedBy = "voucherItemId", fetch = FetchType.LAZY)
    private List<ShrVcrItem> shrVcrItems;

}