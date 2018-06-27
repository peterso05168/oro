package hk.oro.iefas.domain.funds.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@RequiredArgsConstructor
public class DailyCashRequirementResultVO extends TxnVO {

    private Integer dailyCashRequirementId;
    private Date currentDate;
    private BigDecimal requiredTotalAmount;
    private String status;
    private List<DailyCashRequirementItemVO> dailyCashRequirementItems;

}
