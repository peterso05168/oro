package hk.oro.iefas.domain.organization.vo;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2058 $ $Date: 2018-04-13 11:00:35 +0800 (週五, 13 四月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RankVO extends TxnVO {

    private Integer rankId;
    private String rankName;
    private String rankDesc;
    private Integer voucherLimit;
    private Integer paymentLimit;
    private String narrativeCode;
    private String status;
}
