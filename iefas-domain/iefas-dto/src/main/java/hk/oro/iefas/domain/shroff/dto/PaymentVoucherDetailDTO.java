package hk.oro.iefas.domain.shroff.dto;

import java.util.List;

import hk.oro.iefas.domain.common.dto.ActionDTO;
import hk.oro.iefas.domain.dividend.dto.ApproveHistoryDTO;
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
public class PaymentVoucherDetailDTO {

    private PaymentVoucherBasicInformationDTO paymentVoucherBasicInformation;
    private List<PaymentVoucherAccountItemDTO> paymentVoucherItems;
    private List<ApproveHistoryDTO> approveHistories;
    private ActionDTO action;

}
