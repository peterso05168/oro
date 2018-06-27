package hk.oro.iefas.ws.dividend.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.dividend.dto.DividendScheduleDistDTO;
import hk.oro.iefas.domain.dividend.dto.PercentagesAdjustmentDTO;
import hk.oro.iefas.domain.dividend.dto.SearchPercentagesAdjustmentCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2961 $ $Date: 2018-06-06 20:14:53 +0800 (週三, 06 六月 2018) $
 * @author $Author: noah.liang $
 */
public interface PercentagesAdjustmentService {

    public Page<PercentagesAdjustmentDTO>
        searchPercentagesAdjustmentList(SearchDTO<SearchPercentagesAdjustmentCriteriaDTO> criteria);

    public PercentagesAdjustmentDTO searchPercentagesAdjustmentDTO(Integer adjudicationResultId);

    public boolean savePercentageAdjustment(PercentagesAdjustmentDTO percentagesAdjustment);

    public DividendScheduleDistDTO searchDivScheduleDistByAppAdjResultItemId(Integer appAdjResultItemId);

}
