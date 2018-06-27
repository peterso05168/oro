package hk.oro.iefas.domain.bank.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @version $Revision: 981 $ $Date: 2018-01-31 10:26:00 +0800 (週三, 31 一月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CurrencySearchVO {

    private Integer curcyId;
    private String currencyCode;
    private String currencyName;
    private String status;
}
