package hk.oro.iefas.ws.dividend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.dto.CalculationMaintenanceDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeTypeDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.OrFeeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_ORFEE)
public class OrFeeController {

    @Autowired
    private OrFeeService orFeeService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_ORFEE_SEARCH_ORFEEITEM)
    public CaseFeeTypeDTO searchORFeeItemWithCalculationMethod(@RequestBody Integer caseFeeTypeId) {
        log.info("searchORFeeItemWithCalculationMethod start - param: " + caseFeeTypeId);
        CaseFeeTypeDTO dto = orFeeService.searchORFeeItemWithCalculationMethod(caseFeeTypeId);
        log.info("searchORFeeItemWithCalculationMethod end - return: " + dto);
        return dto;
    }

    @PostMapping(value = RequestUriConstant.URI_ORFEE_VALIDATE_SAVE_ORFEEITEM)
    public boolean validateSaveORFeeItemWithCalculationMethod(
        @RequestBody List<CalculationMaintenanceDTO> calculationMaintenances) {
        log.info("saveORFeeItemWithCalculationMethodValidate start - pram: " + calculationMaintenances);
        boolean validate = orFeeService.validateSaveORFeeItemWithCalculationMethod(calculationMaintenances);
        log.info("saveORFeeItemWithCalculationMethodValidate end - return: " + validate);
        return validate;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_ORFEE_SAVE_ORFEE_ITEM)
    public Integer saveORFeeItemWithCalculationMethod(@RequestBody CaseFeeTypeDTO caseFeeTypeDTO) {
        log.info("saveORFeeItemWithCalculationMethod start - pram: " + caseFeeTypeDTO);
        Integer id = this.orFeeService.saveORFeeItemWithCalculationMethod(caseFeeTypeDTO);
        log.info("saveORFeeItemWithCalculationMethod end - return: " + id);
        return id;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_ORFEE_SEARCH_ORFEE_ITEM_WITH_ANALYSIS)
    public CaseFeeTypeDTO searchORFeeItemWithAnalysisCodeCharged(@RequestBody Integer caseFeeTypeId) {
        log.info("searchORFeeItemWithAnalysisCodeCharged start - pram: " + caseFeeTypeId);
        CaseFeeTypeDTO caseFeeTypeDTO = orFeeService.searchORFeeItemWithAnalysisCodeCharged(caseFeeTypeId);
        log.info("searchORFeeItemWithAnalysisCodeCharged end - caseFeeTypeDTO: " + caseFeeTypeDTO);
        return caseFeeTypeDTO;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_ORFEE_SAVE_ORFEE_ITEM_WITH_ANALYSIS)
    public boolean saveORFeeItemWithAnalysisCodeCharged(@RequestBody CaseFeeTypeDTO caseFeeTypeDTO) {
        log.info("saveORFeeItemWithAnalysisCodeCharged start - param: " + caseFeeTypeDTO);
        boolean result = orFeeService.saveORFeeItemWithAnalysisCodeCharged(caseFeeTypeDTO);
        log.info("saveORFeeItemWithAnalysisCodeCharged end - result: " + result);
        return result;
    }
}
