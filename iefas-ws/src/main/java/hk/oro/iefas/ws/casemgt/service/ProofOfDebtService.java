package hk.oro.iefas.ws.casemgt.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.casemgt.dto.SearchProofOfDebtCriteriaDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchProofOfDebtResultDTO;
import hk.oro.iefas.domain.counter.dto.ProofOfDebtDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface ProofOfDebtService {

    Page<SearchProofOfDebtResultDTO> findByCriteria(SearchDTO<SearchProofOfDebtCriteriaDTO> criteria);

    Integer save(ProofOfDebtDTO proofOfDebtDTO);

    ProofOfDebtDTO findOne(Integer proofOfDebtId);

}
