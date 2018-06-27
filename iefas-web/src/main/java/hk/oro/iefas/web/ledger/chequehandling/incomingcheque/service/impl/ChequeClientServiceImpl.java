package hk.oro.iefas.web.ledger.chequehandling.incomingcheque.service.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.ChequeVO;
import hk.oro.iefas.domain.shroff.vo.SearchIncomingChequeVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.chequehandling.incomingcheque.service.ChequeClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Named
@Slf4j
public class ChequeClientServiceImpl extends BaseClientService implements ChequeClientService {
    @Override
    public ChequeVO getIncomingChequeDetail(Integer chequeId) {
        log.info("getIncomingChequeDetail() start - chequeId : " + chequeId);
        ChequeVO chequeVO;
        ResponseEntity<ChequeVO> chequeVOResponseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_GET_INCOMING_CHEQUE_DETAIL_BY_ID, chequeId, ChequeVO.class);
        chequeVO = chequeVOResponseEntity.getBody();
        log.info("getIncomingChequeDetail end - " + chequeVO);
        return chequeVO;
    }

    @Override
    public Integer saveIncomingCheque(ChequeVO chequeVO) {
        log.info("saveIncomingCheque() start - chequeVO : " + chequeVO);
        Integer chequeId;
        ResponseEntity<Integer> chequeIdResponseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_INCOMING_CHEQUE, chequeVO, Integer.class);
        chequeId = chequeIdResponseEntity.getBody();
        log.info("saveIncomingCheque end - " + chequeId);
        return chequeId;
    }

    @Override
    public Integer saveOutgoingCheque(ChequeVO chequeVO) {
        log.info("saveOutgoingCheque() start - chequeVO : " + chequeVO);
        Integer chequeId;
        ResponseEntity<Integer> chequeIdResponseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_OUTGOING_CHEQUE, chequeVO, Integer.class);
        chequeId = chequeIdResponseEntity.getBody();
        log.info("saveOutgoingCheque end - " + chequeId);
        return chequeId;
    }

    @Override
    public Boolean existsByChequeNumber(SearchIncomingChequeVO searchIncomingChequeVO) {
        log.info("existsByChequeNumber start - searchIncomingChequeVO: " + searchIncomingChequeVO);
        Boolean exists = null;
        ResponseEntity<Boolean> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_CHEQUE_EXISTS_BY_CHEQUE_NUMBER, searchIncomingChequeVO, Boolean.class);
        if (responseEntity != null) {
            exists = responseEntity.getBody();
        }
        log.info("existsByChequeNumber end - " + exists);
        return exists;
    }

    @Override
    public ChequeVO getChequeDetailByChequeNo(String chequeNo) {
        log.info("getChequeDetailByChequeNo start - chequeNo: " + chequeNo);
        ChequeVO result = null;
        if (chequeNo != null) {
            ResponseEntity<ChequeVO> responseEntity = this.postForEntity(
                RequestUriConstant.CLIENT_URI_CHEQUE_GET_CHEQUE_DETAIL_BY_CHEQUE_NO, chequeNo, ChequeVO.class);
            if (responseEntity != null)
                result = responseEntity.getBody();
        }
        log.info("getChequeDetailByChequeNo end - " + result);
        return result;
    }

    @Override
    public List<ChequeVO> getOutgoingChequeByParentId(Integer parentId) {
        log.info("getOutgoingChequeByParentId start - parentId: " + parentId);
        List<ChequeVO> result = null;
        if (parentId != null) {
            ResponseEntity<List<ChequeVO>> responseEntity
                = this.exchange(RequestUriConstant.CLIENT_URI_CHEQUE_GET_OUTGOING_CHEQUE_LIST_BY_PARENT_ID,
                    HttpMethod.POST, new HttpEntity<>(parentId), new ParameterizedTypeReference<List<ChequeVO>>() {});
            if (responseEntity != null)
                result = responseEntity.getBody();
        }
        log.info("getOutgoingChequeByParentId end - " + result);
        return result;
    }

    @Override
    public ChequeVO getOutgoingChequeDetail(Integer chequeId) {
        log.info("getOutgoingChequeDetail start - chequeId: " + chequeId);
        ChequeVO chequeVO = null;
        ResponseEntity<ChequeVO> responseEntity
            = this.postForEntity(RequestUriConstant.CLIENT_URI_GET_OUTGOING_CHEQUE_DETAIL, chequeId, ChequeVO.class);
        if (responseEntity != null) {
            chequeVO = responseEntity.getBody();
        }
        return chequeVO;
    }

    @Override
    public Integer combineOutgoingCheque(Set<Integer> chequeIds) {
        log.info("combineOutgoingCheque start - chequeIds: " + chequeIds);
        Integer result = null;
        ResponseEntity<Integer> responseEntity = this
            .postForEntity(RequestUriConstant.CLIENT_URI_CHEQUE_COMBINE_OUTGOING_CHEQUE, chequeIds, Integer.class);
        if (responseEntity != null) {
            result = responseEntity.getBody();
        }
        log.info("combineOutgoingCheque end - " + result);
        return result;
    }
}
