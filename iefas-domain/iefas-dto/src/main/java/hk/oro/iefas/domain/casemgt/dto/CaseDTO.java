package hk.oro.iefas.domain.casemgt.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeDTO;
import hk.oro.iefas.domain.organization.dto.PostDTO;
import hk.oro.iefas.domain.shroff.dto.VoucherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3119 $ $Date: 2018-06-13 17:28:06 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CaseDTO extends TxnDTO {

    private Integer caseId;
    private CaseTypeDTO caseType;
    private String caseName;
    private String caseNo;
    private String caseYear;
    private Date bringUpDate;
    private Date openingDate;
    private Date closingDate;
    private BigDecimal estateBalance;
    private PostDTO post;
    private OutsiderDTO outsider;
    private CaseFeeDTO caseFee;
    private Integer noOfCreditor;
    private VoucherDTO orFeeVoucher;
    private VoucherDTO remitVoucher;
    private String wholeCaseNo;
    private String status;
}
