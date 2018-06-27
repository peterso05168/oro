package hk.oro.iefas.domain.release.dto;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (Mon, 09 Apr 2018) $
 * @author $Author: marvel.ma $
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistoricalCaseListDTO extends TxnDTO {
	
	private Date listDate;

    private Integer statusId;
    
    private String status;

}
