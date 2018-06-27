package hk.oro.iefas.domain.shroff.vo;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BulkReversalItemVO extends TxnVO {

	private Long bulkReversalItemId;
	private Long bulkReversalId;
	private String status;
	private ChequeVO shrCheque;
	
	private Long rowId;
}
