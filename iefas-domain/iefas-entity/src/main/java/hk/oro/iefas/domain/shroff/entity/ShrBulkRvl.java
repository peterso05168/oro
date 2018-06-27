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
 * The persistent class for the SHR_BULK_RVL database table.
 * 
 */
@Entity
@Table(name = "SHR_BULK_RVL")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrBulkRvl extends UpdateTxnKeyable {

    @Id
    @Column(name = "BULK_REVERSAL_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BULK_REVERSAL_SEQ")
    @TableGenerator(name = "BULK_REVERSAL_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BULK_REVERSAL_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long bulkReversalId;

    @Temporal(TemporalType.DATE)
    @Column(name = "CUT_OFF_DATE")
    private Date cutOffDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "PROCESS_DATE")
    private Date processDate;

    @Column(length = 300)
    private String remark;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "TOTAL_AMOUNT", precision = 16, scale = 2)
    private BigDecimal totalAmount;

    // bi-directional many-to-one association to ShrVcrInfo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOUCHER_ID")
    private Voucher shrVcrInfo;
    
    @OneToMany(mappedBy = "shrBulkRvl", fetch = FetchType.LAZY)
    private List<ShrBulkRvlItem> shrBulkRvlItems;

}