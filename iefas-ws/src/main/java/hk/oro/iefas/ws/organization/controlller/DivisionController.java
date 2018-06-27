package hk.oro.iefas.ws.organization.controlller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.dto.DivisionDTO;
import hk.oro.iefas.domain.organization.dto.SearchDivisionCriteriaDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.organization.service.DivisionService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_DIVISION)
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @PostMapping(value = RequestUriConstant.URI_DIVISION_BY_CRITERIA)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public List<DivisionDTO> findByCriteria(@RequestBody SearchDivisionCriteriaDTO criteria) {
        log.info("findByCriteria() start - and criteria = " + criteria);
        List<DivisionDTO> list = divisionService.findByCriteria(criteria);
        log.info("findByCriteria() end and result = " + list);
        return list;
    }

    @PostMapping(value = RequestUriConstant.URI_DIVISION_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public DivisionDTO findOne(@RequestBody Integer divisionId) {
        log.info("getDivisionDetail() start - " + divisionId);
        DivisionDTO division = divisionService.findOne(divisionId);
        log.info("getDivisionDetail() end ");
        return division;
    }
}
