package hk.oro.iefas.ws.bank.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.dto.CalculationOfFundsAvailableDTO;
import hk.oro.iefas.domain.bank.dto.SearchPlacingDepositsCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.bank.service.PlacingDepositsService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_PLACING_DEPOSITS)
public class PlacingDepositsController {

    @Autowired
    private PlacingDepositsService placingDepositsService;

    @PostMapping(value = RequestUriConstant.URI_PLACING_DEPOSITS_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<CalculationOfFundsAvailableDTO>
        searchPlacingDepositsList(@RequestBody SearchDTO<SearchPlacingDepositsCriteriaDTO> criteriaDTO) {
        log.info("searchPlacingDepositsList() start - " + criteriaDTO);
        Page<CalculationOfFundsAvailableDTO> page = placingDepositsService.searchPlacingDepositsList(criteriaDTO);
        log.info("searchPlacingDepositsList() end - " + page);
        return page;
    }

    @PostMapping(value = RequestUriConstant.URI_PLACING_DEPOSITS_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    public CalculationOfFundsAvailableDTO searchPlacingDeposits(@RequestBody Integer calculationOfFundsAvailableId) {
        log.info("searchPlacingDeposits() start - calculationOfFundsAvailableId: " + calculationOfFundsAvailableId);
        CalculationOfFundsAvailableDTO calculationOfFundsAvailableDTO
            = placingDepositsService.findOne(calculationOfFundsAvailableId);
        log.info("searchPlacingDeposits() end - " + calculationOfFundsAvailableDTO);
        return calculationOfFundsAvailableDTO;
    }

    @PostMapping(value = RequestUriConstant.URI_PLACING_DEPOSITS_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    public Integer savePlacingDeposits(@RequestBody CalculationOfFundsAvailableDTO calculationOfFundsAvailableDTO) {
        log.info("savePlacingDeposits() start - calculationOfFundsAvailableDTO: " + calculationOfFundsAvailableDTO);
        Integer returnVal = placingDepositsService.save(calculationOfFundsAvailableDTO);
        log.info("savePlacingDeposits() end - " + returnVal);
        return returnVal;
    }

    @PostMapping(value = RequestUriConstant.URI_PLACING_DEPOSITS_EXISTS)
    public Boolean existsByInvestmentDate(@RequestBody Date invtDate) {
        log.info("existsByInvestmentDate() start - invtDate: " + invtDate);
        Boolean returnVal = placingDepositsService.existsByInvestmentDate(invtDate);
        log.info("existsByInvestmentDate() end - " + returnVal);
        return returnVal;
    }
}
