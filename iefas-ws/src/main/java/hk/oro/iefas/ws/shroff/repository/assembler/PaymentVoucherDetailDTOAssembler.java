package hk.oro.iefas.ws.shroff.repository.assembler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.dividend.dto.ApproveHistoryDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.dividend.repository.assembler.ApproveHistoryDTOAssembler;

/**
 * @version $Revision: 2970 $ $Date: 2018-06-06 23:23:14 +0800 (週三, 06 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class PaymentVoucherDetailDTOAssembler {

    @Autowired
    private PaymentVoucherBasicInformationDTOAssembler paymentVoucherBasicInformationDTOAssembler;

    @Autowired
    private PaymentVoucherAccountItemDTOAssembler paymentVoucherAccountItemDTOAssembler;

    @Autowired
    private ApproveHistoryDTOAssembler approveHistoryDTOAssembler;

    public PaymentVoucherDetailDTO toDTO(Voucher voucher, List<ShrVcrItem> shrVcrItems,
        List<SysApprovalWf> approvalWorkflows) {

        if (voucher != null) {
            PaymentVoucherDetailDTO paymentVoucherDetailDTO = new PaymentVoucherDetailDTO();
            paymentVoucherDetailDTO
                .setPaymentVoucherBasicInformation(paymentVoucherBasicInformationDTOAssembler.toDTO(voucher));
            if (CommonUtils.isNotEmpty(shrVcrItems)) {
                List<PaymentVoucherAccountItemDTO> paymentVoucherAccountItems
                    = paymentVoucherAccountItemDTOAssembler.toDTOList(shrVcrItems);
                paymentVoucherDetailDTO.setPaymentVoucherItems(paymentVoucherAccountItems);
            }

            if (CommonUtils.isNotEmpty(approvalWorkflows)) {
                List<ApproveHistoryDTO> approveHistoryDTOs = approveHistoryDTOAssembler.toDTOList(approvalWorkflows);
                paymentVoucherDetailDTO.setApproveHistories(approveHistoryDTOs);
            }
            return paymentVoucherDetailDTO;
        }
        return null;
    }
}
