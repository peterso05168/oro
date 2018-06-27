package hk.oro.iefas.ws.casemgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.CommonCreditorBasicDTO;
import hk.oro.iefas.domain.casemgt.dto.CommonCreditorDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchCommonCreditorCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.casemgt.service.DividendCommonCreditorService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_DIVIDEND_COMMON_CREDITOR)
public class DividendCommonCreditorController {

    @Autowired
    private DividendCommonCreditorService dividendCommonCreditorService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_COMMON_CREDITOR_LIST)
    public Page<CommonCreditorBasicDTO>
        searchCommonCreditorList(@RequestBody SearchDTO<SearchCommonCreditorCriteriaDTO> criteriaDTO) {
        log.info("searchCommonCreditorList() start - and param: " + criteriaDTO);
        Page<CommonCreditorBasicDTO> page = dividendCommonCreditorService.searchCommonCreditorList(criteriaDTO);
        log.info("searchCommonCreditorList() end - return : " + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_COMMON_CREDITOR_DETAIL)
    public CommonCreditorDTO searchCommonCreditor(@RequestBody Integer commonCreditorId) {
        log.info("searchCommonCreditor() start - and param: " + commonCreditorId);
        CommonCreditorDTO dto = dividendCommonCreditorService.searchCommonCreditor(commonCreditorId);
        log.info("searchCommonCreditor() end - return : " + dto);
        return dto;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_COMMON_CREDITOR_SAVE)
    public Integer saveCommonCreditor(@RequestBody CommonCreditorDTO dto) {
        log.info("saveCommonCreditor() start - and param: " + dto);
        Integer id = dividendCommonCreditorService.saveCommonCreditor(dto);
        log.info("saveCommonCreditor() end - return : " + id);
        return id;
    }

    @PostMapping(value = RequestUriConstant.URI_COMMON_CREDITOR_EXISTS)
    public boolean exists(@RequestBody SearchCommonCreditorCriteriaDTO criteriaDTO) {
        log.info("exists() start - and param: " + criteriaDTO);
        boolean bl = dividendCommonCreditorService.existsByCommonCreditorName(criteriaDTO.getCommonCreditorName(),
            criteriaDTO.getCommonCreditorId());
        log.info("exists() end - return : " + bl);
        return bl;
    }

    @GetMapping(value = RequestUriConstant.URI_COMMON_CREDITOR_ALL)
    public List<CommonCreditorDTO> findAll() {
        log.info("findAll() start ");
        List<CommonCreditorDTO> list = dividendCommonCreditorService.searchAllActCommonCreditors();
        log.info("findAll() end - list: " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_COMMON_CREDITOR_SEARCH_BY_NAME)
    public List<CommonCreditorDTO> searchCommonCreditorByName(@RequestBody String commonCreditorName) {
        log.info("searchCommonCreditorByName() start - and commonCreditorName: " + commonCreditorName);
        List<CommonCreditorDTO> list = dividendCommonCreditorService.searchCommonCreditorByName(commonCreditorName);
        log.info("searchCommonCreditorByName() end - return : " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_COMMON_CREDITOR_SEARCH_BY_SEQ_NO)
    public List<CommonCreditorDTO> searchCommonCreditorBySeqNo(@RequestBody String commonCreditorSeqNo) {
        log.info("searchCommonCreditorBySeqNo() start - and commonCreditorSeqNo: " + commonCreditorSeqNo);
        List<CommonCreditorDTO> list = dividendCommonCreditorService.searchCommonCreditorBySeqNo(commonCreditorSeqNo);
        log.info("searchCommonCreditorBySeqNo() end - return : " + list);
        return list;
    }
}
