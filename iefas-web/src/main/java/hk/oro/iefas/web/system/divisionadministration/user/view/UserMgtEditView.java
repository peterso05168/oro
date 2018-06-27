/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.user.view;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.core.util.ValidationUtils;
import hk.oro.iefas.domain.organization.vo.DelegationVO;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.domain.organization.vo.UserProfileVO;
import hk.oro.iefas.domain.security.vo.UserAccountVO;
import hk.oro.iefas.domain.security.vo.UserPwdHistVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserAccountClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserDelegationClientService;
import hk.oro.iefas.web.system.profile.service.UserProfileClientService;
import hk.oro.iefas.web.system.profile.service.UserPwdHistClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3241 $ $Date: 2018-06-21 11:11:22 +0800 (週四, 21 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Named
@ViewScoped
public class UserMgtEditView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private DivisionAdminClientService divisionAdminClientService;

    @Inject
    private PostClientService postClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private UserAccountClientService userAccountClientService;

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    private UserDelegationClientService userDelegationClientService;

    @Inject
    private UserProfileClientService userProfileClientService;

    @Inject
    private UserPwdHistClientService userPwdHistClientService;

    @Getter
    @Setter
    private UserAccountVO userAccountVO;

    @Getter
    @Setter
    private UserProfileVO userProfileVO;

    @Getter
    @Setter
    private List<DivisionVO> divisionVOs;

    @Getter
    @Setter
    private List<PostVO> posts;

    @Getter
    @Setter
    private List<PostVO> filteredPosts;

    @Getter
    @Setter
    private Integer selectedDivisionId;

    @Getter
    @Setter
    private Integer selectedPostId;

    @Getter
    @Setter
    private String newPassword;

    @Getter
    @Setter
    private String confirmPassowrd;

    @Getter
    @Setter
    private Integer userAcId;

    @Getter
    @Setter
    private List<DelegationVO> delegations;

    @Getter
    @Setter
    private DelegationVO delegation;

    @Getter
    @Setter
    private Integer delegateToId;

    @Getter
    @Setter
    private Integer divisionId;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private List<PostVO> divPosts;

    @PostConstruct
    private void init() {
        log.info("======UserMgtView init======");
        initDropDown();
        userAcId = Faces.getRequestParameter("userAcId", Integer.class);
        log.debug("UserAcId: " + userAcId);
        loadDetail();
    }

    private void loadDetail() {
        if (userAcId != null) {
            userAccountVO = userAccountClientService.findOne(userAcId);
            userProfileVO = userAccountVO.getUserProfile();

            selectedDivisionId = userProfileVO.getDivision().getDivisionId();
            selectedPostId = userProfileVO.getPost().getPostId();
            filterPost();
            initDelegations();
        } else {
            userAccountVO = new UserAccountVO();
            userProfileVO = new UserProfileVO();
        }
    }

    private void initDelegations() {
        log.info("initDelegations() start");
        delegations = userDelegationClientService.findByDelegateFrom(selectedPostId);
        log.info("initDelegations() end");
    }

    private void initDropDown() {
        divisionVOs = divisionAdminClientService.getByPostId(SecurityUtils.getCurrentPostId());
        posts = postClientService.findByDivisionAdmin(SecurityUtils.getCurrentPostId());
    }

    public void filterPost() {
        log.info("filterPost() start");
        if (CommonUtils.isNotEmpty(posts)) {
            filteredPosts = this.posts.stream()
                .filter(
                    post -> (post.getDivision().getDivisionId().equals(this.selectedDivisionId) && !post.getAssigned())
                        || (this.userProfileVO.getPost() != null
                            && post.getPostId().equals(this.userProfileVO.getPost().getPostId())))
                .collect(Collectors.toList());
        }
        log.info("filterPost() end");
    }

    public void save() {
        log.info("save() start");

        if (userAccountVO.getUserAcId() == null) {
            createUser();
        } else {
            updateUser();
        }
        if (CommonUtils.isNotBlank(newPassword)) {
            userPwdHistClientService.saveUserPwdHist(new UserPwdHistVO(null, this.userAccountVO.getUserAcId(),
                this.userAccountVO.getPassword(), appResourceUtils.getBusinessDate(), CoreConstant.STATUS_ACTIVE));
        }

        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        log.info("save() end");
    }

    private void updateUser() {
        log.info("updateUser() start");
        if (CommonUtils.isNotBlank(newPassword)) {
            userAccountVO.setPassword(bCryptPasswordEncoder.encode(newPassword));
        }
        setDetail();

        userAcId = userAccountClientService.updateUserDetail(userAccountVO);
        loadDetail();
        log.info("updateUser() end");
    }

    private void createUser() {
        log.info("createUser() start");
        userAccountVO.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userAccountVO.setExpiryDate(appResourceUtils.getBusinessDate());

        setDetail();

        userAcId = userAccountClientService.insertUserDetail(userAccountVO);
        loadDetail();
        log.info("createUser() end");
    }

    private void setDetail() {
        Optional<PostVO> postOptional
            = posts.parallelStream().filter(post -> post.getPostId().equals(selectedPostId)).findFirst();
        if (postOptional.isPresent()) {
            userProfileVO.setPost(postOptional.get());
        }

        Optional<DivisionVO> divisionOptional = divisionVOs.parallelStream()
            .filter(division -> division.getDivisionId().equals(selectedDivisionId)).findFirst();
        if (divisionOptional.isPresent()) {
            userProfileVO.setDivision(divisionOptional.get());
        }
        userAccountVO.setUserProfile(userProfileVO);
    }

    public void initDelegationDialog() {
        log.info("initDelegationDialog() start");
        if (delegation != null) {
            PostVO delegateTo = delegation.getDelegateTo();
            this.delegateToId = delegateTo.getPostId();
            this.divisionId = delegateTo.getDivision().getDivisionId();
            initUserName();
            initPostForDelegation();
        } else {
            delegation = new DelegationVO();
        }
        log.info("initDelegationDialog() end");
    }

    public void initUserName() {
        log.info("initUserName() start");
        userName = userProfileClientService.getUserNameByPostId(delegateToId);
        log.info("initUserName() end");
    }

    public void initPostForDelegation() {
        log.info("initPost() start");
        divPosts = postClientService.findByDivisionId(divisionId);
        log.info("initPost() end");
    }

    public void saveDelegation() {
        log.info("saveDelegation() start");
        boolean validateDatePeriod = ValidationUtils.validateDatePeriod(this.delegation.getEffectiveStartDate(),
            this.delegation.getEffectiveEndDate());
        if (!validateDatePeriod) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_MUST_BEFORE,
                MsgParamCodeConstant.EFFECTIVE_START_DATE, MsgParamCodeConstant.EFFECTIVE_END_DATE));
        } else {
            this.delegation.setDelegateFrom(this.selectedPostId);
            this.delegation.setStatus(CoreConstant.STATUS_ACTIVE);
            Optional<PostVO> optional
                = this.posts.parallelStream().filter(post -> post.getPostId().equals(this.delegateToId)).findFirst();
            PostVO postVO = optional.get();
            if (postVO != null) {
                this.delegation.setDelegateTo(postVO);
            }

            userDelegationClientService.saveDelegation(this.delegation);
            initDelegations();
            hideComponent("delegationDialog");
        }

        log.info("saveDelegation() end");
    }

    public void handleDialogClose() {
        log.info("handleDialogClose() start");
        delegation = null;
        delegateToId = null;
        divisionId = null;
        userName = null;
        log.info("handleDialogClose() end");
    }

    public void deleteDelegation() {
        log.info("deleteDelegation() start");

        if (this.delegation.getEffectiveStartDate().after(appResourceUtils.getBusinessDate())
            || this.delegation.getEffectiveEndDate().before(appResourceUtils.getBusinessDate())) {
            this.delegation.setStatus(CoreConstant.STATUS_INACTIVE);
            userDelegationClientService.saveDelegation(this.delegation);
            initDelegations();
        } else {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_CANNOT_DELETE, MsgParamCodeConstant.DELEGATION));
        }
        this.delegation = null;
        log.info("deleteDelegation() end");
    }

    public void unlock() {
        log.info("unlock() start");
        this.userAccountVO.setUnlockTime(null);
        this.userAccountVO.setLockedInd(false);
        userAccountClientService.save(this.userAccountVO);
        log.info("unlock() end");
    }

}
