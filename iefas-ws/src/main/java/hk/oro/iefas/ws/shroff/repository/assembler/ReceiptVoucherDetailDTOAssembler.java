package hk.oro.iefas.ws.shroff.repository.assembler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.dividend.dto.ApproveHistoryDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.dividend.repository.assembler.ApproveHistoryDTOAssembler;

/**
 * 
 * @version $Revision: 3063 $ $Date: 2018-06-11 21:26:48 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
@Component
public class ReceiptVoucherDetailDTOAssembler {
    @Autowired
    private ReceiptVoucherBasicInformationDTOAssembler receiptVoucherBasicInformationDTOAssembler;

    @Autowired
    private ReceiptVoucherAccountItemDTOAssembler receiptVoucherAccountItemDTOAssembler;
    
    @Autowired
    private ApproveHistoryDTOAssembler approveHistoryDTOAssembler;

    public ReceiptVoucherDetailDTO toDTO(Voucher voucher, List<ShrVcrItem> shrVcrItems, 
        List<SysApprovalWf> approvalWorkflows) {
        if (voucher != null) {
            ReceiptVoucherDetailDTO receiptVoucherDetailDTO = new ReceiptVoucherDetailDTO();
            receiptVoucherDetailDTO
                .setReceiptVoucherBasicInformation(receiptVoucherBasicInformationDTOAssembler.toDTO(voucher));
            if (CommonUtils.isNotEmpty(shrVcrItems)) {
                List<ReceiptVoucherAccountItemDTO> receiptVoucherAccountItems
                    = receiptVoucherAccountItemDTOAssembler.toDTOList(shrVcrItems);
                receiptVoucherDetailDTO.setReceiptVoucherAccountItems(receiptVoucherAccountItems);
            }
            if (CommonUtils.isNotEmpty(approvalWorkflows)) {
                List<ApproveHistoryDTO> approveHistoryDTOs = this.approveHistoryDTOAssembler.toDTOList(approvalWorkflows);
                receiptVoucherDetailDTO.setApproveHistories(approveHistoryDTOs);
            }
            return receiptVoucherDetailDTO;
        }
        return null;
    }
}
