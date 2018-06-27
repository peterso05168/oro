package hk.oro.iefas.ws.dividend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.dividend.dto.DividendScheduleItemDTO;
import hk.oro.iefas.domain.dividend.dto.InterestTrialAdjudicationDTO;
import hk.oro.iefas.domain.dividend.dto.SearchInterestTrialCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 3163 $ $Date: 2018-06-15 16:09:30 +0800 (週五, 15 六月 2018) $
 * @author $Author: vicki.huang $
 */
public interface InterestTrialService {

    public Page<InterestTrialAdjudicationDTO>
        searchInterestTrialList(SearchDTO<SearchInterestTrialCriteriaDTO> criteria);

    public InterestTrialAdjudicationDTO searchInterestTrialById(Integer interestTrialAdjudicationId);

    public Integer saveInterestTrial(InterestTrialAdjudicationDTO dto);

    public InterestTrialAdjudicationDTO createInterestTrial(Integer creditorId);

    public List<DividendScheduleItemDTO> searchDivScheduleItemByAdjResultId(Integer adjResultId);

}
