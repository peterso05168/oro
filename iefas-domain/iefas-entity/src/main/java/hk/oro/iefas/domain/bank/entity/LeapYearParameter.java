package hk.oro.iefas.domain.bank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @version $Revision: 2015 $ $Date: 2018-04-11 14:13:56 +0800 (週三, 11 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FUND_LEAP_YEAR_PARAM")
public class LeapYearParameter extends UpdateTxnKeyable {

    @Id
    @Column(name = "LEAP_YEAR_PARAM_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "LEAP_YEAR_PARAM_SEQ")
    @TableGenerator(name = "LEAP_YEAR_PARAM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "LEAP_YEAR_PARAM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer leapYearCalculationId;

    @Column(name = "BANK_INFO_ID")
    private Integer bankInfoId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "CURRENCY_ID")
    private Currency currency;

    @Column(name = "NUM_DAYS_IN_LEAP_YEAR", length = 15)
    private Integer numDaysInLeapYear;

    @Column(name = "NUM_DAYS_IN_NON_LEAP_YEAR", length = 15)
    private Integer numDaysInNonLeapYear;

    @Column(name = "STATUS")
    private String status;
}
