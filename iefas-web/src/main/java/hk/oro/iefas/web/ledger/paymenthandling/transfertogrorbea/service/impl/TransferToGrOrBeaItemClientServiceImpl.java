package hk.oro.iefas.web.ledger.paymenthandling.transfertogrorbea.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.TransferToGrOrBeaItemVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.paymenthandling.transfertogrorbea.service.TransferToGrOrBeaItemClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3189 $ $Date: 2018-06-19 13:34:32 +0800 (週二, 19 六月 2018) $
 * @author $Author: dante.fang $
 */
@Named
@Slf4j
public class TransferToGrOrBeaItemClientServiceImpl extends BaseClientService
    implements TransferToGrOrBeaItemClientService {

    @Override
    public Integer saveTransferToGrOrBeaItem(TransferToGrOrBeaItemVO item) {
        log.info("saveTransferToGrOrBeaItem start - transfer item: " + item);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_TRANSFER_TO_BEA_OR_GR_ITEM_DETAIL, item, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveTransferToGrOrBeaItem end - transfer Id: " + body);
        return body;

    }

    @Override
    public List<TransferToGrOrBeaItemVO> findTransferToGrOrBeaItemByTransfer(Integer transferId) {
        log.info("findTransferToGrOrBeaItemByTransfer() start - transferId: " + transferId);
        ResponseEntity<List<TransferToGrOrBeaItemVO>> responseEntity = exchange(
            RequestUriConstant.CLIENT_URI_FIND_TRANSFER_ITEM_BY_TRANSFER, HttpMethod.POST,
            new HttpEntity<Integer>(transferId), new ParameterizedTypeReference<List<TransferToGrOrBeaItemVO>>() {});
        List<TransferToGrOrBeaItemVO> body = responseEntity.getBody();
        log.info("findTransferToGrOrBeaItemByTransfer() end - " + body);
        return body;
    }

}
