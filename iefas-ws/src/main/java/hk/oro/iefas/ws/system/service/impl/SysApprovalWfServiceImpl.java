/**
 * 
 */
package hk.oro.iefas.ws.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.oro.iefas.domain.dividend.dto.ApproveHistoryDTO;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.dividend.repository.assembler.ApproveHistoryDTOAssembler;
import hk.oro.iefas.ws.system.repository.SysApprovalWFRepository;
import hk.oro.iefas.ws.system.service.SysApprovalWfService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2904 $ $Date: 2018-06-05 16:37:01 +0800 (週二, 05 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class SysApprovalWfServiceImpl implements SysApprovalWfService {

    @Autowired
    private SysApprovalWFRepository sysApprovalWFRepository;
    
    @Autowired
    private ApproveHistoryDTOAssembler approveHistoryDTOAssembler;

    @Override
    public List<ApproveHistoryDTO> findByWorkflowId(Integer workFlowId, String status) {
        log.info("findByWorkflowId() start - WorkFlowId: " + workFlowId + ", Status: " + status);
        List<SysApprovalWf> approvalWorkflows
            = sysApprovalWFRepository.findByWorkflowIdAndStatusIgnoreCaseOrderByApprovalWorkflowId(workFlowId, status);
        List<ApproveHistoryDTO> dtoList = approveHistoryDTOAssembler.toDTOList(approvalWorkflows);
        log.info("findByWorkflowId() end - "+dtoList);
        return dtoList;
    }

}
