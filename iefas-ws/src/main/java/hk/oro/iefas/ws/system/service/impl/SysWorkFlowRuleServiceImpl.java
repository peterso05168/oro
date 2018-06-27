/**
 * 
 */
package hk.oro.iefas.ws.system.service.impl;

import static hk.oro.iefas.core.util.Assert.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.WorkFlowAction;
import hk.oro.iefas.domain.common.dto.ActionDTO;
import hk.oro.iefas.domain.system.dto.SysWorkFlowRuleDTO;
import hk.oro.iefas.domain.system.entity.SysWfInitialStatus;
import hk.oro.iefas.domain.system.entity.SysWorkFlowRule;
import hk.oro.iefas.ws.system.repository.SysWfInitialStatusRepository;
import hk.oro.iefas.ws.system.repository.SysWorkFlowRuleRepository;
import hk.oro.iefas.ws.system.repository.assembler.SysWorkFlowRuleDTOAssembler;
import hk.oro.iefas.ws.system.repository.predicate.SysWorkFlowRulePredicate;
import hk.oro.iefas.ws.system.service.SysWorkFlowRuleService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3143 $ $Date: 2018-06-14 18:20:23 +0800 (週四, 14 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class SysWorkFlowRuleServiceImpl implements SysWorkFlowRuleService {

    @Autowired
    private SysWorkFlowRuleRepository sysWorkFlowRuleRepository;

    @Autowired
    private SysWorkFlowRuleDTOAssembler sysWorkFlowRuleDTOAssembler;

    @Autowired
    SysWfInitialStatusRepository sysWfInitialStatusRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SysWorkFlowRuleDTO> findByPrivilegeCode(String privilegeCode) {
        log.info("findByPrivilegeCode() start - PrivilegeCode: " + privilegeCode);
        List<SysWorkFlowRule> SysWorkFlowRules = sysWorkFlowRuleRepository.findByPrivilegePrivilegeCode(privilegeCode);
        List<SysWorkFlowRuleDTO> dtoList = sysWorkFlowRuleDTOAssembler.toDTOList(SysWorkFlowRules);
        log.info("findByPrivilegeCode() end - " + dtoList);
        return dtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public ActionDTO findAction(String privilegeCode, String beforeStatus) {
        log.info("findAction() start - "
            + String.format("[PrivilegeCode: %s, BeforeStatus: %s]", privilegeCode, beforeStatus));
        Iterable<SysWorkFlowRule> iterable = sysWorkFlowRuleRepository
            .findAll(SysWorkFlowRulePredicate.findByPrivilegeCodeAndBeforeStatus(privilegeCode, beforeStatus));
        ActionDTO actionDTO = new ActionDTO();
        iterable.forEach(rule -> {
            WorkFlowAction workFlowAction = WorkFlowAction.getByCode(rule.getAction().getCodeValue());
            switch (workFlowAction) {
                case Save:
                    actionDTO.setSaveable(true);
                    break;
                case Submit:
                    actionDTO.setSubmitable(true);
                    break;
                case Approve:
                    actionDTO.setApprovable(true);
                    break;
                case Reject:
                    actionDTO.setRejectable(true);
                    break;
                case Delete:
                    actionDTO.setDeletable(true);
                    break;
                case SubmitFor2ndApprove:
                    actionDTO.setSubmitFor2ndApprovable(true);
                    break;
                case Verify:
                    actionDTO.setVerifiable(true);
                    break;
                case Reverse:
                    actionDTO.setReversible(true);
                    break;
                default:
                    break;
            }
        });
        log.info("findAction() end - " + actionDTO);
        return actionDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public ActionDTO findIntialAction(String privilegeCode) {
        log.info("findAction() start - " + String.format("[PrivilegeCode: %s]", privilegeCode));
        SysWfInitialStatus sysWfInitialStatus
            = sysWfInitialStatusRepository.findByPrivilegePrivilegeCode(privilegeCode);
        ActionDTO action = findAction(privilegeCode, sysWfInitialStatus.getStatus().getCodeValue());
        assertNotNull(sysWfInitialStatus);
        log.info("findAction() end - " + action);
        return action;
    }

}
