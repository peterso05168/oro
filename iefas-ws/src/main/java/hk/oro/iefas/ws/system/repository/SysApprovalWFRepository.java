package hk.oro.iefas.ws.system.repository;

import java.util.List;

import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2786 $ $Date: 2018-05-31 17:43:02 +0800 (週四, 31 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface SysApprovalWFRepository extends BaseRepository<SysApprovalWf, Integer> {
    List<SysApprovalWf> findByWorkflowIdAndStatusIgnoreCaseOrderByApprovalWorkflowId(Integer workFlowId, String status);
}
