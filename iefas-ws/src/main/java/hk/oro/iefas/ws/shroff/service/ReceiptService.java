package hk.oro.iefas.ws.shroff.service;

import hk.oro.iefas.domain.shroff.dto.ReceiptDTO;
import hk.oro.iefas.domain.shroff.entity.ShrReceipt;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface ReceiptService {
    ShrReceipt saveReceipt(ReceiptDTO receiptDTO);

    String generateReceiptNumber();

    ReceiptDTO getReceiptDetail(Integer receiptId);
}
