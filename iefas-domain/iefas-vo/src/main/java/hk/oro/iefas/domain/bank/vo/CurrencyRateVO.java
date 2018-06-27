package hk.oro.iefas.domain.bank.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.core.constant.ActionKey;
import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1088 $ $Date: 2018-02-08 11:15:43 +0800 (週四, 08 二月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRateVO extends TxnVO {

    private Integer curcyRateId;

    private Integer curcyId;

    private Date effectiveFrom;

    private Date effectiveTo;

    private BigDecimal rateAmount;

    private String status;

    private ActionKey actionKey;

}
