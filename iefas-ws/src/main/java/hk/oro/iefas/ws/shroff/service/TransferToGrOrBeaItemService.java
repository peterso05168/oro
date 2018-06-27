package hk.oro.iefas.ws.shroff.service;

import java.util.List;

import hk.oro.iefas.domain.shroff.dto.TransferToGrOrBeaItemDTO;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface TransferToGrOrBeaItemService {

    Integer saveTransferToGrOrBeaItem(TransferToGrOrBeaItemDTO item);

    List<TransferToGrOrBeaItemDTO> findTransferItemByTransfer(Integer transferId);
}
