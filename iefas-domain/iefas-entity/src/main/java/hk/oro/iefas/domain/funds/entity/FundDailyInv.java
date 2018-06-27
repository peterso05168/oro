package hk.oro.iefas.domain.funds.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hk.oro.iefas.domain.bank.entity.Currency;
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
@Table(name = "FUND_DAILY_INV")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class FundDailyInv extends UpdateTxnKeyable {

    @Id
    @Column(name = "DAILY_INVEST_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DAILY_INVEST_SEQ")
    @TableGenerator(name = "DAILY_INVEST_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DAILY_INVEST_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long dailyInvestId;

    @Temporal(TemporalType.DATE)
    @Column(name = "INVEST_DATE")
    private Date investDate;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to BankCurrency
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID")
    private Currency bankCurrency;

}