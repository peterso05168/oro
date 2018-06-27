package hk.oro.iefas.domain.shroff.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @version $Revision$ $Date$
 * @author $Author$
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptVoucherSearchDTO {
    private String receitpVoucherTypeCode;

    private String voucherNo;
    private Date voucherDate;
    private String groupCode;

    private String caseTypeCodeValue;
    private String caseNoValue;
    private String caseYearValue;
    private String accountTypeCodeValue;

    private String analysisCode;
    private String status;
}
