package hk.oro.iefas.ws.organization.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.DivisionDTO;
import hk.oro.iefas.domain.organization.entity.Division;
import hk.oro.iefas.domain.organization.entity.QDivision;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class DivisionDTOAssembler {

    public static DivisionDTO toDTO(Division division) {
        DivisionDTO dto = null;
        if (division != null) {
            dto = DataUtils.copyProperties(division, DivisionDTO.class);
        }
        return dto;
    }

    public static QBean<DivisionDTO> toDTO() {
        QDivision d = QDivision.division;
        QBean<DivisionDTO> dto
            = Projections.bean(DivisionDTO.class, d.divisionId, d.divisionName, d.parentDivisionId, d.status);
        return dto;

    }

    public static List<DivisionDTO> toDTOList(List<Division> divisionList) {
        List<DivisionDTO> list = null;
        if (CommonUtils.isNotEmpty(divisionList)) {
            list = new ArrayList<DivisionDTO>();
            for (Division division : divisionList) {
                list.add(toDTO(division));
            }
        }
        return list;
    }
}
