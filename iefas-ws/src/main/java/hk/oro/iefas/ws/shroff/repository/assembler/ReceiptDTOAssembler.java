package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.ReceiptDTO;
import hk.oro.iefas.domain.shroff.entity.ShrReceipt;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Component
public class ReceiptDTOAssembler extends PagingAssemblerSupport<ReceiptDTO, ShrReceipt> {
    @Override
    public ReceiptDTO toDTO(ShrReceipt entity) {
        ReceiptDTO receiptDTO = null;
        if (entity != null)
            receiptDTO = DataUtils.copyProperties(entity, ReceiptDTO.class);
        return receiptDTO;
    }
}
