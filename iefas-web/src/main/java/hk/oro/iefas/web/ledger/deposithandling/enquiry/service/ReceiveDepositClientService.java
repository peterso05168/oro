package hk.oro.iefas.web.ledger.deposithandling.enquiry.service;

import hk.oro.iefas.domain.shroff.vo.ReceiveDepositVO;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface ReceiveDepositClientService {

    Integer saveReceiveDeposit(ReceiveDepositVO receiveDepositVO);

    ReceiveDepositVO getReceiveDepositDetail(Integer depositId);

    Integer printReceipt(ReceiveDepositVO receiveDepositVO);

    String generateReceiptNumber();

    String generateDepositNumber();
}
