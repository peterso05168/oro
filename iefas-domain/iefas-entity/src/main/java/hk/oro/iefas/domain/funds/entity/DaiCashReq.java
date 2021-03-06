package hk.oro.iefas.domain.funds.entity;

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
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FUND_DAI_CASH_REQ")
public class DaiCashReq extends UpdateTxnKeyable {

    @Id
    @Column(name = "DAILY_CASH_RQMT_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DAILY_CASH_RQMT_SEQ")
    @TableGenerator(name = "DAILY_CASH_RQMT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DAILY_CASH_RQMT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer dailyCashRqmtId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVEST_TYPE_ID")
    private InvType investType;

    @Column(name = "INVEST_DATE")
    private Date investDate;

    @Column(name = "REQU_TOTAL_AMOUNT", length = 16, scale = 2)
    private BigDecimal requTotalAmount;

    @Column(name = "STATUS", length = 3)
    private String status;
}
