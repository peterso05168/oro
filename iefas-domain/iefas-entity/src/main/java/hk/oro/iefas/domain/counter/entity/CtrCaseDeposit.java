package hk.oro.iefas.domain.counter.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hk.oro.iefas.domain.bank.entity.Currency;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.shroff.entity.ShrReceipt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the CTR_CASE_DEPOSIT database table.
 * 
 */
@Entity
@Table(name = "CTR_CASE_DEPOSIT")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CtrCaseDeposit extends UpdateTxnKeyable {

    @Id
    @Column(name = "DEPOSIT_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DEPOSIT_SEQ")
    @TableGenerator(name = "DEPOSIT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "DEPOSIT_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer depositId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    private Case caseInfo;

    @Column(name = "CHEQUE_NO", precision = 15)
    private BigDecimal chequeNo;

    @Column(name = "CONTACT_NO", length = 100)
    private String contactNo;

    @Column(name = "DEPOSIT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal depositAmount;

    @Column(name = "DEPOSIT_NO", length = 100)
    private String depositNo;

    @Column(length = 100)
    private String payer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURCY_ID")
    private Currency curcy;

    @Column(name = "PAYMENT_TYPE_ID", precision = 15)
    private Integer paymentTypeId;

    @Temporal(TemporalType.DATE)
    @Column(name = "RECEIVE_DATE")
    private Date receiveDate;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "VOUCHER_ID", precision = 15)
    private BigDecimal voucherId;

    // bi-directional many-to-one association to ShrReceipt
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIPT_ID")
    private ShrReceipt shrReceipt;

}