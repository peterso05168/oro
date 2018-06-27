/**
 * 
 */
package hk.oro.iefas.web.system.profile.view;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.core.util.ValidationUtils;
import hk.oro.iefas.domain.organization.vo.DelegationVO;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.domain.security.vo.RoleSummaryVO;
import hk.oro.iefas.domain.security.vo.UserAccountVO;
import hk.oro.iefas.domain.security.vo.UserPwdHistVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.RoleClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserAccountClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserDelegationClientService;
import hk.oro.iefas.web.system.profile.service.UserProfileClientService;
import hk.oro.iefas.web.system.profile.service.UserPwdHistClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2500 $ $Date: 2018-05-21 10:24:29 +0800 (週一, 21 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class MyProfileView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserAccountClientService userAccountClientService;

    @Inject
    private UserProfileClientService userProfileClientService;

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    private RoleClientService roleClientService;

    @Inject
    private UserDelegationClientService userDelegationClientService;

    @Inject
    private DivisionAdminClientService divisionAdminClientService;

    @Inject
    private PostClientService postClientService;

    @Inject
    private UserPwdHistClientService userPwdHistClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private UserAccountVO userAccountVO;

    @Getter
    @Setter
    private String currentPassword;

    @Getter
    @Setter
    private String newPassword;

    @Getter
    @Setter
    private String confirmPassword;

    @Getter
    @Setter
    private List<RoleSummaryVO> roleSummaryList;

    @Getter
    @Setter
    private List<DelegationVO> delegations;

    @Getter
    @Setter
    private List<DivisionVO> divisions;

    @Getter
    @Setter
    private DelegationVO delegation;

    @Getter
    @Setter
    private Integer selectedDivisionId;

    @Getter
    @Setter
    private List<PostVO> posts;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private Integer delegateToId;

    @PostConstruct
    private void init() {
        log.info("======MyProfileView init======");
        initBasicInfo();
        initRoles();
        initDelegations();
        initDivisions();
    }

    private void initDivisions() {
        log.info("initDivisions() start");
        divisions = divisionAdminClientService.getByPostId(SecurityUtils.getCurrentPostId());
        log.info("initDivisions() end");
    }

    private void initDelegations() {
        log.info("initDelegations() start");
        delegations = userDelegationClientService.findByDelegateFrom(SecurityUtils.getCurrentPostId());
        log.info("initDelegations() end");
    }

    private void initRoles() {
        log.info("initRoles() start");
        roleSummaryList = roleClientService.findByPostId(SecurityUtils.getCurrentPostId());
        log.info("initRoles() end");
    }

    private void initBasicInfo() {
        log.info("initBasicInfo() start");
        userAccountVO = userAccountClientService.findByLoginName(SecurityUtils.getUserName());
        log.info("initBasicInfo() end");
    }

    public void initPost() {
        log.info("initPost() start");
        posts = postClientService.findByDivisionId(selectedDivisionId);
        log.info("initPost() end");
    }

    public void saveUserDetail() {
        log.info("saveUserDetail() start");
        userProfileClientService.saveUserProfile(userAccountVO.getUserProfile());
        initBasicInfo();
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        log.info("saveUserDetail() end");
    }

    public void savePassword() {
        log.info("savePassword() start");
        this.userAccountVO.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userAccountClientService.save(this.userAccountVO);
        userPwdHistClientService.saveUserPwdHist(new UserPwdHistVO(null, this.userAccountVO.getUserAcId(),
            this.userAccountVO.getPassword(), appResourceUtils.getBusinessDate(), CoreConstant.STATUS_ACTIVE));
        initBasicInfo();
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        log.info("savePassword() end");
    }

    public void initDelegationDialog() {
        log.info("initDelegationDialog() start");
        if (delegation != null) {
            PostVO delegateTo = delegation.getDelegateTo();
            this.delegateToId = delegateTo.getPostId();
            this.selectedDivisionId = delegateTo.getDivision().getDivisionId();
            initPost();
            initUserName();
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

    public void saveDelegation() {
        log.info("saveDelegation() start");
        boolean validateDatePeriod = ValidationUtils.validateDatePeriod(this.delegation.getEffectiveStartDate(),
            this.delegation.getEffectiveEndDate());
        if (!validateDatePeriod) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_MUST_BEFORE,
                MsgParamCodeConstant.EFFECTIVE_START_DATE, MsgParamCodeConstant.EFFECTIVE_END_DATE));
        } else {
            this.delegation.setDelegateFrom(SecurityUtils.getCurrentPostId());
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
        selectedDivisionId = null;
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

}
