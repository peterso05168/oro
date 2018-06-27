package hk.oro.iefas.ws.adjudication.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.dividend.dto.AdjucationDTO;
import hk.oro.iefas.ws.casemgt.repository.assembler.CreditorDTOAssembler;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;
import hk.oro.iefas.ws.dividend.repository.assembler.AdjucationAccountDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.CreditorTypeDTOAssembler;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class AdjucationDTOAssembler extends AssemblerSupport<AdjucationDTO, AdjResult> {

    @Autowired
    private CreditorTypeDTOAssembler creditorTypeDTOAssembler;

    @Autowired
    private CreditorDTOAssembler creditorDTOAssembler;

    @Autowired
    private AdjucationAccountDTOAssembler adjucationAccountDTOAssembler;

    @Override
    public AdjucationDTO toDTO(AdjResult entity) {
        AdjucationDTO dto = null;
        if (entity != null && entity.getAdjResultId() != null && entity.getAdjResultId().intValue() > 0) {
            dto = DataUtils.copyProperties(entity, AdjucationDTO.class);
            dto.setAdjucationId(entity.getAdjResultId());
            dto.setCreditor(creditorDTOAssembler.toDTO(entity.getCaseCred()));
            dto.setCreditorType(creditorTypeDTOAssembler.toDTO(entity.getCreditorType()));
            dto.setAddressType(entity.getLocal());
            dto.setAdjucationAccounts(adjucationAccountDTOAssembler.toDTOList(entity.getAdjAdjAccNumbers()));
        }
        return dto;
    }
}
