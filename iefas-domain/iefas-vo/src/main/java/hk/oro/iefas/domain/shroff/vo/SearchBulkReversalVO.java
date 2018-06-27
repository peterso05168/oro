package hk.oro.iefas.domain.shroff.vo;

import java.util.Date;
import java.util.List;

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
public class SearchBulkReversalVO {

	private Long bulkReversalId;
	private Date cutOffDate;
	private Date processDate;
	private String voucherNo;
}
