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

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_BULK_VCR database table.
 * 
 */
@Entity
@Table(name = "SHR_BULK_VCR")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrBulkVcr extends UpdateTxnKeyable {

    @Id
    @Column(name = "BULK_VOUCHER_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BULK_VOUCHER_SEQ")
    @TableGenerator(name = "BULK_VOUCHER_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BULK_VOUCHER_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long bulkVoucherId;

    @Temporal(TemporalType.DATE)
    @Column(name = "IMPORT_DATE")
    private Date importDate;

    @Column(name = "IMPORT_FILE", precision = 15)
    private BigDecimal importFile;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to ShrVcrType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOUCHER_TYPE_ID")
    private VoucherType shrVcrType;

    // bi-directional many-to-one association to ShrBulkVcrItem
    @OneToMany(mappedBy = "bulkVoucherItemId", fetch = FetchType.LAZY)
    private List<ShrBulkVcrItem> shrBulkVcrItems;

}