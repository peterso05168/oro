package hk.oro.iefas.domain.release.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (Mon, 09 Apr 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RelHistListVO extends TxnVO {

    private Integer histCaseListId;
	
    private Date histCaseListDate;
    
    private String histCaseListDesc;
    
    private String status;
}
