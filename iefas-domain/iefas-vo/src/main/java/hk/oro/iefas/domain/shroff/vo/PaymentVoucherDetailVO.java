package hk.oro.iefas.domain.shroff.vo;

import java.util.List;

import hk.oro.iefas.domain.common.vo.ActionVO;
import hk.oro.iefas.domain.dividend.vo.ApproveHistoryVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3143 $ $Date: 2018-06-14 18:20:23 +0800 (週四, 14 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVoucherDetailVO {

    private PaymentVoucherBasicInformationVO paymentVoucherBasicInformation;
    private List<PaymentVoucherAccountItemVO> paymentVoucherItems;
    private List<ApproveHistoryVO> approveHistories;
    private ActionVO action;

}
