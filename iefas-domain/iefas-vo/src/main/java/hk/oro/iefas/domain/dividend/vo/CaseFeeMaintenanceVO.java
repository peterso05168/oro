package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CaseFeeMaintenanceVO extends TxnVO {

    private Integer caseFeeMaintenanceId;
    private String calculationType;
    private String caseFeeTypeName;
    private Date periodFrom;
    private Date periodTo;
    private BigDecimal feeAmount;
    private BigDecimal percent;
    private Integer valueFrom;
    private Integer valueTo;
    private BigDecimal valuePercent;
    private Integer roundingUnit;
    private Integer roundingAmount;
    private String status;

}
