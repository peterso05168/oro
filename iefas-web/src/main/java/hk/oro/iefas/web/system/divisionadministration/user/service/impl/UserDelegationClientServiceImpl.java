/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.vo.DelegatedVO;
import hk.oro.iefas.domain.organization.vo.DelegationInfoSearchVO;
import hk.oro.iefas.domain.organization.vo.DelegationVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserDelegationClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class UserDelegationClientServiceImpl extends BaseClientService implements UserDelegationClientService {

    @Inject
    private AppResourceUtils appResourceUtils;

    @Override
    public List<DelegatedVO> findByDelegateTo(Integer postId) {
        log.info("findByDelegateTo() start - Post Id: " + postId);
        HttpEntity<DelegationInfoSearchVO> entity
            = new HttpEntity<>(new DelegationInfoSearchVO(postId, appResourceUtils.getBusinessDate()));
        ResponseEntity<List<DelegatedVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_DELEGATION_FINDBY_DELEGATION_TO, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<DelegatedVO>>() {});
        List<DelegatedVO> body = responseEntity.getBody();
        log.info("findByDelegateTo() end - " + body);
        return body;
    }

    @Override
    public List<DelegationVO> findByDelegateFrom(Integer postId) {
        log.info("findByDelegateFrom() start - Post Id: " + postId);
        ResponseEntity<List<DelegationVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_DELEGATION_FINDBY_DELEGATION_FROM, HttpMethod.POST,
                new HttpEntity<>(postId), new ParameterizedTypeReference<List<DelegationVO>>() {});
        List<DelegationVO> body = responseEntity.getBody();
        log.info("findByDelegateFrom() end - " + body);
        return body;
    }

    @Override
    public Integer saveDelegation(DelegationVO delegationVO) {
        log.info("saveDelegation() start - " + delegationVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_DELEGATION_SAVE, delegationVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveDelegation() end - Delegation Id: " + body);
        return body;
    }
}
