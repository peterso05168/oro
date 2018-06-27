package hk.oro.iefas.ws.shroff.repository.assembler;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.counter.entity.CtrCaseDeposit;
import hk.oro.iefas.domain.shroff.dto.ReceiveDepositDTO;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
@Slf4j
public class ReceiveDepositDTOAssembler extends PagingAssemblerSupport<ReceiveDepositDTO, CtrCaseDeposit> {
    @Override
    public ReceiveDepositDTO toDTO(CtrCaseDeposit entity) {
        ReceiveDepositDTO result = null;
        if (entity != null) {
            result = DataUtils.copyProperties(entity, ReceiveDepositDTO.class);
        }
        return result;
    }
}
