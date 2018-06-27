/**
 * 
 */
package hk.oro.iefas.ws.organization.controlller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.dto.DelegatedDTO;
import hk.oro.iefas.domain.organization.dto.DelegationDTO;
import hk.oro.iefas.domain.organization.dto.DelegationInfoSearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.organization.service.UserDelegationService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_DELEGATION)
public class UserDelegationController {

    @Autowired
    private UserDelegationService userDelegationService;

    @PostMapping(value = RequestUriConstant.URI_DELEGATION_FINDBY_DELEGATION_TO)
    public List<DelegatedDTO> findByDelegateTo(@RequestBody DelegationInfoSearchDTO delegationInfoSearchDTO) {
        log.info("findByDelegateTo() start - " + delegationInfoSearchDTO);
        List<DelegatedDTO> result = userDelegationService.findByDelegateTo(delegationInfoSearchDTO.getPostId(),
            delegationInfoSearchDTO.getCurrentDate());
        log.info("findByDelegateTo() end - " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_DELEGATION_FINDBY_DELEGATION_FROM)
    public List<DelegationDTO> findByDelegateFrom(@RequestBody Integer postId) {
        log.info("findByDelegateTo() start - postId: " + postId);
        List<DelegationDTO> result = userDelegationService.findByDelegateFrom(postId);
        log.info("findByDelegateTo() end - " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_ORGANIZATION, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_DELEGATION_SAVE)
    public Integer saveDelegation(@RequestBody DelegationDTO delegationDTO) {
        log.info("findByDelegateFrom() start - " + delegationDTO);
        Integer delegationId = userDelegationService.saveDelegation(delegationDTO);
        log.info("findByDelegateFrom() end - delegationId: " + delegationId);
        return delegationId;
    }

}
