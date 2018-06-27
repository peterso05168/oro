package hk.oro.iefas.ws.casemgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchCaseDetailCriteriaDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchCaseDetailResultDTO;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.casemgt.service.CaseService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_CASE)
public class CaseController {

    @Autowired
    private CaseService caseService;

    @PostMapping(value = RequestUriConstant.URI_EXISTS_BY_CASENO)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Boolean validateExistsByCaseNo(@RequestBody String caseNo) {
        log.info("validateExistsByCaseNo start - and caseNo = " + caseNo);
        Boolean result = caseService.validateExistsByCaseNo(caseNo);
        log.info("validateExistsByCaseNo end - return: " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_SEARCH_CASE_BY_CRITERIA)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<SearchCaseDetailResultDTO>
        findByCriteria(@RequestBody SearchDTO<SearchCaseDetailCriteriaDTO> criteria) {
        log.info("searchCaseDetailList() start - " + criteria);
        Page<SearchCaseDetailResultDTO> roleList = caseService.findByCriteria(criteria);
        log.info("searchCaseDetailList() end ");
        return roleList;
    }

    @PostMapping(value = RequestUriConstant.URI_CASE_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public CaseDTO findOne(@RequestBody Integer caseId) {
        log.info("findOne() start - " + caseId);
        CaseDTO caseInfo = caseService.findOne(caseId);
        log.info("findOne() end ");
        return caseInfo;
    }

    @PostMapping(value = RequestUriConstant.URI_CASE_FINDBYCASENO)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public CaseDTO findByCaseNumber(@RequestBody CaseNumberDTO caseNumber) {
        log.info("findByCaseNumber start - and caseNumber = " + caseNumber);
        CaseDTO result = caseService.findByCaseNumber(caseNumber);
        log.info("findByCaseNumber end - return: " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_CASE_FIND_BY_CASENO)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public CaseDTO findByWholeCaseNumber(@RequestBody String wholeCaseNumber) {
        log.info("findByWholeCaseNumber start - caseNumber: " + wholeCaseNumber);
        CaseDTO result = caseService.findByWholeCaseNumber(wholeCaseNumber);
        log.info("findByWholeCaseNumber end - return: " + result);
        return result;
    }
}
