package hk.oro.iefas.ws.shroff.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.*;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.ChequeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@RestController
@Slf4j
@RequestMapping(value = RequestUriConstant.URI_ROOT_CHEQUE)
public class ChequeController {

    @Autowired
    private ChequeService chequeService;

    @PostMapping(RequestUriConstant.URI_SEARCH_OUTGOING_CHEQUE_LIST_BY_CRITERIA)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<OutgoingChequeResultDTO>
        searchOutgoingChequeList(@RequestBody SearchDTO<SearchOutgoingChequeDTO> criteria) {
        log.info("searchOutgoingChequeList() start - criteria : " + criteria);
        Page<OutgoingChequeResultDTO> result = chequeService.searchOutgoingChequeByCriteria(criteria);
        log.info("searchOutgoingChequeList end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_GET_INCOMING_CHEQUE_DETAIL_BY_ID)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public ChequeDTO getIncomingChequeDetail(@RequestBody Integer chequeId) {
        log.info("getIncomingChequeDetail() start - chequeId : " + chequeId);
        ChequeDTO result = chequeService.getIncomingChequeDetail(chequeId);
        log.info("getIncomingChequeDetail end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_SAVE_INCOMING_CHEQUE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public Integer saveIncomingCheque(@RequestBody ChequeDTO chequeDTO) {
        log.info("saveIncomingCheque() start - chequeDTO : " + chequeDTO);
        Integer result = chequeService.saveIncomingCheque(chequeDTO);
        log.info("saveIncomingCheque() end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_SEARCH_INCOMING_CHEQUE_LIST_BY_CRITERIA)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<IncomingChequeResultDTO>
        searchIncomingChequeList(@RequestBody SearchDTO<SearchIncomingChequeDTO> criteria) {
        log.info("searchIncomingChequeList() start - criteria : " + criteria);
        Page<IncomingChequeResultDTO> result = chequeService.searchIncomingChequeByCriteria(criteria);
        log.info("searchIncomingChequeList end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_SAVE_OUTGOING_CHEQUE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public Integer saveOutgoingCheque(@RequestBody ChequeDTO chequeDTO) {
        log.info("saveOutgoingCheque() start - chequeDTO : " + chequeDTO);
        Integer result = chequeService.saveOutgoingCheque(chequeDTO);
        log.info("saveOutgoingCheque() end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_CHEQUE_EXISTS_BY_CHEQUE_NUMBER)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Boolean existsByChequeNumber(@RequestBody SearchIncomingChequeDTO searchIncomingChequeDTO) {
        log.info("existsByChequeNumber start - chequeNumber: " + searchIncomingChequeDTO);
        Boolean exists;
        exists = chequeService.existsByChequeNo(searchIncomingChequeDTO);
        log.info("existsByChequeNumber end - " + exists);
        return exists;
    }

    @PostMapping(RequestUriConstant.URI_CHEQUE_GET_CHEQUE_DETAIL_BY_CHEQUE_NO)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public ChequeDTO getChequeDetailByChequeNo(@RequestBody String chequeNo) {
        log.info("getChequeDetailByChequeNo start - chequeNo: " + chequeNo);
        ChequeDTO result = chequeService.getByChequeNo(chequeNo);
        log.info("getChequeDetailByChequeNo end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_GET_OUTGOING_CHEQUE_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public ChequeDTO getOutgoingChequeDetail(@RequestBody Integer outgoingChequeId) {
        log.info("getOutgoingChequeDetail start - outgoingChequeId: " + outgoingChequeId);
        ChequeDTO result = chequeService.getOutgoingChequeDetail(outgoingChequeId);
        log.info("getOutgoingChequeDetail end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_CHEQUE_COMBINE_OUTGOING_CHEQUE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public Integer combineOutgoingCheque(@RequestBody Set<Integer> outgoingChequeIds) {
        log.info("combineOutgoingCheque start - outgoingChequeIds: " + outgoingChequeIds);
        Integer result = chequeService.combineOutgoingCheque(outgoingChequeIds);
        log.info("combineOutgoingCheque end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_CHEQUE_GET_OUTGOING_CHEQUE_LIST_BY_PARENT_ID)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public List<ChequeDTO> getOutgoingChequeListByParentId(@RequestBody Integer chequeParentId) {
        log.info("getOutgoingChequeListByParentId start - chequeParentId: " + chequeParentId);
        List<ChequeDTO> result;
        result = chequeService.getOutgoingChequeListByParentId(chequeParentId);
        log.info("getOutgoingChequeListByParentId end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_CHEQUE_SEARCH_CHEQUE_FOR_CHEQUE_FILE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<ChequeFileResultDTO> searchChequeForChequeFile(@RequestBody SearchDTO<SearchChequeFileDTO> criteria) {
        log.info("searchChequeForChequeFile start - criteria: " + criteria);
        Page<ChequeFileResultDTO> result = chequeService.searchChequeForChequeFile(criteria);
        log.info("searchChequeForChequeFile end - " + result);
        return result;
    }

}
