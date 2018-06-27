package hk.oro.iefas.ws.dividend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDistDTO;
import hk.oro.iefas.domain.dividend.dto.PercentagesAdjustmentDTO;
import hk.oro.iefas.domain.dividend.dto.SearchPercentagesAdjustmentCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.PercentagesAdjustmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2961 $ $Date: 2018-06-06 20:14:53 +0800 (週三, 06 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_PERCENTAGES_ADJUSTMENT)
public class PercentagesAdjustmentController {

    @Autowired
    private PercentagesAdjustmentService percentagesAdjustmentService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_ROOT_SEARCH_PERCENTAGES_ADJUSTMENT_LIST)
    public Page<PercentagesAdjustmentDTO>
        searchPercentagesAdjustmentList(@RequestBody SearchDTO<SearchPercentagesAdjustmentCriteriaDTO> criteria) {
        log.info("searchPercentagesAdjustmentList - start and criteria = " + criteria);
        Page<PercentagesAdjustmentDTO> result = percentagesAdjustmentService.searchPercentagesAdjustmentList(criteria);
        log.info("searchPercentagesAdjustmentList - end");
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_ROOT_SEARCH_PERCENTAGES_ADJUSTMENT)
    public PercentagesAdjustmentDTO searchPercentageAdjustment(@RequestBody Integer adjudicationResultId) {
        log.info("savePercentageAdjustment - start and adjudicationResultId =" + adjudicationResultId);
        PercentagesAdjustmentDTO result
            = this.percentagesAdjustmentService.searchPercentagesAdjustmentDTO(adjudicationResultId);
        log.info("savePercentageAdjustment - end and result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_ROOT_SAVE_PERCENTAGES_ADJUSTMENT)
    public boolean savePercentageAdjustment(@RequestBody PercentagesAdjustmentDTO percentagesAdjustment) {
        log.info("savePercentageAdjustment - start and percentagesAdjustment =" + percentagesAdjustment);
        boolean result = percentagesAdjustmentService.savePercentageAdjustment(percentagesAdjustment);
        log.info("savePercentageAdjustment - end and result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_ROOT_SEARCH_BY_APPADJRESULTITEMID)
    public DividendScheduleDistDTO searchDivScheduleDistByAppAdjResultItemId(@RequestBody Integer appAdjResultItemId) {
        log.info("searchDivScheduleDistByAppAdjResultItemId - start and appAdjResultItemId =" + appAdjResultItemId);
        DividendScheduleDistDTO result
            = percentagesAdjustmentService.searchDivScheduleDistByAppAdjResultItemId(appAdjResultItemId);
        log.info("searchDivScheduleDistByAppAdjResultItemId - end and result =" + result);
        return result;
    }
}
