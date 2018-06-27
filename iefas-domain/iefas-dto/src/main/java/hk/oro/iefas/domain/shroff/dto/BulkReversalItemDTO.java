package hk.oro.iefas.domain.shroff.dto;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BulkReversalItemDTO extends TxnDTO {
	private Long bulkReversalItemId;
	private Long bulkReversalId;
	private String status;
	private ChequeDTO shrCheque;
}
