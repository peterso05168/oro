package hk.oro.iefas.ws.dividend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleItemDTO;
import hk.oro.iefas.domain.dividend.dto.InterestTrialAdjudicationDTO;
import hk.oro.iefas.domain.dividend.dto.SearchInterestTrialCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.InterestTrialService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3163 $ $Date: 2018-06-15 16:09:30 +0800 (週五, 15 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_INTERESTTRIAL)
public class InterestTrialController {

    @Autowired
    private InterestTrialService interestTrialService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_SEARCH_INTERESTTRIALLIST)
    public Page<InterestTrialAdjudicationDTO>
        searchInterestTrialList(@RequestBody SearchDTO<SearchInterestTrialCriteriaDTO> criteria) {
        log.info("searchInterestTrialList - start - and criteria =" + criteria);
        Page<InterestTrialAdjudicationDTO> result = interestTrialService.searchInterestTrialList(criteria);
        log.info("searchInterestTrialList - end - and return result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_SEARCH_INTERESTTRIAL_BY_ID)
    public InterestTrialAdjudicationDTO searchInterestTrial(@RequestBody Integer interestTrialAdjudicationId) {
        log.info("searchInterestTrial - start - and interestTrialAdjudicationId =" + interestTrialAdjudicationId);
        InterestTrialAdjudicationDTO result = interestTrialService.searchInterestTrialById(interestTrialAdjudicationId);
        log.info("searchInterestTrial - end - and return result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_SAVE_INTERESTTRIAL)
    public Integer saveInterestTrial(@RequestBody InterestTrialAdjudicationDTO dto) {
        log.info("saveInterestTrial - start - and dto =" + dto);
        Integer result = interestTrialService.saveInterestTrial(dto);
        log.info("saveInterestTrial - end - and return result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_CREATE_INTERESTTRIAL)
    public InterestTrialAdjudicationDTO createInterestTrial(@RequestBody Integer creditorId) {
        log.info("createInterestTrial - start - and creditorId =" + creditorId);
        InterestTrialAdjudicationDTO result = interestTrialService.createInterestTrial(creditorId);
        log.info("createInterestTrial - end - and result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_SEARCH_DIVSHCEDULEITEM_BY_ADJRESULTID)
    public List<DividendScheduleItemDTO> searchDivScheduleItemByAdjResultId(@RequestBody Integer adjResultId) {
        log.info("searchDivScheduleItemByAdjResultId - start - and adjResultId =" + adjResultId);
        List<DividendScheduleItemDTO> result
            = this.interestTrialService.searchDivScheduleItemByAdjResultId(adjResultId);
        log.info("searchDivScheduleItemByAdjResultId - end - and result =" + result);
        return result;
    }
}
