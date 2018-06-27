package hk.oro.iefas.web.ledger.paymenthandling.transfertogrorbea.service;

import hk.oro.iefas.domain.shroff.vo.TransferToGrOrBeaVO;

public interface TransferToGrOrBeaClientService {

    Integer saveTransferToGrOrBea(TransferToGrOrBeaVO transfer);
    
    TransferToGrOrBeaVO getTransferToGrOrBeaDetail(Integer transferId);
}
