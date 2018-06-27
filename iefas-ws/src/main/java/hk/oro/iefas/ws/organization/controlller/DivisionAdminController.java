package hk.oro.iefas.ws.organization.controlller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.dto.DivisionAdminDTO;
import hk.oro.iefas.domain.organization.dto.DivisionDTO;
import hk.oro.iefas.domain.organization.dto.SearchDivisionAdminResultDTO;
import hk.oro.iefas.domain.organization.dto.SearchDivisionCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.organization.service.DivisionAdminService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2174 $ $Date: 2018-04-23 17:52:58 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_DIVISION_ADMIN)
public class DivisionAdminController {

    @Autowired
    private DivisionAdminService divisionAdminService;

    @PostMapping(value = RequestUriConstant.URI_DIVISION_ADMIN_BY_POST)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = true)
    public List<DivisionDTO> getByPostId(@RequestBody Integer postId) {
        log.info("getByPostId() start - postId = " + postId);
        List<DivisionDTO> list = divisionAdminService.getByPostId(postId);
        log.info("getByPostId() end and result = " + list);
        return list;
    }

    @PostMapping(value = RequestUriConstant.URI_DIVISION_ADMIN_BY_DIVISION)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = true)
    public Page<SearchDivisionAdminResultDTO>
        findByDivision(@RequestBody SearchDTO<SearchDivisionCriteriaDTO> criteria) {
        log.info("findByDivision() start - criteria = " + criteria.getCriteria());
        Page<SearchDivisionAdminResultDTO> list = divisionAdminService.findByDivision(criteria);
        log.info("findByDivision() end and result = " + list);
        return list;
    }

    @PostMapping(value = RequestUriConstant.URI_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    public Integer save(@Valid @RequestBody DivisionAdminDTO divisionAdminDTO) {
        log.info("saveDivisionAdmin() start - " + divisionAdminDTO);
        Integer divisionAdminId = divisionAdminService.save(divisionAdminDTO);
        log.info("saveDivisionAdmin() end and divisionAdminId = " + divisionAdminId);
        return divisionAdminId;
    }

    @PostMapping(value = RequestUriConstant.URI_FIND_ONE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = true)
    public DivisionAdminDTO findOne(@RequestBody Integer divisionAdminId) {
        log.info("findOne() start - " + divisionAdminId);
        DivisionAdminDTO divisionAdmin = divisionAdminService.findOne(divisionAdminId);
        log.info("findOne() end ");
        return divisionAdmin;
    }

    @PostMapping(value = RequestUriConstant.URI_DIVISION_ADMIN_BY_DIVISION_AND_POST)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = true)
    public DivisionAdminDTO findByDivisionAndPost(@RequestBody SearchDivisionCriteriaDTO criteria) {
        log.info("findByDivisionAndPost() start - criteria = " + criteria);
        DivisionAdminDTO divisionAdmin = divisionAdminService.findByDivisionAndPost(criteria);
        log.info("findByDivisionAndPost() end ");
        return divisionAdmin;
    }
}
