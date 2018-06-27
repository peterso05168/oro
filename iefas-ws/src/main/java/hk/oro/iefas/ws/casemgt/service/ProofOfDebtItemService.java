package hk.oro.iefas.ws.casemgt.service;

import hk.oro.iefas.domain.counter.dto.ProofOfDebtItemDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface ProofOfDebtItemService {

    Integer save(ProofOfDebtItemDTO proofOfDebtItemDTO);

    ProofOfDebtItemDTO findOne(Integer proofOfDebtItemId);

}
