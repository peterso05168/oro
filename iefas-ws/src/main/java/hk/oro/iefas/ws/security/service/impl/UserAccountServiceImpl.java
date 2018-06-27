/**
 * 
 */
package hk.oro.iefas.ws.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.entity.UserProfile;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.security.dto.SearchUserCriteriaDTO;
import hk.oro.iefas.domain.security.dto.SearchUserResultDTO;
import hk.oro.iefas.domain.security.dto.UserAccountDTO;
import hk.oro.iefas.domain.security.dto.UserDetailDTO;
import hk.oro.iefas.domain.security.entity.UserAccount;
import hk.oro.iefas.ws.organization.repository.UserProfileRepository;
import hk.oro.iefas.ws.security.repository.PrivilegeRepository;
import hk.oro.iefas.ws.security.repository.UserAccountRepository;
import hk.oro.iefas.ws.security.repository.assembler.UserAccountDTOAssembler;
import hk.oro.iefas.ws.security.repository.predicate.UserAccountPredicate;
import hk.oro.iefas.ws.security.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Transactional(readOnly = true)
    @Override
    public UserAccountDTO findByLoginName(String loginName) {
        log.info("findByLoginName() start - Login Name: " + loginName);
        UserAccount userAccount = userAccountRepository.findOne(UserAccountPredicate.findByLoginName(loginName));
        UserAccountDTO userAccountDTO = UserAccountDTOAssembler.toDTO(userAccount);
        log.info("findByLoginName() end - " + userAccountDTO);
        return userAccountDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetailDTO findUserDetail(String loginName) {
        log.info("findUserDetail() start - Login Name: " + loginName);
        UserDetailDTO userDetailDTO = userAccountRepository.findOne(UserAccountDTOAssembler.toDetailDTO(),
            UserAccountPredicate.findByLoginName(loginName));
        List<PrivilegeDTO> privileges = privilegeRepository.findByPostId(userDetailDTO.getPostId());
        userDetailDTO.setPrivileges(privileges);
        log.info("findUserDetail() end - " + userDetailDTO);
        return userDetailDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer save(UserAccountDTO userAccountDTO) {
        log.info("save() start - " + userAccountDTO);
        UserAccount userAccount = DataUtils.copyProperties(userAccountDTO, UserAccount.class);
        userAccount = userAccountRepository.save(userAccount);
        log.info("save() end - User Id: " + userAccount.getUserAcId());
        return userAccount.getUserAcId();
    }

    @Transactional(readOnly = true)
    @Override
    public void clearLock(String loginName) {
        log.info("clearLock() start - Login Name: " + loginName);
        UserAccount userAccount = userAccountRepository.findOne(UserAccountPredicate.findByLoginName(loginName));
        userAccount.setLockedInd(false);
        userAccount.setUnlockTime(null);
        userAccountRepository.save(userAccount);
        log.info("clearLock() end");
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SearchUserResultDTO> searchUserList(SearchDTO<SearchUserCriteriaDTO> searchDTO) {
        log.info("searchUserList() start - " + searchDTO);
        Pageable pageable = searchDTO.getPage().toPageable();
        Page<SearchUserResultDTO> page = userAccountRepository.findAll(UserAccountDTOAssembler.toResultDTO(),
            UserAccountPredicate.searchUserList(searchDTO.getCriteria()), pageable);
        log.info("searchUserList() end - " + page);
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer insertUserDetail(UserAccountDTO userAccountDTO) {
        log.info("insertUserDetail() start - " + userAccountDTO);
        UserAccount userAccount = DataUtils.copyProperties(userAccountDTO, UserAccount.class);
        UserProfile userProfile = userAccount.getUserProfile();

        userAccount.setLoginName(userProfile.getEmailAddress().split("@")[0]);
        userAccount.setFailCount(0);
        userAccount.setLockedInd(false);
        userAccount.setStatus(CoreConstant.STATUS_ACTIVE);
        userAccount = userAccountRepository.save(userAccount);

        Integer userAcId = userAccount.getUserAcId();
        userProfile.setUserAcId(userAcId);
        userProfile.setStatus(CoreConstant.STATUS_ACTIVE);
        userProfileRepository.save(userProfile);

        log.info("insertUserDetail() end - ");
        return userAcId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateUserDetail(UserAccountDTO userAccountDTO) {
        log.info("updateUserDetail() start - " + userAccountDTO);
        UserAccount userAccount = DataUtils.copyProperties(userAccountDTO, UserAccount.class);
        UserProfile userProfile = userAccount.getUserProfile();
        userAccountRepository.save(userAccount);
        userProfileRepository.save(userProfile);

        log.info("updateUserDetail() end - ");
        return userAccount.getUserAcId();
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccountDTO findOne(Integer id) {
        log.info("findUserDetail() start - Id: " + id);
        UserAccount userAccount = userAccountRepository.findOne(id);
        UserAccountDTO userAccountDTO = UserAccountDTOAssembler.toDTO(userAccount);
        log.info("findUserDetail() end - " + userAccountDTO);
        return userAccountDTO;
    }

}
