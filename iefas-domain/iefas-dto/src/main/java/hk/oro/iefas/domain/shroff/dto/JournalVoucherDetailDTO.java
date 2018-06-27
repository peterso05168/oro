/**
 * 
 */
package hk.oro.iefas.domain.shroff.dto;

import java.util.List;

import hk.oro.iefas.domain.common.dto.ActionDTO;
import hk.oro.iefas.domain.dividend.dto.ApproveHistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3143 $ $Date: 2018-06-14 18:20:23 +0800 (週四, 14 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalVoucherDetailDTO {

    private JournalVoucherBasicInformationDTO journalVoucherBasicInformation;
    private List<JournalVoucherAccountItemDTO> journalVoucherAccountItems;
    private List<ApproveHistoryDTO> approveHistories;
    private ActionDTO action;

}
