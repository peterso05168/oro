/**
 * 
 */
package hk.oro.iefas.ws.system.service;

import java.util.List;

import hk.oro.iefas.domain.dividend.dto.ApproveHistoryDTO;

/**
 * @version $Revision: 2904 $ $Date: 2018-06-05 16:37:01 +0800 (週二, 05 六月 2018) $
 * @author $Author: marvel.ma $
 */
public interface SysApprovalWfService {

    List<ApproveHistoryDTO> findByWorkflowId(Integer workFlowId, String status);

}
