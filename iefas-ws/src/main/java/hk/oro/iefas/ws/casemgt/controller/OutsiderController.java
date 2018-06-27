package hk.oro.iefas.ws.casemgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.OutsiderDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchOutsiderDetailCriteriaDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchOutsiderDetailResultDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.casemgt.service.OutsiderService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_OUTSIDER)
public class OutsiderController {

    @Autowired
    private OutsiderService outsiderService;

    @PostMapping(value = RequestUriConstant.URI_SEARCH_OUTSIDER_BY_CRITERIA)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<SearchOutsiderDetailResultDTO>
        findByCriteria(@RequestBody SearchDTO<SearchOutsiderDetailCriteriaDTO> criteria) {
        log.info("searchOutsiderDetailList() start - " + criteria);
        Page<SearchOutsiderDetailResultDTO> roleList = outsiderService.findByCriteria(criteria);
        log.info("searchOutsiderDetailList() end ");
        return roleList;
    }

    @PostMapping(value = RequestUriConstant.URI_GET_OUTSIDER_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public OutsiderDTO getOutsiderDetail(@RequestBody Integer outsiderId) {
        log.info("getOutsiderDetail() start - " + outsiderId);
        OutsiderDTO outsiderDTO = outsiderService.getOutsiderDetail(outsiderId);
        log.info("getOutsiderDetail() end - " + outsiderDTO);
        return outsiderDTO;
    }

    @PostMapping(value = RequestUriConstant.URI_SAVE_OUTSIDER)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SAVE)
    public Integer saveOutsider(@RequestBody OutsiderDTO outsiderDTO) {
        log.info("saveOutsider() start - " + outsiderDTO);
        Integer outsiderId = outsiderService.saveOutsider(outsiderDTO);
        log.info("saveOutsider() end - " + outsiderId);
        return outsiderId;

    }

    @PostMapping(value = RequestUriConstant.URI_VALIDATE_OUTSIDER_NAME)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Boolean existsByOutsiderName(@RequestBody SearchOutsiderDetailCriteriaDTO criteria) {
        log.info("existsByOutsiderName() start - and criteria = " + criteria);
        Boolean result = outsiderService.existsByOutsiderName(criteria);
        log.info("existsByOutsiderName() result = " + result);
        return result;
    }
}
