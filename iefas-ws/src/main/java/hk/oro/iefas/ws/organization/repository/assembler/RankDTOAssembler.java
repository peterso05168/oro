/**
 * 
 */
package hk.oro.iefas.ws.organization.repository.assembler;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DslUtils;
import hk.oro.iefas.domain.organization.dto.RankDTO;
import hk.oro.iefas.domain.organization.entity.QRank;
import hk.oro.iefas.domain.organization.entity.Rank;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class RankDTOAssembler {

    public static RankDTO toDTO(Rank rank) {
        RankDTO dto = null;
        if (rank != null) {
            dto = DataUtils.copyProperties(rank, RankDTO.class);
        }
        return dto;
    }

    public static QBean<RankDTO> toDTO() {
        return Projections.bean(RankDTO.class, DslUtils.getAllExpression(QRank.rank));
    }

}
