package hk.oro.iefas.domain.shroff.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.casemgt.dto.CaseAccountInfoDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @version $Revision: 3044 $ $Date: 2018-06-11 21:08:54 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptVoucherAccountItemDTO extends TxnDTO {
    private Integer voucherItemId;
    private Integer voucherItemNo;
    private CaseAccountInfoDTO account;
//    private Integer accountId;
//    private String accountNumber;
//    private String caseName;
    private String analysisCode;
    private Integer analysisCodeId;
    private String chequeNo;
    private Date chequeDate;
    private String nature;
    private String payerName;
    private BigDecimal voucherAmount;
    private String status;

    private String caseTypeCodeValue;
    private String caseNoValue;
    private String caseYearValue;
    private String accountTypeCodeValue;
}
