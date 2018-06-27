package hk.oro.iefas.ws.shroff.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiveDepositDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiveDepositResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchReceiveDepositDTO;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface ReceiveDepositService {
    Page<ReceiveDepositResultDTO> searchReceiveDepositList(SearchDTO<SearchReceiveDepositDTO> criteria);

    ReceiveDepositDTO getReceiveDepositDetail(Integer depositId);

    Integer saveReceiveDeposit(ReceiveDepositDTO receiveDepositDTO);

    Integer saveReceipt(ReceiveDepositDTO receiveDepositDTO);

    String generateDepositNo();
}
