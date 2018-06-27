package hk.oro.iefas.ws.shroff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.TransferToGrOrBeaItemDTO;
import hk.oro.iefas.domain.shroff.entity.ShrTxfGrBeaItem;
import hk.oro.iefas.ws.shroff.repository.TransferToGrOrBeaItemRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.TransferToGrOrBeaItemDTOAssembler;
import hk.oro.iefas.ws.shroff.service.TransferToGrOrBeaItemService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3189 $ $Date: 2018-06-19 13:34:32 +0800 (週二, 19 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Service
public class TransferToGrOrBeaItemServiceImpl implements TransferToGrOrBeaItemService {

    @Autowired
    private TransferToGrOrBeaItemRepository transferToGrOrBeaItemRepository;

    @Autowired
    private TransferToGrOrBeaItemDTOAssembler transferToGrOrBeaItemDTOAssembler;

    @Transactional(readOnly = true)
    @Override
    public List<TransferToGrOrBeaItemDTO> findTransferItemByTransfer(Integer transferId) {
        log.info("getTransferToGrOrBeaDetail() start and transferId = " + transferId);
        List<ShrTxfGrBeaItem> list = transferToGrOrBeaItemRepository.findTransferItemByTransfer(transferId);
        List<TransferToGrOrBeaItemDTO> result = transferToGrOrBeaItemDTOAssembler.toDTOList(list);
        log.info("getTransferToGrOrBeaDetail() end and result = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveTransferToGrOrBeaItem(TransferToGrOrBeaItemDTO item) {
        log.info("saveTransferToGrOrBeaItem() start - transfer Item : " + item);
        Integer result = null;
        if (item != null) {
            ShrTxfGrBeaItem shrTxfGrBeaItem = DataUtils.copyProperties(item, ShrTxfGrBeaItem.class);
            transferToGrOrBeaItemRepository.save(shrTxfGrBeaItem);
            result = shrTxfGrBeaItem.getTransferItemId();
        }
        log.info("saveTransferToGrOrBea end - " + result);
        return result;
    }

}
