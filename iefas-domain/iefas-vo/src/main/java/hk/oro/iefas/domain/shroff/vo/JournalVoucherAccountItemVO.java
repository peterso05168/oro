/**
 * 
 */
package hk.oro.iefas.domain.shroff.vo;

import java.math.BigDecimal;

import hk.oro.iefas.domain.casemgt.vo.CaseAccountInfoVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2888 $ $Date: 2018-06-04 22:28:27 +0800 (週一, 04 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class JournalVoucherAccountItemVO extends TxnVO {

    private Integer voucherItemId;
    private Integer voucherItemNo;
    private CaseAccountInfoVO account;
    private String caseName;
    private String analysisCode;
    private Integer analysisCodeId;
    private String particulars;
    private BigDecimal amountDr;
    private BigDecimal amountCr;
    private String status;
    private String caseTypeCodeValue;
    private String caseNoValue;
    private String caseYearValue;
    private String accountTypeCodeValue;
}
