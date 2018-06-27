package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.casemgt.vo.CaseVO;
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
public class CaseFeeVO extends TxnVO {

    private Integer caseFeeId;
    private CaseFeeTypeVO caseFeeType;
    private CaseVO casemgt;
    private Integer scheduleId;
    private Date issuesDate;
    private BigDecimal feeAmount;
    private BigDecimal amountPaid;
    private String status;
}
