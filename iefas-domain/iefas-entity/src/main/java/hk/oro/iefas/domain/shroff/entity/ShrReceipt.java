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

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_RECEIPT database table.
 * 
 */
@Entity
@Table(name = "SHR_RECEIPT")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrReceipt extends UpdateTxnKeyable {

    @Id
    @Column(name = "RECEIPT_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RECEIPT_SEQ")
    @TableGenerator(name = "RECEIPT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "RECEIPT_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer receiptId;

    @Column(name = "CHEQUE_NO", precision = 15)
    private BigDecimal chequeNo;

    @Column(name = "CONTACT_NO", length = 100)
    private String contactNo;

    @Column(name = "PAYER_NAME", length = 100)
    private String payerName;

    @Column(name = "PAYMENT_TYPE_ID", precision = 15)
    private BigDecimal paymentTypeId;

    @Column(name = "RECEIPT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal receiptAmount;

    @Column(name = "RECEIPT_NO", length = 100)
    private String receiptNo;

    @Column(name = "RECEIVE_DATE")
    private Date receiveDate;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to ShrVcrInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOUCHER_ID")
    private Voucher shrVcrInfo;

}