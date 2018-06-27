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

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_TXF_GR_BEA database table.
 * 
 */
@Entity
@Table(name = "SHR_TXF_GR_BEA")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrTxfGrBea extends UpdateTxnKeyable {

    @Id
    @Column(name = "TRANSFER_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TRANSFER_SEQ")
    @TableGenerator(name = "TRANSFER_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "TRANSFER_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer transferId;

    @Column(name = "TRANSFER_NO", length = 13)
    private String transferNo;

    @Column(name = "CUT_OFF_DATE")
    private Date cutOffDate;

    @Column(name = "PROCESS_DATE")
    private Date processDate;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "TOTAL_AMOUNT", precision = 16, scale = 2)
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANSFER_TYPE_ID")
    private ShrTxfAmountType transferType;

    // bi-directional many-to-one association to ShrVcrInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_VOUCHER_ID")
    private Voucher voucher;

    // bi-directional many-to-one association to ShrTxfGrBeaItem
    @OneToMany(mappedBy = "transferItemId", fetch = FetchType.LAZY)
    private List<ShrTxfGrBeaItem> shrTxfGrBeaItems;

}