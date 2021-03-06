package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.counter.dto.ProofOfDebtDTO;
import hk.oro.iefas.domain.counter.entity.CtrProofDebt;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class ProofOfDebtDTOAssembler extends PagingAssemblerSupport<ProofOfDebtDTO, CtrProofDebt> {

    @Override
    public ProofOfDebtDTO toDTO(CtrProofDebt t) {
        ProofOfDebtDTO dto = null;
        if (t != null) {
            dto = DataUtils.copyProperties(t, ProofOfDebtDTO.class);
        }
        return dto;
    }

}
