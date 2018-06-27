package hk.oro.iefas.domain.dividend.dto;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import hk.oro.iefas.domain.shroff.dto.ChequeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (Thu, 24 May 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DividendChequeDTO extends TxnDTO {
    private Integer dividendChequeId;
    private ChequeDTO cheque;
    private String reissuesReason;
    private DividendScheduleItemDTO dividendScheduleItem;
    private String status;

}
