package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.casemgt.entity.CaseCred;
import hk.oro.iefas.domain.dividend.dto.CreditorTypeDTO;
import hk.oro.iefas.ws.adjudication.repository.AdjResultRepository;
import hk.oro.iefas.ws.adjudication.repository.predicate.AdjResultPredicate;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;
import hk.oro.iefas.ws.dividend.repository.assembler.CreditorTypeDTOAssembler;

/**
 * @version $Revision: 3112 $ $Date: 2018-06-13 15:48:09 +0800 (週三, 13 六月 2018) $
 * @author $Author: noah.liang $
 */
@Component
public class CreditorDTOAssembler extends AssemblerSupport<CreditorDTO, CaseCred> {

    @Autowired
    private AdjResultRepository adjResultRepository;

    @Autowired
    private CreditorTypeDTOAssembler creditorTypeDTOAssembler;

    @Override
    public CreditorDTO toDTO(CaseCred entity) {
        CreditorDTO dto = null;
        if (entity != null && entity.getCreditorId() != null && entity.getCreditorId().intValue() > 0) {
            dto = DataUtils.copyProperties(entity, CreditorDTO.class);
            AdjResult adjResult
                = adjResultRepository.findOne(AdjResultPredicate.findByCreditorId(entity.getCreditorId()));
            if (adjResult != null) {
                CreditorTypeDTO creditorType = creditorTypeDTOAssembler.toDTO(adjResult.getCreditorType());
                dto.setCreditorType(creditorType);
                dto.setNatureOfClaim(adjResult.getNatureOfClaim());
            }
        }
        return dto;
    }
}
