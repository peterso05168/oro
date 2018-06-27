package hk.oro.iefas.web.casemgt.proofofdebt.enquiry.service;

import hk.oro.iefas.domain.counter.vo.ProofOfDebtItemVO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface ProofOfDebtItemClientService {

    Integer save(ProofOfDebtItemVO proofOfDebtItemVO);

    ProofOfDebtItemVO findOne(Integer proofOfDebtItemId);

}
