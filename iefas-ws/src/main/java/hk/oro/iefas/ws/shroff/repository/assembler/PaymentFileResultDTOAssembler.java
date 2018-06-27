package hk.oro.iefas.ws.shroff.repository.assembler;

import hk.oro.iefas.domain.shroff.dto.PaymentFileResultDTO;
import hk.oro.iefas.domain.shroff.entity.ShrPaymentFile;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class PaymentFileResultDTOAssembler extends PagingAssemblerSupport<PaymentFileResultDTO, ShrPaymentFile> {
    @Override
    public PaymentFileResultDTO toDTO(ShrPaymentFile entity) {
        PaymentFileResultDTO result = null;
        if (entity != null) {
            result = new PaymentFileResultDTO();
            if (entity.getCurcy() != null)
                result.setCurrency(entity.getCurcy().getCurcyName());
            result.setPaymentFileId(entity.getPaymentFileId());
            if (entity.getPaymentFileType() != null)
                result.setPaymentFileType(entity.getPaymentFileType().getPaymentFileTypeName());
            result.setTotalAmount(entity.getTotalAmount());
            result.setProcessDate(entity.getProcessDate());
            result.setStatus(entity.getStatus());
        }
        return result;
    }
}
