package hk.oro.iefas.domain.release.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (Mon, 09 Apr 2018) $
 * @author $Author: marvel.ma $
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalCaseListResultDTO{
	
	//private List<HistoricalCaseListResultItemDTO> historicalCaseListResultItemList;
	
	private List<RelHistListDTO> resultList;
	private Page<RelHistListDTO> pageVo;

}
