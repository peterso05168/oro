package hk.oro.iefas.domain.dividend.entity;

import java.math.BigDecimal;

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
import hk.oro.iefas.domain.shroff.entity.Voucher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
@Entity
@Table(name = "DIV_SCHEDULE_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivScheduleItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "DIV_SCHEDULE_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DIV_SCHEDULE_ITEM_SEQ")
    @TableGenerator(name = "DIV_SCHEDULE_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DIV_SCHEDULE_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer divScheduleItemId;

    @Column(name = "AGREED_AMOUNT", precision = 16, scale = 2)
    private BigDecimal agreedAmount;

    @Column(name = "DIST_AMOUNT", precision = 16, scale = 2)
    private BigDecimal distAmount;

    @Column(name = "DIST_PERCENT", precision = 16, scale = 5)
    private BigDecimal distPercent;

    @Column(name = "DIV_NOTICE", length = 100)
    private String divNotice;

    @Column(name = "VOUCHER_PART", length = 200)
    private String voucherPart;

    @Column(name = "WITHHELD_AMOUNT", precision = 16, scale = 2)
    private BigDecimal withheldAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WITHHELD_RSN_TYPE_ID")
    private DivWithheldReasonType divWithheldReasonType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIV_SCHD_ID")
    private DivSchedule divSchedule;

    @Column(name = "ADJ_RESULT_ID")
    private Integer adjResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOUCHER_ID")
    private Voucher voucher;

    @Column(name = "STATUS")
    private String status;
}