package hk.oro.iefas.domain.shroff.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @version $Revision: 2785 $ $Date: 2018-05-31 17:42:18 +0800 (週四, 31 五月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GeneralLedgerDTO extends TxnDTO {

    private Integer generalLedgerId;
    private String analysisCode;
    private BigDecimal balance;
    private Integer bankAcId;
    private Integer caseAcId;
    private String chequeNo;
    private Integer controlAcId;
    private BigDecimal credit;
    private BigDecimal debit;
    private String particulars;
    private Date postingDate;
    private String refName;
    private String status;
    private Date workingDate;
    private VoucherDTO shrVcrInfo;

}
