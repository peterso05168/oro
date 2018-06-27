/**
 * 
 */
package hk.oro.iefas.web.ledger.voucherhandling.common.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.vo.SysWfInitialStatusVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.SysWfInitialStatusClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2831 $ $Date: 2018-06-02 14:14:30 +0800 (週六, 02 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class SysWfInitialStatusClientServiceImpl extends BaseClientService implements SysWfInitialStatusClientService {

    @Override
    public SysWfInitialStatusVO findByPrivilegeCode(String privilegeCode) {
        log.info("findByPrivilegeId() start - PrivilegeCode: " + privilegeCode);
        ResponseEntity<SysWfInitialStatusVO> postForEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SYS_WF_INITIAL_STATUS_FINDBY_PRIVILEGE_CODE, privilegeCode,
                SysWfInitialStatusVO.class);
        SysWfInitialStatusVO sysWfInitialStatusVO = postForEntity.getBody();
        log.info("findByPrivilegeId() end - " + sysWfInitialStatusVO);
        return sysWfInitialStatusVO;
    }

}
