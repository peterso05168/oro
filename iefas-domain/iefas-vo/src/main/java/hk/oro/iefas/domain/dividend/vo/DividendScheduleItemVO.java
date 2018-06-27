package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3149 $ $Date: 2018-06-14 20:04:03 +0800 (週四, 14 六月 2018) $
 * @author $Author: noah.liang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DividendScheduleItemVO extends TxnVO {

    private Integer dividendScheduleItemId;
    private CreditorVO creditor;
    private String natureOfClaim;
    private BigDecimal distributionPercentage;
    private BigDecimal distributionAmount;
    private BigDecimal withheldAmount;
    private WithheldReasonVO withheldReason;
    private AdjucationResultVO adjResult;
    private String status;
    private Date paymentEffectiveDate;
    private String voucherPart;
    private List<DividendScheduleDistVO> dividendScheduleDistList;
    private BigDecimal totalClaimAmount;

}
