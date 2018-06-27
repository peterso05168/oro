package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
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
public class DividendScheduleItemDTO extends TxnDTO {

    private Integer dividendScheduleItemId;
    private CreditorDTO creditor;
    private String natureOfClaim;
    private BigDecimal distributionPercentage;
    private BigDecimal distributionAmount;
    private BigDecimal withheldAmount;
    private WithheldReasonDTO withheldReason;
    private AdjucationResultDTO adjResult;
    private String status;
    private Date paymentEffectiveDate;
    private String voucherPart;
    private List<DividendScheduleDistDTO> dividendScheduleDistList;
    //--------------- Page parameters -----------
    private BigDecimal totalClaimAmount;
}
