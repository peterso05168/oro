package hk.oro.iefas.ws.release.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.release.dto.RelHistListDTO;
import hk.oro.iefas.domain.release.entity.QRelHistList;
import hk.oro.iefas.domain.release.entity.RelHistList;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (Mon, 09 Apr 2018) $
 * @author $Author: marvel.ma $
 */
public class HistoricalCaseListDTOAssembler {

    public static RelHistListDTO toDTO(RelHistList relHistList) {
    	RelHistListDTO dto = null;
        if (relHistList != null) {
            dto = DataUtils.copyProperties(relHistList, RelHistListDTO.class);
        }
        return dto;
    }

    public static QBean<RelHistListDTO> toDTO() {
        QRelHistList h = QRelHistList.relHistList;
        QBean<RelHistListDTO> dto = Projections.bean(RelHistListDTO.class, h.histCaseListId, h.histCaseListDate, h.histCaseListDesc, h.status);
        return dto;
    }

    public static List<RelHistListDTO> toDTOList(List<RelHistList> relHistList) {
        List<RelHistListDTO> list = null;
        if (CommonUtils.isNotEmpty(relHistList)) {
            list = new ArrayList<RelHistListDTO>();
            for (RelHistList relHist : relHistList) {
                list.add(toDTO(relHist));
            }
        }
        return list;
    }
}
