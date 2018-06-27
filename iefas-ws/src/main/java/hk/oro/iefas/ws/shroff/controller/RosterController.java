package hk.oro.iefas.ws.shroff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.RosterDTO;
import hk.oro.iefas.domain.shroff.dto.RosterResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchRosterDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.RosterService;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @version $Revision: 2745 $ $Date: 2018-05-30 20:19:44 +0800 (週三, 30 五月 2018) $
 * @author $Author: garrett.han $
 */
@RestController
@Slf4j
@RequestMapping(value = RequestUriConstant.URI_ROOT_ROSTER)
public class RosterController {

    @Autowired
    private RosterService rosterService;

    @PostMapping(value = RequestUriConstant.URI_SEARCH_ROSTER_LIST_BY_CRITERIA)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public Page<RosterResultDTO> searchRosterList(@RequestBody SearchDTO<SearchRosterDTO> criteria) {
        log.info("searchRosterList() start - criteria :" + criteria);
        Page<RosterResultDTO> result = rosterService.searchRosterList(criteria);
        log.info("searchRosterList() end -" + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_GET_ROSTER_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public RosterDTO getRosterDetail(@RequestBody Integer rosterId) {
        log.info("getRosterDetail() start  - id : " + rosterId);
        RosterDTO rosterDTO = rosterService.getRosterDetail(rosterId);
        log.info("getRosterDetail() end -" + rosterDTO);
        return rosterDTO;
    }

    @PostMapping(value = RequestUriConstant.URI_SAVE_ROSTER)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public Integer saveRoster(@RequestBody RosterDTO rosterDTO) {
        log.info("saveRoster() start - rosterDTO : " + rosterDTO);
        Integer rosterId = rosterService.saveRoster(rosterDTO);
        log.info("saveRoster() end - " + rosterId);
        return rosterId;
    }

    @PostMapping(RequestUriConstant.URI_EXISTS_BY_ON_DUTY_DATE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Boolean existsByOnDutyDate(@RequestBody SearchRosterDTO criteria) {
        log.info("existsByOnDutyDate start - criteria: " + criteria);
        Boolean result;
        result = rosterService.existsByOnDutyDateAndIdNot(criteria);
        log.info("existsByOnDutyDate end - " + result);
        return result;
    }
}
