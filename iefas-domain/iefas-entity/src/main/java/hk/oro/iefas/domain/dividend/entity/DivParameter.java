package hk.oro.iefas.domain.dividend.entity;

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
@Table(name = "DIV_DIV_PARAMETER")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivParameter extends UpdateTxnKeyable {

    @Id
    @Column(name = "DIV_PARAMETER_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DIV_PARAMETER_SEQ")
    @TableGenerator(name = "DIV_PARAMETER_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DIV_PARAMETER_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long divParameterId;

    @Column(name = "MAX_AMOUNT", precision = 16, scale = 2)
    private BigDecimal maxAmount;

    @Column(name = "NAME_IN_DIV_NOTICE", length = 60)
    private String nameInDivNotice;

    @Column(name = "PAYMENT_INTEREST_RATE", precision = 16, scale = 5)
    private BigDecimal paymentInterestRate;

    @Column(name = "PROVISION_ODD_BAL_BANK", precision = 16, scale = 2)
    private BigDecimal provisionOddBalBank;

    @Column(name = "PROVISION_ODD_BAL_LIQU", precision = 16, scale = 2)
    private BigDecimal provisionOddBalLiqu;

    @Column(name = "SEVRN_MAX_AMOUNT_PRE", precision = 16, scale = 2)
    private BigDecimal sevrnMaxAmountPre;

    @Column(name = "SEVRN_MAX_DAY_RATE", precision = 16, scale = 5)
    private BigDecimal sevrnMaxDayRate;

    @Column(name = "SEVRN_MAX_MON_RATE", precision = 16, scale = 5)
    private BigDecimal sevrnMaxMonRate;

    @Column(name = "SEVRN_MON_FRACTION_RATE_NOM", precision = 15)
    private BigDecimal sevrnMonFractionRateNom;

    @Column(name = "SEVRN_MON_FRACTION_RATE_DEN", precision = 15)
    private BigDecimal sevrnMonFractionRateDen;

    @Column(name = "SEVRN_PERIOD_WO_PAY", precision = 15)
    private BigDecimal sevrnPeriodWoPay;

    @Column(name = "SEVRN_DAI_FRACTION_RATE", precision = 15)
    private BigDecimal sevrnDaiFractionRate;

    @Column(name = "SEVRN_MAX_AMOUNT", precision = 16, scale = 2)
    private BigDecimal sevrnMaxAmount;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "TEL_IN_DIV_NOTICE", length = 20)
    private String telInDivNotice;

    @Column(name = "WILON_PERIOD_DAY", precision = 15)
    private BigDecimal wilonPeriodDay;

    @Column(name = "WILON_PERIOD_MON", precision = 15)
    private BigDecimal wilonPeriodMon;

}