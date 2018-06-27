package hk.oro.iefas.ws.organization.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.DivisionAdminDTO;
import hk.oro.iefas.domain.organization.dto.DivisionDTO;
import hk.oro.iefas.domain.organization.dto.SearchDivisionAdminResultDTO;
import hk.oro.iefas.domain.organization.entity.DivisionAdmin;
import hk.oro.iefas.domain.organization.entity.QDivisionAdmin;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class DivisionAdminDTOAssembler extends AssemblerSupport<DivisionAdminDTO, DivisionAdmin> {

    @Override
    public DivisionAdminDTO toDTO(DivisionAdmin DivisionAdmin) {
        DivisionAdminDTO dto = null;
        if (DivisionAdmin != null) {
            dto = DataUtils.copyProperties(DivisionAdmin, DivisionAdminDTO.class);
        }
        return dto;
    }

    public QBean<SearchDivisionAdminResultDTO> toDTO() {
        QDivisionAdmin d = QDivisionAdmin.divisionAdmin;
        QBean<SearchDivisionAdminResultDTO> dto
            = Projections.bean(SearchDivisionAdminResultDTO.class, d.divisionAdminId, d.division.divisionId,
                d.division.divisionName, d.post.postId, d.post.postTitle, d.status);
        return dto;
    }

    public QBean<DivisionDTO> toDivision() {
        QDivisionAdmin d = QDivisionAdmin.divisionAdmin;
        QBean<DivisionDTO> dto = Projections.bean(DivisionDTO.class, d.division.divisionId, d.division.divisionName,
            d.division.parentDivisionId, d.division.status, d.division.versionNo);
        return dto;
    }

    public List<DivisionDTO> toDivisionList(List<DivisionAdmin> divisionList) {
        List<DivisionDTO> list = null;
        if (CommonUtils.isNotEmpty(divisionList)) {
            list = new ArrayList<DivisionDTO>();
            for (DivisionAdmin DivisionAdmin : divisionList) {
                list.add(DivisionDTOAssembler.toDTO(DivisionAdmin.getDivision()));
            }
        }
        return list;
    }
}
