package hk.oro.iefas.ws.casemgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.CommonCreditorSectionDTO;
import hk.oro.iefas.ws.casemgt.service.CommonCreditorSectionService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_COMMON_CREDITOR_SECTION)
public class CommonCreditorSectionController {

    @Autowired
    private CommonCreditorSectionService commonCreditorSectionService;

    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_COMMON_CREDITOR_SECTION_SEARCH_BY_NAME)
    public List<CommonCreditorSectionDTO>
        searchCommonCreditorSectionByName(@RequestBody String commonCreditorSectionName) {
        log.info("searchCommonCreditorByName() start - and commonCreditorSectionName: " + commonCreditorSectionName);
        List<CommonCreditorSectionDTO> list
            = commonCreditorSectionService.searchCommonCreditorSectionByName(commonCreditorSectionName);
        log.info("searchCommonCreditorByName() end - return : " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_COMMON_CREDITOR_SECTION_SEARCH_BY_SEQ_NO)
    public List<CommonCreditorSectionDTO>
        searchCommonCreditorSectionBySeqNo(@RequestBody String commonCreditorSectionSeqNo) {
        log.info("searchCommonCreditorSectionBySeqNo() start - and commonCreditorSectionSeqNo: "
            + commonCreditorSectionSeqNo);
        List<CommonCreditorSectionDTO> list
            = commonCreditorSectionService.searchCommonCreditorSectionBySeqNo(commonCreditorSectionSeqNo);
        log.info("searchCommonCreditorSectionBySeqNo() end - return : " + list);
        return list;
    }
}
