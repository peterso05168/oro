package hk.oro.iefas.domain.bank.vo;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountInfoResultVO extends TxnVO {

    private Integer bankInfoId;
    private String bankName;
    private String bankShortName;
    private String bankCode;
    private BigDecimal avaiableRoom;
    private String status;

}
