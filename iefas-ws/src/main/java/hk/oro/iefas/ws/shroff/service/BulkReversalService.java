package hk.oro.iefas.ws.shroff.service;

import hk.oro.iefas.domain.shroff.dto.BulkReversalDTO;
import hk.oro.iefas.domain.shroff.dto.BulkReversalResultDTO;
import hk.oro.iefas.domain.shroff.dto.GenerateBulkReversalDTO;
import hk.oro.iefas.domain.shroff.dto.SearchBulkReversalDTO;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
public interface BulkReversalService {

	BulkReversalResultDTO searchBulkReversal(SearchBulkReversalDTO criteria);

	BulkReversalDTO loadBulkReversalDetail(Long bulkReversalId);

	BulkReversalDTO generateBulkReversal(GenerateBulkReversalDTO generateBulkReversalDTO);

	boolean insertBulkReversal(BulkReversalDTO confirmedBulkReversalDto);

	BulkReversalDTO confirmBulkReversal(BulkReversalDTO confirmedBulkReversal);
	
}
