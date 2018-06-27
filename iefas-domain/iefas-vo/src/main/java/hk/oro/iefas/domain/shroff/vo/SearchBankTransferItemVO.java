package hk.oro.iefas.domain.shroff.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3030 $ $Date: 2018-06-11 18:10:24 +0800 (週一, 11 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchBankTransferItemVO {
    private String voucherNumber;
    private Date transferDate;
    private String fromAccount;
    private String toAccount;
    private String currencyCode;
}
