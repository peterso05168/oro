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

import hk.oro.iefas.domain.bank.entity.Currency;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_PAYMENT_FILE database table.
 * 
 */
@Entity
@Table(name = "SHR_PAYMENT_FILE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrPaymentFile extends UpdateTxnKeyable {

    @Id
    @Column(name = "PAYMENT_FILE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PAYMENT_FILE_SEQ")
    @TableGenerator(name = "PAYMENT_FILE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "PAYMENT_FILE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long paymentFileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURCY_ID")
    private Currency curcy;

    @Column(name = "GENERATED_FILE", precision = 15)
    private BigDecimal generatedFile;

    @Column(name = "PROCESS_DATE")
    private Date processDate;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "TOTAL_AMOUNT", precision = 16, scale = 2)
    private BigDecimal totalAmount;

    // bi-directional many-to-one association to ShrPaymentType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_FILE_TYPE_ID")
    private PaymentFileType paymentFileType;

}