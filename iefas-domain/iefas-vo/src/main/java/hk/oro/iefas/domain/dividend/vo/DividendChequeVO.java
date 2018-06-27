package hk.oro.iefas.domain.dividend.vo;

import hk.oro.iefas.domain.core.vo.TxnVO;
import hk.oro.iefas.domain.shroff.vo.ChequeVO;
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
public class DividendChequeVO extends TxnVO {
    private Integer dividendChequeId;
    private ChequeVO cheque;
    private String reissuesReason;
    private DividendScheduleItemVO dividendScheduleItem;
    private String status;
}
