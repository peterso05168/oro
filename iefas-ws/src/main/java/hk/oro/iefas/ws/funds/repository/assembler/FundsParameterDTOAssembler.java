package hk.oro.iefas.ws.funds.repository.assembler;

import java.util.List;
import java.util.stream.Collectors;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.funds.dto.FundsParameterDTO;
import hk.oro.iefas.domain.funds.entity.FundsParameter;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class FundsParameterDTOAssembler {

    public static FundsParameterDTO toDto(FundsParameter fundsParameter) {
        if (fundsParameter == null) {
            return null;
        }
        FundsParameterDTO dto = DataUtils.copyProperties(fundsParameter, FundsParameterDTO.class);
        return dto;
    }

    public static List<FundsParameterDTO> toDtoList(List<FundsParameter> fundsParameters) {
        if (CommonUtils.isEmpty(fundsParameters)) {
            return null;
        }
        List<FundsParameterDTO> list
            = fundsParameters.stream().map(type -> toDto(type)).filter(dto -> dto != null).collect(Collectors.toList());
        return list;
    }

    public static FundsParameter toEntity(FundsParameterDTO dto) {
        if (dto != null) {
            FundsParameter fundsParameter = DataUtils.copyProperties(dto, FundsParameter.class);
            return fundsParameter;
        }
        return null;
    }

    public static List<FundsParameter> toEntity(List<FundsParameterDTO> dtos) {
        if (CommonUtils.isEmpty(dtos)) {
            return null;
        }
        List<FundsParameter> list
            = dtos.stream().map(type -> toEntity(type)).filter(entity -> entity != null).collect(Collectors.toList());
        return list;
    }
}
