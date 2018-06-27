package hk.oro.iefas.ws.shroff.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.BulkReversalDTO;
import hk.oro.iefas.domain.shroff.entity.QShrBulkRvl;
import hk.oro.iefas.domain.shroff.entity.ShrBulkRvl;
import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.entity.Holiday;
import hk.oro.iefas.domain.system.entity.QHoliday;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
public class BulkReversalDTOAssembler {

    public static BulkReversalDTO toDTO(ShrBulkRvl bulkReversal) {
    	BulkReversalDTO dto = null;
        if (bulkReversal != null) {
            dto = DataUtils.copyProperties(bulkReversal, BulkReversalDTO.class);
        }
        return dto;
    }

    public static QBean<BulkReversalDTO> toDTO() {
        QShrBulkRvl br = QShrBulkRvl.shrBulkRvl;
        QBean<BulkReversalDTO> dto = Projections.bean(BulkReversalDTO.class, 
        		br.bulkReversalId, 
        		br.processDate, 
        		br.cutOffDate, 
        		br.totalAmount,
        		br.remark,
        		br.status);
        return dto;
    }

    public static List<BulkReversalDTO> toDTOList(List<ShrBulkRvl> bulkReversalList) {
        List<BulkReversalDTO> list = null;
        if (CommonUtils.isNotEmpty(bulkReversalList)) {
            list = new ArrayList<BulkReversalDTO>();
            for (ShrBulkRvl br : bulkReversalList) {
                list.add(toDTO(br));
            }
        }
        return list;
    }
}
