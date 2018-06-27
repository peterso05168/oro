package hk.oro.iefas.domain.shroff.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2579 $ $Date: 2018-05-24 16:32:04 +0800 (週四, 24 五月 2018) $
 * @author $Author: garrett.han $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchChequeFileDTO {
    private String voucherNumber;
    private Date chequeDate;
    private String toAccount;
    private Integer currencyId;
}
