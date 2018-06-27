package hk.oro.iefas.ws.funds.repository.assembler;

import java.util.List;
import java.util.stream.Collectors;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.funds.dto.InvestmentTypeDTO;
import hk.oro.iefas.domain.funds.entity.InvType;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class InvestmentTypeDTOAssembler {

    public static InvestmentTypeDTO toDto(InvType investmentType) {
        if (investmentType == null) {
            return null;
        }
        InvestmentTypeDTO dto = DataUtils.copyProperties(investmentType, InvestmentTypeDTO.class);
        dto.setInvestmentTypeId(investmentType.getInvestmentTypeId());
        dto.setInvestmentTypeDesc(investmentType.getInvestTypeCode());
        return dto;
    }

    public static List<InvestmentTypeDTO> toDtoList(List<InvType> investmentTypes) {
        if (CommonUtils.isEmpty(investmentTypes)) {
            return null;
        }
        List<InvestmentTypeDTO> list
            = investmentTypes.stream().map(type -> toDto(type)).filter(dto -> dto != null).collect(Collectors.toList());
        return list;
    }
}
