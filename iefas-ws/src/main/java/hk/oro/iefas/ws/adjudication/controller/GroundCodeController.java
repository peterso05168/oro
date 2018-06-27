package hk.oro.iefas.ws.adjudication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.dto.GroundCodeDTO;
import hk.oro.iefas.domain.dividend.dto.SearchGroundCodeCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.adjudication.service.GroundCodeService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2084 $ $Date: 2018-04-17 10:36:23 +0800 (週二, 17 四月 2018) $
 * @author $Author: noah.liang $
 */

@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_GROUND_CODE_CREDITOR)
public class GroundCodeController {

    @Autowired
    private GroundCodeService groundCodeService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_GROUND_CODE_LIST)
    public Page<GroundCodeDTO> searchGroundCodeList(@RequestBody SearchDTO<SearchGroundCodeCriteriaDTO> criteriaDTO) {
        log.info("searchGroundCodeList start - and criteriaDTO =" + criteriaDTO);
        Page<GroundCodeDTO> page = groundCodeService.searchGroundCodeList(criteriaDTO);
        log.info("searchGroundCodeList end - return " + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_SAVE_GROUND_CODE)
    public Integer saveGroundCode(@RequestBody GroundCodeDTO groundCodeDTO) {
        log.info("saveGroundCode controller start - and groundCodeDTO =" + groundCodeDTO);
        Integer result = groundCodeService.saveGroundCode(groundCodeDTO);
        log.info("saveGroundCode controller end - return " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_GROUND_CODE)
    public GroundCodeDTO searchGroundCode(@RequestBody Integer groundCodeId) {
        log.info("searchGroundCode start - and groundCodeId = " + groundCodeId);
        GroundCodeDTO groundCodeDTO = groundCodeService.searchGroundCodeById(groundCodeId);
        log.info("searchGroundCode end - return " + groundCodeId);
        return groundCodeDTO;
    }

    @GetMapping(value = RequestUriConstant.URI_FIND_ALL)
    public List<GroundCodeDTO> findAll() {
        log.info("findAll start-");
        List<GroundCodeDTO> list = groundCodeService.findAll();
        log.info("findAll end- return : " + list);
        return list;
    }

}
