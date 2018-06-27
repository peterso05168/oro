package hk.oro.iefas.domain.shroff.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.casemgt.vo.CaseAccountInfoVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @version $Revision: 3045 $ $Date: 2018-06-11 21:12:02 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptVoucherAccountItemVO extends TxnVO {

    private Integer voucherItemId;
    private Integer voucherItemNo;
    private CaseAccountInfoVO account;
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
