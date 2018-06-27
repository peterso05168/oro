package hk.oro.iefas.ws.dividend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.dto.DividendChequeDTO;
import hk.oro.iefas.domain.dividend.dto.SearchDividendChequeDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.DividendChequeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (Sun, 10 Jun 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.ROOT_DIVIDEND_CHEQUE)
public class DividendChequeController {

    @Autowired
    private DividendChequeService dividendChequeService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_DIVIDEND_CHEQUE_LIST)
    public Page<DividendChequeDTO>
        searchDividendChequeList(@RequestBody SearchDTO<SearchDividendChequeDTO> criteriaDTO) {
        log.info("searchDividendChequeList() start criteriaDTO :" + criteriaDTO);
        Page<DividendChequeDTO> page = dividendChequeService.searchDividendChequeList(criteriaDTO);
        log.info("searchDividendChequeList() end - return : " + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_DIVIDEND_CHEQUE_CREATE)
    public Integer saveDividendCheque(@RequestBody DividendChequeDTO dividendChequeDTO) throws Exception {
        log.info("saveDividendCheque() start - " + dividendChequeDTO);
        Integer dividendChequeId = dividendChequeService.saveDividendCheque(dividendChequeDTO);
        log.info("saveDividendCheque() end return : " + dividendChequeId);
        return dividendChequeId;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_DIVIDEND_CHEQUE_DETAIL)
    public DividendChequeDTO searchDividendCheque(@RequestBody Integer dividendChequeId) {
        log.info("searchDividendCheque() start dividendChequeId:" + dividendChequeId);
        DividendChequeDTO dto = dividendChequeService.searchDividendCheque(dividendChequeId);
        log.info("searchDividendCheque() end - return : " + dto);
        return dto;
    }

    @PostMapping(value = RequestUriConstant.SAVE_DIVIDEND_CHEQUELIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE)
    public boolean saveDividendChequeList(@RequestBody List<DividendChequeDTO> dividendChequeList) {
        log.info("saveDividendChequeList() start dividendChequeList:" + dividendChequeList);
        boolean result = dividendChequeService.saveDividendChequeList(dividendChequeList);
        log.info("saveDividendChequeList() end - return : result =" + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_DIVIDEND_CHEQUE_LIST_BY_DIVSCHEID)
    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<DividendChequeDTO> searchDividendChequeList(@RequestBody List<Integer> divScheItemIdList) {
        log.info("searchDividendChequeList() start dividendChequeList:" + divScheItemIdList);
        List<DividendChequeDTO> result = dividendChequeService.searchDividendChequeList(divScheItemIdList);
        log.info("searchDividendChequeList() end - return : result =" + result);
        return result;
    }

}
