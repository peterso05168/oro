package hk.oro.iefas.domain.funds.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity
@Table(name = "FUND_DAILY_INV_INT")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class FundDailyInvInt extends UpdateTxnKeyable {

    @Id
    @Column(name = "DAILY_INVEST_INT_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DAILY_INVEST_INT_SEQ")
    @TableGenerator(name = "DAILY_INVEST_INT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DAILY_INVEST_INT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long dailyInvestIntId;

    @Column(name = "INV_INTE_AMOUNT", precision = 16, scale = 2)
    private BigDecimal invInteAmount;

    @Column(name = "STATUS", length = 3)
    private String status;

}