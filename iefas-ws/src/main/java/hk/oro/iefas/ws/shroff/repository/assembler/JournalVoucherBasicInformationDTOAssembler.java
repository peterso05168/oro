/**
 * 
 */
package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherBasicInformationDTO;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class JournalVoucherBasicInformationDTOAssembler
    extends AssemblerSupport<JournalVoucherBasicInformationDTO, Voucher> {

    @Override
    public JournalVoucherBasicInformationDTO toDTO(Voucher entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, JournalVoucherBasicInformationDTO.class);
        }
        return null;
    }

}
