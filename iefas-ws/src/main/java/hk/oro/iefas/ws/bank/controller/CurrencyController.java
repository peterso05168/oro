package hk.oro.iefas.ws.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.dto.CurrencyBasicInfoDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyResultDTO;
import hk.oro.iefas.domain.bank.dto.CurrencySearchDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.bank.service.CurrencyService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_CURRENCY)
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping(value = RequestUriConstant.URI_CURRENCY_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    public Integer saveCurrency(@Valid @RequestBody CurrencyDTO currency) {
        log.info("saveCurrency() start - " + currency);
        Integer curcyId = currencyService.save(currency);
        log.info("saveCurrency() end ");
        return curcyId;
    }

    @PostMapping(value = RequestUriConstant.URI_CURRENCY_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    public CurrencyDTO searchCurrencyRate(@RequestBody Integer curcyId) {
        log.info("searchCurrencyRate() start - curcyId: " + curcyId);
        CurrencyDTO currency = currencyService.findOne(curcyId);
        log.info("searchCurrencyRate() end - " + currency);
        return currency;
    }

    @PostMapping(value = RequestUriConstant.URI_CURRENCY_EXISTS)
    public Boolean existsByCurcyCodeAndCurcyIdNot(@RequestBody CurrencySearchDTO currencySearchDTO) {
        log.info("existsByCurcyCodeAndCurcyIdNot() start - " + currencySearchDTO);
        boolean exists = currencyService.existsByCurcyCodeAndCurcyIdNot(currencySearchDTO.getCurrencyCode(),
            currencySearchDTO.getCurcyId() == null ? 0 : currencySearchDTO.getCurcyId());
        log.info("existsByCurcyCodeAndCurcyIdNot() end - exists: " + exists);
        return exists;
    }

    @PostMapping(value = RequestUriConstant.URI_CURRENCY_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<CurrencyResultDTO> searchCurrencyRateList(@RequestBody SearchDTO<CurrencySearchDTO> searchDTO) {
        log.info("searchCurrencyRateList() start - " + searchDTO);
        Page<CurrencyResultDTO> page = currencyService.findByCriteria(searchDTO);
        log.info("searchCurrencyRateList() end - " + page);
        return page;
    }

    @GetMapping(value = RequestUriConstant.URI_FIND_ALL)
    public List<CurrencyBasicInfoDTO> findAll() {
        log.info("findAll() start - ");
        List<CurrencyBasicInfoDTO> infos = currencyService.findAll();
        log.info("findAll() end - " + infos);
        return infos;
    }

}
