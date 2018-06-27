package hk.oro.iefas.domain.release.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.core.vo.TxnVO;
import hk.oro.iefas.domain.release.vo.HistoricalCaseListResultItemVO;
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
public class HistoricalCaseListResultVO{

	//private List<HistoricalCaseListResultItemVO> historicalCaseListResultItemList;
	
	List<RelHistListVO> resultList;
	Page<RelHistListVO> pageVo;
}
