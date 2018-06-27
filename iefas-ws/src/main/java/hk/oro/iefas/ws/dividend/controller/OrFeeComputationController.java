package hk.oro.iefas.ws.dividend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.dto.CaseFeeMaintenanceDTO;
import hk.oro.iefas.domain.dividend.dto.CreateOrFeeComputationDTO;
import hk.oro.iefas.domain.dividend.dto.DividendCalculationDTO;
import hk.oro.iefas.domain.dividend.dto.SearchOrFeeComputationCriteriaDTO;
import hk.oro.iefas.domain.dividend.dto.ValidateOrFeeComputationDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.OrFeeComputationService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3124 $ $Date: 2018-06-13 17:47:21 +0800 (週三, 13 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_ORFEECOMPUTATION)
public class OrFeeComputationController {

    @Autowired
    private OrFeeComputationService orFeeComputationService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_ORFEECOMPUTATION_LIST)
    public Page<DividendCalculationDTO>
        searchORFeeComputationList(@RequestBody SearchDTO<SearchOrFeeComputationCriteriaDTO> criteriaDTO) {
        log.info("searchORFeeComputationList() start criteriaDTO :" + criteriaDTO);
        Page<DividendCalculationDTO> page = orFeeComputationService.searchORFeeComputationList(criteriaDTO);
        log.info("searchORFeeComputationList() end - return : " + page);
        return page;
    }

    @PostMapping(value = RequestUriConstant.URI_CASE_CREATABLE_VALIDATION)
    public boolean validateCaseCreatable(@RequestBody ValidateOrFeeComputationDTO validateOrFeeComputationDTO) {
        log.info("validateCaseCreatable() start - " + validateOrFeeComputationDTO);
        boolean creatable = orFeeComputationService.validateCaseCreatable(validateOrFeeComputationDTO);
        log.info("validateCaseCreatable() end return : " + creatable);
        return creatable;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_ORFEECOMPUTATION_SAVE)
    public Integer saveORFeeComputation(@RequestBody DividendCalculationDTO dividendCalculationDTO) {
        log.info("saveORFeeComputation() start - " + dividendCalculationDTO);
        Integer orFeeComputationId = orFeeComputationService.saveORFeeComputation(dividendCalculationDTO);
        log.info("saveORFeeComputation() end return : " + orFeeComputationId);
        return orFeeComputationId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_ORFEECOMPUTATION_DETAIL)
    public DividendCalculationDTO searchORFeeComputation(@RequestBody Integer orFeeComputationId) {
        log.info("searchORFeeComputation() start orFeeComputationId:" + orFeeComputationId);
        DividendCalculationDTO dividendCalculationDTO
            = orFeeComputationService.searchORFeeComputation(orFeeComputationId);
        log.info("searchORFeeComputation() end - return : " + dividendCalculationDTO);
        return dividendCalculationDTO;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_ORFEECOMPUTATION_INIT)
    public DividendCalculationDTO
        searchORFeeComputation(@RequestBody CreateOrFeeComputationDTO createORFeeComputationDTO) {
        log.info("searchORFeeComputation() start createORFeeComputationDTO:" + createORFeeComputationDTO);
        DividendCalculationDTO dividendCalculationDTO
            = orFeeComputationService.searchORFeeComputation(createORFeeComputationDTO);
        log.info("searchORFeeComputation() end - return : " + dividendCalculationDTO);
        return dividendCalculationDTO;
    }

    @PostMapping(value = RequestUriConstant.URI_CASE_FEE_MAIN_LIST)
    public List<CaseFeeMaintenanceDTO> findCaseFeeMains(@RequestBody String caseFeeType) {
        log.info("findCaseFeeMains start");
        List<CaseFeeMaintenanceDTO> list = orFeeComputationService.findCaseFeeMainsByType(caseFeeType);
        log.info("findCaseFeeMains end return:" + list);
        return list;
    }
}
