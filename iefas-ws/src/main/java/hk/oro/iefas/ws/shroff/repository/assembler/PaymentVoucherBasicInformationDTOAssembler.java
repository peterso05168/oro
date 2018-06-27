package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherBasicInformationDTO;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2925 $ $Date: 2018-06-05 21:36:41 +0800 (週二, 05 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class PaymentVoucherBasicInformationDTOAssembler
    extends AssemblerSupport<PaymentVoucherBasicInformationDTO, Voucher> {

    @Autowired
    private ControlAccountDTOAssembler controlAccountDTOAssembler;

    @Override
    public PaymentVoucherBasicInformationDTO toDTO(Voucher entity) {
        if (entity != null) {
            PaymentVoucherBasicInformationDTO dto
                = DataUtils.copyProperties(entity, PaymentVoucherBasicInformationDTO.class);
            dto.setControlAccount(controlAccountDTOAssembler.toDTO(entity.getControlAccount()));
            return dto;
        }
        return null;
    }

}
