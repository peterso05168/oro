/**
 * 
 */
package hk.oro.iefas.ws.shroff.repository.assembler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.dividend.dto.ApproveHistoryDTO;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.dividend.repository.assembler.ApproveHistoryDTOAssembler;

/**
 * @version $Revision: 2903 $ $Date: 2018-06-05 16:35:10 +0800 (週二, 05 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class JournalVoucherDetailDTOAssembler {

    @Autowired
    private JournalVoucherBasicInformationDTOAssembler journalVoucherBasicInformationDTOAssembler;

    @Autowired
    private JournalVoucherAccountItemDTOAssembler journalVoucherAccountItemDTOAssembler;

    @Autowired
    private ApproveHistoryDTOAssembler approveHistoryDTOAssembler;

    public JournalVoucherDetailDTO toDTO(Voucher voucher, List<ShrVcrItem> shrVcrItems,
        List<SysApprovalWf> approvalWorkflows) {

        if (voucher != null) {
            JournalVoucherDetailDTO journalVoucherDetailDTO = new JournalVoucherDetailDTO();
            journalVoucherDetailDTO
                .setJournalVoucherBasicInformation(journalVoucherBasicInformationDTOAssembler.toDTO(voucher));
            if (CommonUtils.isNotEmpty(shrVcrItems)) {
                List<JournalVoucherAccountItemDTO> journalVoucherAccountItems
                    = journalVoucherAccountItemDTOAssembler.toDTOList(shrVcrItems);
                journalVoucherDetailDTO.setJournalVoucherAccountItems(journalVoucherAccountItems);
            }

            if (CommonUtils.isNotEmpty(approvalWorkflows)) {
                List<ApproveHistoryDTO> approveHistoryDTOs = approveHistoryDTOAssembler.toDTOList(approvalWorkflows);
                journalVoucherDetailDTO.setApproveHistories(approveHistoryDTOs);
            }

            return journalVoucherDetailDTO;
        }
        return null;
    }
}
