package hk.oro.iefas.domain.shroff.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2604 $ $Date: 2018-05-25 10:59:21 +0800 (週五, 25 五月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVoucherSearchVO {

    private Integer paymentVoucherId;
    private String voucherNumber;
    private Date paymentDate;
    private String caseType;
    private String accountType;
    private String caseSeqNo;
    private String caseYear;
    private String accountNumber;
    private String analysisCode;
    private String status;

}
