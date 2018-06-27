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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

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
@Table(name = "SHR_VCR_INFO")
public class Voucher extends UpdateTxnKeyable {

    @Id
    @Column(name = "VOUCHER_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "VOUCHER_SEQ")
    @TableGenerator(name = "VOUCHER_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "VOUCHER_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer voucherId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOUCHER_TYPE_ID")
    private VoucherType voucherType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOURNAL_TYPE_ID")
    private JournalType journalType;

    @Column(name = "FILE_REF", length = 100)
    private String fileRef;

    @Column(name = "GROUP_CODE", length = 100)
    private String groupCode;

    @Column(name = "VOUCHER_NO", length = 100)
    private String voucherNo;

    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;

    @Column(name = "RECEIVE_METHOD", length = 100)
    private String receiveMethod;

    @Column(name = "VOUCHER_DATE")
    private Date voucherDate;

    @Column(name = "SUBMISSION_DATE")
    private Date submissionDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_TYPE_ID")
    private PaymentType paymentType;

    @Column(name = "VOUCHER_TOTAL_AMOUNT", length = 16, scale = 2)
    private BigDecimal voucherTotalAmount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTL_AC_ID")
    private ShrCtlAc controlAccount;

    @Column(name = "PREPARER_ID")
    private Integer preparerId;

    @Column(name = "FIRST_APPROVER_ID")
    private Integer firstApproverId;

    @Column(name = "SECOND_APPROVER_ID")
    private Integer secondApproverId;

    @Column(name = "PAYMENT_VERIFIER_ID")
    private Integer paymentVerifierId;

    @Column(name = "PAYMENT_APPROVER_NAME", length = 100)
    private String paymentApproverName;

    @Column(name = "WORKFLOW_ID")
    private Integer workflowId;

    @Column(name = "CANCEL_DATE")
    private Date cancelDate;

    @Column(name = "REMARK", length = 300)
    private String remark;

    @Column(name = "IS_HARD_COPY")
    private Boolean isHardCopy;

    @Column(name = "BRING_UP_DATE")
    private Date bringUpDate;

    @Column(name = "CURCY_ID")
    private Integer currencyId;

    @Column(name = "PAYEE_NAME", length = 300)
    private String payeeName;

    @Column(name = "REALIZATION_FEE")
    private String realizationFee;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "voucherId", fetch = FetchType.LAZY)
    private List<ShrVcrItem> shrVcrItems;

}
