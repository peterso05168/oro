package hk.oro.iefas.ws.dividend.repository.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.WilonAndSeverancePayDTO;
import hk.oro.iefas.domain.dividend.entity.DivWilonAndSever;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2961 $ $Date: 2018-06-06 20:14:53 +0800 (週三, 06 六月 2018) $
 * @author $Author: noah.liang $
 */
@Component
public class WilonAndSeveranceDTOAssembler extends PagingAssemblerSupport<WilonAndSeverancePayDTO, DivWilonAndSever> {

    @Override
    public WilonAndSeverancePayDTO toDTO(DivWilonAndSever entity) {
        if (entity == null) {
            return null;
        }
        WilonAndSeverancePayDTO dto = DataUtils.copyProperties(entity, WilonAndSeverancePayDTO.class);
        return dto;
    }

    public DivWilonAndSever toEntity(WilonAndSeverancePayDTO dto) {
        if (dto != null) {
            DivWilonAndSever output = DataUtils.copyProperties(dto, DivWilonAndSever.class);
            return output;
        }
        return null;
    }

    public List<DivWilonAndSever> toEntity(List<WilonAndSeverancePayDTO> dtos) {
        if (CommonUtils.isEmpty(dtos)) {
            return null;
        }
        List<DivWilonAndSever> list
            = dtos.stream().map(type -> toEntity(type)).filter(entity -> entity != null).collect(Collectors.toList());
        return list;
    }
}
