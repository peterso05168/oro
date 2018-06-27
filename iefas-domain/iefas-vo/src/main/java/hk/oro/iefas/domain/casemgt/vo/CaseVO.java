package hk.oro.iefas.domain.casemgt.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.common.vo.CaseTypeVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
import hk.oro.iefas.domain.dividend.vo.CaseFeeVO;
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.domain.shroff.vo.VoucherVO;
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
public class CaseVO extends TxnVO {

    private Integer caseId;
    private CaseTypeVO caseType;
    private String caseName;
    private String caseNo;
    private String caseYear;
    private Date bringUpDate;
    private Date openingDate;
    private Date closingDate;
    private BigDecimal estateBalance;
    private PostVO post;
    private OutsiderVO outsider;
    private CaseFeeVO caseFee;
    private Integer noOfCreditor;
    private VoucherVO orFeeVoucher;
    private VoucherVO remitVoucher;
    private String wholeCaseNo;
    private String status;
}
