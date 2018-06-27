/**
 * 
 */
package hk.oro.iefas.web.ledger.voucherhandling.common.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.common.vo.ActionVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.SysWorkFlowRuleClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3143 $ $Date: 2018-06-14 18:20:23 +0800 (週四, 14 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class SysWorkFlowRuleClientServiceImpl extends BaseClientService implements SysWorkFlowRuleClientService {

    @Override
    public List<SysWorkFlowRuleVO> findByPrivilegeCode(String privilegeCode) {
        log.info("findByPrivilegeCode() start - PrivilegeCode: " + privilegeCode);
        ResponseEntity<List<SysWorkFlowRuleVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_SYS_WORKFLOW_RULE_FINDBY_PRIVILEGE_CODE, HttpMethod.POST,
                new HttpEntity<String>(privilegeCode), new ParameterizedTypeReference<List<SysWorkFlowRuleVO>>() {});
        List<SysWorkFlowRuleVO> body = responseEntity.getBody();
        log.info("findByPrivilegeCode() end - " + body);
        return body;
    }

    @Override
    public ActionVO findIntialAction(String privilegeCode) {
        log.info("findIntialAction() start - PrivilegeCode: " + privilegeCode);
        ResponseEntity<ActionVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_SYS_WORKFLOW_RULE_FIND_INIT_ACTION, privilegeCode, ActionVO.class);
        ActionVO body = responseEntity.getBody();
        log.info("findIntialAction() end - " + body);
        return body;
    }

}
