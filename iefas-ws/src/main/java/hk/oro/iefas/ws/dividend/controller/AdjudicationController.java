package hk.oro.iefas.ws.dividend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.dto.AdjucationDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationResultDTO;
import hk.oro.iefas.domain.dividend.dto.SearchAdjudicationCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.AdjudicationService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (週日, 10 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_ADJUDICATION)
public class AdjudicationController {

    @Autowired
    private AdjudicationService adjudicationService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_PRE_ADJUDICATION_LIST)
    public Page<AdjucationResultDTO>
        searchPreAdjudicationList(@RequestBody SearchDTO<SearchAdjudicationCriteriaDTO> criteriaDTO) {
        log.info("searchPreAdjudicationList() start criteriaDTO :" + criteriaDTO);
        Page<AdjucationResultDTO> page = adjudicationService.searchPreAdjudicationList(criteriaDTO);
        log.info("searchPreAdjudicationList() end - return : " + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_ORD_ADJUDICATION_LIST)
    public Page<AdjucationResultDTO>
        searchOrdAdjudicationList(@RequestBody SearchDTO<SearchAdjudicationCriteriaDTO> criteriaDTO) {
        log.info("searchOrdAdjudicationList() start criteriaDTO :" + criteriaDTO);
        Page<AdjucationResultDTO> page = adjudicationService.searchOrdAdjudicationList(criteriaDTO);
        log.info("searchOrdAdjudicationList() end - return : " + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_ADJUDICATION_SAVE)
    public Integer saveAdjudication(@RequestBody AdjucationDTO adjucationDTO) throws Exception {
        log.info("createAdjudication() start - " + adjucationDTO);
        Integer adjucationId = adjudicationService.saveAdjudication(adjucationDTO);
        log.info("createAdjudication() end return : " + adjucationId);
        return adjucationId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_ADJUDICATION_DETAIL)
    public AdjucationDTO searchAdjudication(@RequestBody Integer adjucationId) {
        log.info("searchAdjudicationList() start adjucationId:" + adjucationId);
        AdjucationDTO adjucationDTO = adjudicationService.searchAdjudication(adjucationId);
        log.info("searchAdjudicationList() end - return : " + adjucationDTO);
        return adjucationDTO;
    }
}
