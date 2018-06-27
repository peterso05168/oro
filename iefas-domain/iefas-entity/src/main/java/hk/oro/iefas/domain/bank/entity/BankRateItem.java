package hk.oro.iefas.domain.bank.entity;

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
@Table(name = "BANK_BANK_RATE_ITEM")
public class BankRateItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "BANK_RATE_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_RATE_ITEM_SEQ")
    @TableGenerator(name = "BANK_RATE_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BANK_RATE_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer bankRateItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_INFO_ID")
    private BankInfo bankInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_DEPOSIT_TYPE_ID")
    private BankDepositType bankDepositType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURCY_ID")
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_RATE_ID")
    private BankRate bankRate;

    @Column(name = "INVEST_DATE")
    private Date investDate;

    @Column(name = "PERCENTAGE", length = 16, scale = 4)
    private BigDecimal percentage;

    @Column(name = "STATUS", length = 3)
    private String status;

}
