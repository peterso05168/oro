package hk.oro.iefas.ws.dividend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeTypeDTO;
import hk.oro.iefas.domain.dividend.dto.CreditorTypeDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.CommonDividendService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_COMMON_DIVIDEND)
public class CommonDividendController {

    @Autowired
    private CommonDividendService commonDividendService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_COMMON_DIVIDEND_ORFEEITEM_LIST)
    public Page<CaseFeeTypeDTO> searchORFeeItemList(@RequestBody SearchDTO<CaseTypeDTO> caseTypeDTO) {
        log.info("searchORFeeItemList() start - and param: " + caseTypeDTO);
        Page<CaseFeeTypeDTO> page = commonDividendService.searchORFeeItemList(caseTypeDTO);
        log.info("searchCashRequirementList() end - return : " + page);
        return page;
    }

    @GetMapping(value = RequestUriConstant.URI_CREDITOR_TYPE_LIST)
    public List<CreditorTypeDTO> searchCreditorType() {
        log.info("searchCreditorType() start");
        List<CreditorTypeDTO> list = commonDividendService.searchCreditorType();
        log.info("searchCreditorType() end list : " + list);
        return list;
    }
}
