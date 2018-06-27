package hk.oro.iefas.web.ledger.paymenthandling.transfertogrorbea.service.impl;

import javax.inject.Named;

import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.TransferToGrOrBeaVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.paymenthandling.transfertogrorbea.service.TransferToGrOrBeaClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
@Named
@Slf4j
public class TransferToGrOrBeaClientServiceImpl extends BaseClientService implements TransferToGrOrBeaClientService {

    @Override
    public TransferToGrOrBeaVO getTransferToGrOrBeaDetail(Integer transferId) {
        log.info("getTransferToGrOrBeaDetail() start - transferId: " + transferId);
        ResponseEntity<TransferToGrOrBeaVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_GET_TRANSFER_TO_BEA_OR_GR_DETAIL, transferId, TransferToGrOrBeaVO.class);
        TransferToGrOrBeaVO result = responseEntity.getBody();
        log.info("findVoucherAttachmentDetail() end - " + result);
        return result;
    }

    @Override
    public Integer saveTransferToGrOrBea(TransferToGrOrBeaVO transfer) {
        log.info("saveTransferToGrOrBea start - transfer: " + transfer);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_TRANSFER_TO_BEA_OR_GR_DETAIL, transfer, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveTransferToGrOrBea end - transfer Id: " + body);
        return body;
    }

}
