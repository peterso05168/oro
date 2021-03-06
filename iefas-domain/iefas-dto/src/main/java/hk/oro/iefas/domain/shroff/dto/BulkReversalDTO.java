package hk.oro.iefas.domain.shroff.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
public class BulkReversalDTO extends TxnDTO {
	private Long bulkReversalId;
	private Date cutOffDate;
	private Date processDate;
	private String remark;
	private String status;
	private BigDecimal totalAmount;
	private VoucherDTO shrVcrInfo;
    private List<BulkReversalItemDTO> shrBulkRvlItems;
}
