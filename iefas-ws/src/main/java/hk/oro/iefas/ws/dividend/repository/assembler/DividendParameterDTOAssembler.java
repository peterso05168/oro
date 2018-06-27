package hk.oro.iefas.ws.dividend.repository.assembler;

import java.util.List;
import java.util.stream.Collectors;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.DividendParameterDTO;
import hk.oro.iefas.domain.dividend.entity.DivParameter;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class DividendParameterDTOAssembler {
    public static DividendParameterDTO toDto(DivParameter divParameter) {
        if (divParameter == null) {
            return null;
        }
        DividendParameterDTO dto = DataUtils.copyProperties(divParameter, DividendParameterDTO.class);
        return dto;
    }

    public static List<DividendParameterDTO> toDtoList(List<DivParameter> divParameters) {
        if (CommonUtils.isEmpty(divParameters)) {
            return null;
        }
        List<DividendParameterDTO> list
            = divParameters.stream().map(type -> toDto(type)).filter(dto -> dto != null).collect(Collectors.toList());
        return list;
    }

    public static DivParameter toEntity(DividendParameterDTO dto) {
        if (dto != null) {
            DivParameter DivParameter = DataUtils.copyProperties(dto, DivParameter.class);
            return DivParameter;
        }
        return null;
    }

    public static List<DivParameter> toEntity(List<DividendParameterDTO> dtos) {
        if (CommonUtils.isEmpty(dtos)) {
            return null;
        }
        List<DivParameter> list
            = dtos.stream().map(type -> toEntity(type)).filter(entity -> entity != null).collect(Collectors.toList());
        return list;
    }
}
