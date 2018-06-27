package hk.oro.iefas.web.ledger.paymenthandling.transfertogrorbea.service;

import java.util.List;

import hk.oro.iefas.domain.shroff.vo.TransferToGrOrBeaItemVO;

public interface TransferToGrOrBeaItemClientService {

    Integer saveTransferToGrOrBeaItem(TransferToGrOrBeaItemVO item);

    List<TransferToGrOrBeaItemVO> findTransferToGrOrBeaItemByTransfer(Integer transferId);
}
