package hk.oro.iefas.ws.funds.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.funds.dto.InvestmentStatusDTO;
import hk.oro.iefas.domain.funds.dto.InvestmentTypeDTO;
import hk.oro.iefas.ws.funds.service.FundsCommonService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_FUNDS_COMMON)
public class FundsCommonController {

    @Autowired
    private FundsCommonService fundsCommonService;

    @GetMapping(value = RequestUriConstant.URI_INVESTMENT_TYPE_LIST)
    @ResponseBody
    public List<InvestmentTypeDTO> searchInvestmentType() {
        log.info("searchInvestmentType() start - ");
        List<InvestmentTypeDTO> investmentTypeDTOs = fundsCommonService.searchInvestmentType();
        log.info("searchInvestmentType() end - return : " + investmentTypeDTOs);
        return investmentTypeDTOs;
    }

    @GetMapping(value = RequestUriConstant.URI_INVESTMENT_STATUS_LIST)
    @ResponseBody
    // TODO get values from application code table
    public List<InvestmentStatusDTO> searchInvestmentStatus() {
        List<InvestmentStatusDTO> list = new ArrayList<>();
        Map<String, String> map = new TreeMap<>();
        map.put("Pending for investment", "PEN");
        map.put("Submited for investment", "SUB");
        map.put("Executing investment", "EXE");
        map.put("Investment has early Uplift", "UPL");
        map.put("Investment has Revert", "REV");
        map.put("Investment has Matured", "MAT");

        for (String key : map.keySet()) {
            InvestmentStatusDTO statusDTO = new InvestmentStatusDTO();
            statusDTO.setInvestmentStatusCode(map.get(key));
            statusDTO.setInvestmentStatusName(key);
            list.add(statusDTO);
        }
        return list;
    }
}
