package hk.oro.iefas.ws.casemgt.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.dto.DepositCardDTO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface DepositCardService {

    DepositCardDTO findOne(Integer depositCardId);

    Integer save(DepositCardDTO depositCard);

    List<DepositCardDTO> findDepositCardByCase(Integer caseId);
}
