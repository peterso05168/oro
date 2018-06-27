/**
 * 
 */
package hk.oro.iefas.ws.release.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.release.dto.RelHistListDTO;
import hk.oro.iefas.domain.release.entity.RelHistList;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherBasicInformationDTO;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (Thu, 24 May 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class HistoricalCaseListBasicInformationDTOAssembler extends
    AssemblerSupport<RelHistListDTO, RelHistList> {

    @Override
    public RelHistListDTO toDTO(RelHistList entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, RelHistListDTO.class);
        }
        return null;
    }

}
