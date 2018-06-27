package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.shroff.dto.PaymentVoucherResultDTO;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2691 $ $Date: 2018-05-29 16:10:59 +0800 (週二, 29 五月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class PaymentVoucherResultDTOAssembler extends PagingAssemblerSupport<PaymentVoucherResultDTO, Voucher> {

    @Override
    public PaymentVoucherResultDTO toDTO(Voucher entity) {
        if (entity != null) {
            PaymentVoucherResultDTO dto = new PaymentVoucherResultDTO();
            dto.setPaymentDate(entity.getVoucherDate());
            dto.setPaymentVoucherId(entity.getVoucherId());
            dto.setVoucherNumber(entity.getVoucherNo());
            dto.setStatus(entity.getStatus());
            return dto;
        }
        return null;
    }

}
