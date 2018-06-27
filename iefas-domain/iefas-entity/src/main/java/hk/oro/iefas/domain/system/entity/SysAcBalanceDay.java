package hk.oro.iefas.domain.system.entity;

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
 * The persistent class for the SYS_AC_BALANCE_DAY database table.
 * 
 */
@Entity
@Table(name = "SYS_AC_BALANCE_DAY")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SysAcBalanceDay {

    @Id
    @Column(name = "DAILY_AC_BALANCE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DAILY_AC_BALANCE_SEQ")
    @TableGenerator(name = "DAILY_AC_BALANCE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DAILY_AC_BALANCE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long dailyAcBalanceId;

    @Column(name = "AC_ID", precision = 15)
    private BigDecimal acId;

    @Column(name = "ACCOUNT_TYPE", length = 3)
    private String accountType;

    @Temporal(TemporalType.DATE)
    @Column(name = "ACTION_DATE")
    private Date actionDate;

    @Column(name = "BALANCE_DAY", precision = 15)
    private BigDecimal balanceDay;

    @Column(name = "BALANCE_MONTH", precision = 15)
    private BigDecimal balanceMonth;

    @Column(name = "BALANCE_YEAR", precision = 15)
    private BigDecimal balanceYear;

    @Column(name = "INVESTMENT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal investmentAmount;

    @Column(name = "LIQUID_CASH_AMOUNT", precision = 16, scale = 2)
    private BigDecimal liquidCashAmount;

    @Column(name = "ON_HOLD_AMOUNT_CR", precision = 16, scale = 2)
    private BigDecimal onHoldAmountCr;

    @Column(name = "ON_HOLD_AMOUNT_DR", precision = 16, scale = 2)
    private BigDecimal onHoldAmountDr;

}