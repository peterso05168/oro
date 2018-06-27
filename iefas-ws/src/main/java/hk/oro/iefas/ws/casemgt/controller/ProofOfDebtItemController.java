package hk.oro.iefas.ws.casemgt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.SearchProofOfDebtCriteriaDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchProofOfDebtResultDTO;
import hk.oro.iefas.domain.counter.dto.ProofOfDebtDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.casemgt.service.ProofOfDebtService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_PROOF_OF_DEBT_ITEM)
public class ProofOfDebtItemController {

    @Autowired
    private ProofOfDebtService proofOfDebtService;

    @PostMapping(value = RequestUriConstant.URI_FIND_PROOF_OF_DEBT_BY_CRITERIA)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<SearchProofOfDebtResultDTO>
        findByCriteria(@RequestBody SearchDTO<SearchProofOfDebtCriteriaDTO> criteria) {
        log.info("findByCriteria() start - " + criteria);
        Page<SearchProofOfDebtResultDTO> roleList = proofOfDebtService.findByCriteria(criteria);
        log.info("findByCriteria() end ");
        return roleList;
    }

    @PostMapping(value = RequestUriConstant.URI_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    public Integer save(@Valid @RequestBody ProofOfDebtDTO proofOfDebtDTO) {
        log.info("saveProofOfDebtDetail() start - " + proofOfDebtDTO);
        Integer proofOfDebtId = proofOfDebtService.save(proofOfDebtDTO);
        log.info("saveProofOfDebtDetail() end and proofOfDebtId = " + proofOfDebtId);
        return proofOfDebtId;
    }

    @PostMapping(value = RequestUriConstant.URI_FIND_ONE)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public ProofOfDebtDTO findOne(@RequestBody Integer proofOfDebtId) {
        log.info("getProofOfDebtDetail() start - " + proofOfDebtId);
        ProofOfDebtDTO proofOfDebt = proofOfDebtService.findOne(proofOfDebtId);
        log.info("getProofOfDebtDetail() end ");
        return proofOfDebt;
    }
}
