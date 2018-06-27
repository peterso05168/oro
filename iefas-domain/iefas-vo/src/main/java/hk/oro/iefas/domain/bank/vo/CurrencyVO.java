package hk.oro.iefas.domain.bank.vo;

import java.util.List;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 981 $ $Date: 2018-01-31 10:26:00 +0800 (週三, 31 一月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyVO extends TxnVO {

    private Integer curcyId;

    private String curcyName;

    private String curcyCode;

    private String status;

    private List<CurrencyRateVO> currencyRates;
}
