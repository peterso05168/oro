package hk.oro.iefas.ws.dividend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.dto.SearchWithheldReasonDTO;
import hk.oro.iefas.domain.dividend.dto.WithheldReasonDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.WithheldReasonService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3087 $ $Date: 2018-06-12 18:02:20 +0800 (週二, 12 六月 2018) $
 * @author $Author: noah.liang $
 */

@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_WITHHELD_REASON)
public class WithheldReasonsController {

    @Autowired
    private WithheldReasonService withheldReasonService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_SEARCH_WITHHELD_REASON)
    public Page<WithheldReasonDTO>
        searchWithheldReason(@RequestBody SearchDTO<SearchWithheldReasonDTO> withheldSearchCriteria) {
        log.info("searchWithheldReason - start and withheldSearchCriteria =" + withheldSearchCriteria);
        Page<WithheldReasonDTO> result = withheldReasonService.searchWithheldReason(withheldSearchCriteria);
        log.info("searchWithheldReason - end and result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_SAVE_WITHHELD_REASON)
    public Integer saveWithheldReason(@RequestBody WithheldReasonDTO withheldReasonDTO) {
        log.info("saveWithheldReason - start and withheldReasonDTO =" + withheldReasonDTO);
        Integer result = withheldReasonService.saveWithheldReason(withheldReasonDTO);
        log.info("saveWithheldReason - end and result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_SEARCH_WITHHELD_REASON_BY_ID)
    public WithheldReasonDTO searchByWithheldReason(@RequestBody Integer withheldReasonId) {
        log.info("searchByWithheldReason - start and withheldReasonId =" + withheldReasonId);
        WithheldReasonDTO result = withheldReasonService.searchByWithheldReasonId(withheldReasonId);
        log.info("searchByWithheldReason - end and result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_SEARCH_WITHHELD_REASON_LIST)
    public List<WithheldReasonDTO> searchWithheldReasonList() {
        log.info("searchWithheldReasonList() - start");
        List<WithheldReasonDTO> result = this.withheldReasonService.searchWithheldReasonList();
        log.info("searchWithheldReasonList() - end and return: " + result);
        return result;
    }
}
