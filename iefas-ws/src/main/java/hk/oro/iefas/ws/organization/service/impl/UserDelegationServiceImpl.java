/**
 * 
 */
package hk.oro.iefas.ws.organization.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.DelegatedDTO;
import hk.oro.iefas.domain.organization.dto.DelegationDTO;
import hk.oro.iefas.domain.organization.entity.QUserDelegation;
import hk.oro.iefas.domain.organization.entity.QUserProfile;
import hk.oro.iefas.domain.organization.entity.UserDelegation;
import hk.oro.iefas.ws.organization.repository.UserDelegationRepository;
import hk.oro.iefas.ws.organization.repository.assembler.DelegationDTOAssembler;
import hk.oro.iefas.ws.organization.repository.predicate.UserDelegationPredicate;
import hk.oro.iefas.ws.organization.service.UserDelegationService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class UserDelegationServiceImpl implements UserDelegationService {

    @Autowired
    private UserDelegationRepository userDelegationRepository;

    @Transactional(readOnly = true)
    @Override
    public List<DelegatedDTO> findByDelegateTo(Integer postId, Date currentDate) {
        log.info("findByDelegateTo() start - Post Id: " + postId);
        List<DelegatedDTO> result = userDelegationRepository.findByDelegateTo(postId, currentDate);
        log.info("findByDelegateTo() end - " + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DelegationDTO> findByDelegateFrom(Integer postId) {
        log.info("findByDelegateFrom() start - Post Id: " + postId);
        List<Tuple> tupleList = userDelegationRepository.findAll(UserDelegationPredicate.findByDelegateFrom(postId),
            QUserDelegation.userDelegation, QUserProfile.userProfile);
        List<DelegationDTO> result = DelegationDTOAssembler.toDTOList(tupleList);
        log.info("findByDelegateFrom() end - " + result);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveDelegation(DelegationDTO delegationDTO) {
        log.info("saveDelegation() start - " + delegationDTO);
        UserDelegation userDelegation = DataUtils.copyProperties(delegationDTO, UserDelegation.class);
        userDelegation = userDelegationRepository.save(userDelegation);
        log.info("saveDelegation() end - " + userDelegation);
        return userDelegation.getUserDelegationId();
    }

}
