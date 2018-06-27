package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.vo.TxnVO;
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
@NoArgsConstructor
@AllArgsConstructor
public class DividendParameterVO extends TxnVO {
    private long divParameterId;
    private BigDecimal maxAmount;
    private String nameInDivNotice;
    private BigDecimal paymentInterestRate;
    private BigDecimal provisionOddBalBank;
    private BigDecimal provisionOddBalLiqu;
    private BigDecimal sevrnMaxAmountPre;
    private BigDecimal sevrnMaxDayRate;
    private BigDecimal sevrnMaxMonRate;
    private BigDecimal sevrnMonFractionRateNom;
    private BigDecimal sevrnMonFractionRateDen;
    private BigDecimal sevrnPeriodWoPay;
    private BigDecimal sevrnDaiFractionRate;
    private BigDecimal sevrnMaxAmount;
    private String status;
    private String telInDivNotice;
    private BigDecimal wilonPeriodDay;
    private BigDecimal wilonPeriodMon;
}
