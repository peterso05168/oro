package hk.oro.iefas.domain.shroff.dto;

import java.math.BigDecimal;

import hk.oro.iefas.domain.casemgt.dto.CaseAccountInfoDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2794 $ $Date: 2018-05-31 18:17:53 +0800 (週四, 31 五月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVoucherAccountItemDTO extends TxnDTO {

    private Integer voucherItemId;
    private Integer voucherItemNo;
    private CaseAccountInfoDTO account;
    private String analysisCode;
    private Integer analysisCodeId;
    private String nature;
    private BigDecimal amount;
    private String status;
    private String caseTypeCodeValue;
    private String caseNoValue;
    private String caseYearValue;
    private String accountTypeCodeValue;
}
