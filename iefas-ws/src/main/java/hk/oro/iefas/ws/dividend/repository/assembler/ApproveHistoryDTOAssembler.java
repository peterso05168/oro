package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.ApproveHistoryDTO;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class ApproveHistoryDTOAssembler extends AssemblerSupport<ApproveHistoryDTO, SysApprovalWf> {

    @Override
    public ApproveHistoryDTO toDTO(SysApprovalWf entity) {
        ApproveHistoryDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, ApproveHistoryDTO.class);
            dto.setActionDate(entity.getCreateDate());
        }
        return dto;
    }

}
