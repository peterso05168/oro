package hk.oro.iefas.domain.release.dto;

import java.util.List;

import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherBasicInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @version $Revision: 2655 $ $Date: 2018-05-28 10:42:05 +0800 (Mon, 28 May 2018) $
 * @author $Author: george.wu $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalCaseListDetailDTO {
    private RelHistListDTO historicalCaseListInformation;
    private List<RelHistListItemDTO> historicalCaseListDetailItems;
}
