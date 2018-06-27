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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "DIV_SCHEDULE_DIST")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DivScheduleDist extends UpdateTxnKeyable {

    @Id
    @Column(name = "SCHEDULE_DIST_ID", unique = true, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DIV_SCHEDULE_DIST_SEQ")
    @TableGenerator(name = "DIV_SCHEDULE_DIST_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DIV_SCHEDULE_DIST_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer scheduleDistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIV_SCHEDULE_ITEM_ID")
    private DivScheduleItem divScheduleItem;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APP_RESULT_ITEM_ID")
    private AppAdjResultItem appAdjResultItem;

    @Column(name = "AMOUNT_PAID", precision = 16)
    private BigDecimal amountPaid;

    @Column(name = "PERCENT_PAID", precision = 16)
    private BigDecimal percentPaid;

    @Column(name = "STATUS")
    private String status;
}
