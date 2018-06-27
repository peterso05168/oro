package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.PaymentTypeDTO;
import hk.oro.iefas.domain.shroff.entity.PaymentType;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2608 $ $Date: 2018-05-25 13:58:24 +0800 (週五, 25 五月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class PaymentTypeDTOAssembler extends AssemblerSupport<PaymentTypeDTO, PaymentType> {

    @Override
    public PaymentTypeDTO toDTO(PaymentType entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, PaymentTypeDTO.class);
        }
        return null;
    }

}
