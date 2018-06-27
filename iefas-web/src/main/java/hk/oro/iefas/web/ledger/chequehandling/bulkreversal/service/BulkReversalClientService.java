package hk.oro.iefas.web.ledger.chequehandling.bulkreversal.service;

import hk.oro.iefas.domain.shroff.vo.BulkReversalResultVO;
import hk.oro.iefas.domain.shroff.vo.BulkReversalVO;
import hk.oro.iefas.domain.shroff.vo.GenerateBulkReversalVO;
import hk.oro.iefas.domain.shroff.vo.SearchBulkReversalVO;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
public interface BulkReversalClientService {

	BulkReversalResultVO searchBulkReversal(SearchBulkReversalVO criteria);

	BulkReversalVO loadBulkReversalDetail(Long bulkReversalId);

	BulkReversalVO generateBulkReversal(GenerateBulkReversalVO generateBulkReversalVO);

	boolean insertBulkReversal(BulkReversalVO confirmedBulkReversalVo);

	BulkReversalVO confirmBulkReversal(BulkReversalVO confirmedBulkReversalVo);
	
}
