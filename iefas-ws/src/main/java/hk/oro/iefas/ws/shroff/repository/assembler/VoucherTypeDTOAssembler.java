/**
 * 
 */
package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.VoucherTypeDTO;
import hk.oro.iefas.domain.shroff.entity.VoucherType;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class VoucherTypeDTOAssembler extends AssemblerSupport<VoucherTypeDTO, VoucherType> {

    @Override
    public VoucherTypeDTO toDTO(VoucherType entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, VoucherTypeDTO.class);
        }
        return null;
    }

}
