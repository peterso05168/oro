package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherBasicInfoDTO;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2616 $ $Date: 2018-05-25 17:12:57 +0800 (週五, 25 五月 2018) $
 * @author $Author: george.wu $
 */

@Component
public class ReceiptVoucherBasicInformationDTOAssembler extends AssemblerSupport<ReceiptVoucherBasicInfoDTO, Voucher> {

    @Override
    public ReceiptVoucherBasicInfoDTO toDTO(Voucher entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, ReceiptVoucherBasicInfoDTO.class);
        }
        return null;
    }
}
