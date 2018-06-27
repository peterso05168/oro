package hk.oro.iefas.domain.shroff.entity;

import java.io.Serializable;
import javax.persistence.*;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the SHR_BULK_VCR_ITEM database table.
 * 
 */
@Entity
@Table(name = "SHR_BULK_VCR_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrBulkVcrItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "BULK_VOUCHER_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BULK_VOUCHER_ITEM_SEQ")
    @TableGenerator(name = "BULK_VOUCHER_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BULK_VOUCHER_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long bulkVoucherItemId;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "VOUCHER_ID", precision = 15)
    private BigDecimal voucherId;

    // bi-directional many-to-one association to ShrBulkVcr
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BULK_VOUCHER_ID")
    private ShrBulkVcr shrBulkVcr;

}