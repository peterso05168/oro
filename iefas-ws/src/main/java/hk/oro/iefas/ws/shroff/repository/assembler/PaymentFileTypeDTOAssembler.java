package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.PaymentFileTypeDTO;
import hk.oro.iefas.domain.shroff.entity.PaymentFileType;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class PaymentFileTypeDTOAssembler extends PagingAssemblerSupport<PaymentFileTypeDTO, PaymentFileType> {
    @Override
    public PaymentFileTypeDTO toDTO(PaymentFileType entity) {
        PaymentFileTypeDTO result = null;
        if (entity != null) {
            result = DataUtils.copyProperties(entity, PaymentFileTypeDTO.class);
        }
        return result;
    }
}
