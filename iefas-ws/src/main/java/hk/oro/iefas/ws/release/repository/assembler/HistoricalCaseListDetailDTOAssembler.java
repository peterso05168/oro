package hk.oro.iefas.ws.release.repository.assembler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.release.dto.HistoricalCaseListDetailDTO;
import hk.oro.iefas.domain.release.dto.RelHistListItemDTO;
import hk.oro.iefas.domain.release.entity.RelHistList;
import hk.oro.iefas.domain.release.entity.RelHistListItem;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.domain.shroff.entity.Voucher;

/**
 * 
 * @version $Revision: 2652 $ $Date: 2018-05-28 10:37:03 +0800 (Mon, 28 May 2018) $
 * @author $Author: george.wu $
 */
@Component
public class HistoricalCaseListDetailDTOAssembler {
    @Autowired
    private HistoricalCaseListBasicInformationDTOAssembler historicalCaseListBasicInformationDTOAssembler;

    @Autowired
    private HistoricalCaseListItemDTOAssembler historicalCaseListItemDTOAssembler;

    public HistoricalCaseListDetailDTO toDTO(RelHistList histCaseList, List<RelHistListItem> histCaseListItems) {
        if (histCaseList != null) {
        	HistoricalCaseListDetailDTO historicalCaseListDetailDTO = new HistoricalCaseListDetailDTO();
        	historicalCaseListDetailDTO
                .setHistoricalCaseListInformation(historicalCaseListBasicInformationDTOAssembler.toDTO(histCaseList));
            if (CommonUtils.isNotEmpty(histCaseListItems)) {
                List<RelHistListItemDTO> histCaseListItemsItemsDTO
                    = historicalCaseListItemDTOAssembler.toDTOList(histCaseListItems);
                historicalCaseListDetailDTO.setHistoricalCaseListDetailItems(histCaseListItemsItemsDTO);
            }
            return historicalCaseListDetailDTO;
        }
        return null;
    }
}
