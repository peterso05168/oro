package hk.oro.iefas.domain.funds.entity;

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
 * @version $Revision: 3285 $ $Date: 2018-06-25 17:28:14 +0800 (週一, 25 六月 2018) $
 * @author $Author: noah.liang $
 */
@Entity
@Table(name = "FUND_DAILY_INV_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class FundDailyInvItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "DAILY_INVEST_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DAILY_INVEST_ITEM_SEQ")
    @TableGenerator(name = "DAILY_INVEST_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DAILY_INVEST_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer dailyInvestItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DAILY_INVEST_ID")
    private FundDailyInv fundDailyInv;

    // bi-directional many-to-one association to FundInvItem
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCE_INVEST_ITEM_ID")
    private InvItem sourceInvItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACEMENT_INVEST_ITEM_ID")
    private InvItem placementInvItem;

    @Column(name = "STATUS", length = 3)
    private String status;

}