/**
 * 
 */
package hk.oro.iefas.ws.organization.repository.assembler;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.SearchRoleResultDTO;
import hk.oro.iefas.domain.security.dto.PostRoleDTO;
import hk.oro.iefas.domain.security.entity.PostRole;
import hk.oro.iefas.domain.security.entity.QPostRole;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class PostRoleDTOAssembler {

    public static PostRoleDTO toDTO(PostRole postRole) {
        PostRoleDTO dto = null;
        if (postRole != null) {
            dto = DataUtils.copyProperties(postRole, PostRoleDTO.class);
        }
        return dto;
    }

    public static QBean<SearchRoleResultDTO> toDTO() {
        QPostRole postRole = QPostRole.postRole;
        return Projections.bean(SearchRoleResultDTO.class, postRole.role.roleId, postRole.post.postId,
            postRole.post.division.divisionName, postRole.role.roleName, postRole.role.roleDesc, postRole.status);
    }
}
