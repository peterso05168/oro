package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.adjudication.entity.CredType;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleCreditorDTO;
import hk.oro.iefas.domain.dividend.entity.DivScheduleCred;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

@Component
public class DividendScheduleCreditorDTOAssembler
    extends AssemblerSupport<DividendScheduleCreditorDTO, DivScheduleCred> {

    @Autowired
    private CreditorTypeDTOAssembler creditorTypeDTOAssembler;

    @Override
    public DividendScheduleCreditorDTO toDTO(DivScheduleCred entity) {
        if (entity != null) {
            DividendScheduleCreditorDTO dto = new DividendScheduleCreditorDTO();
            dto.setScheduleCredId(entity.getScheduleCredId());
            dto.setRemark(entity.getRemark());
            dto.setStatus(entity.getStatus());
            dto.setDivScheduledId(entity.getDivSchdId());
            CredType credType = entity.getAdjCredType();
            if (credType != null) {
                dto.setAdjCredType(creditorTypeDTOAssembler.toDTO(credType));
            }
            return dto;
        }
        return null;
    }

}
