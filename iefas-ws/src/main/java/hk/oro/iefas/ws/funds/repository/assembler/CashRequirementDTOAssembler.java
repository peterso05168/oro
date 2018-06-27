package hk.oro.iefas.ws.funds.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.funds.dto.CashRequirementResultDTO;
import hk.oro.iefas.domain.funds.dto.InvestmentTypeDTO;
import hk.oro.iefas.domain.funds.entity.CashReq;
import hk.oro.iefas.domain.funds.entity.InvType;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import hk.oro.iefas.ws.funds.repository.InvTypeRepository;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CashRequirementDTOAssembler extends PagingAssemblerSupport<CashRequirementResultDTO, CashReq> {

    @Autowired
    private InvTypeRepository invTypeRepository;

    @Override
    public CashRequirementResultDTO toDTO(CashReq cashRequirement) {
        CashRequirementResultDTO dto = null;
        if (cashRequirement != null) {
            dto = DataUtils.copyProperties(cashRequirement, CashRequirementResultDTO.class);

            InvestmentTypeDTO investmentTypeDTO = null;
            InvType investType = cashRequirement.getInvestType();
            if (investType != null) {
                investmentTypeDTO = InvestmentTypeDTOAssembler.toDto(investType);
            }
            dto.setInvestmentType(investmentTypeDTO);

            dto.setCashRequirementResultId(cashRequirement.getCashRequirementId());
            dto.setRqmtPeriodFrom(cashRequirement.getRqmtPeriodFrom());
            dto.setRqmtPeriodTo(cashRequirement.getRqmtPeriodTo());
            dto.setYearRequirement(cashRequirement.getYearlyRqmt());
            dto.setDailyRequirement(cashRequirement.getDailyRqmt());
            dto.setStatus(cashRequirement.getStatus());
        }
        return dto;
    }

    public CashReq toEntity(CashRequirementResultDTO dto) {
        if (dto != null) {
            CashReq cashReq = DataUtils.copyProperties(dto, CashReq.class);
            cashReq.setCashRequirementId(dto.getCashRequirementResultId());
            cashReq.setDailyRqmt(dto.getDailyRequirement());
            cashReq.setYearlyRqmt(dto.getYearRequirement());
            InvType invType = null;
            if (dto.getInvestmentType() != null) {
                invType = invTypeRepository.findOne(dto.getInvestmentType().getInvestmentTypeId());
            }
            cashReq.setInvestType(invType);
            return cashReq;
        }
        return null;
    }

}
