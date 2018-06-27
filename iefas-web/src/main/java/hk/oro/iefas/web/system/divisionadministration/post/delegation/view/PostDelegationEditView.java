package hk.oro.iefas.web.system.divisionadministration.post.delegation.view;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.core.util.ValidationUtils;
import hk.oro.iefas.domain.organization.vo.DelegationVO;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserDelegationClientService;
import hk.oro.iefas.web.system.profile.service.UserProfileClientService;
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
public class PostDelegationEditView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private PostClientService postClientService;

    @Inject
    private DivisionAdminClientService divisionAdminClientService;

    @Inject
    private UserProfileClientService userProfileClientService;

    @Inject
    private UserDelegationClientService userDelegationClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private Integer postId;

    @Getter
    @Setter
    private PostVO postVO;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private List<DelegationVO> delegations;

    @Getter
    @Setter
    private List<DivisionVO> divisionVOs;

    @Getter
    @Setter
    private DelegationVO delegation;

    @Getter
    @Setter
    private Integer selectedDivisionId;

    @Getter
    @Setter
    private List<PostVO> postVOs;

    @Getter
    @Setter
    private Integer delegateToId;

    @Getter
    @Setter
    private String dialogUserName;

    @PostConstruct
    private void init() {
        log.info("======PostDelegationEditView init======");
        postId = Faces.getRequestParameter("postId", Integer.class);
        if (postId != null && postId != 0) {
            initPostVO();
            initUserName();
        }
        initDivisions();
        initDelegations();
        log.info("init() end");
    }

    public void initPostVO() {
        log.info("initPost() start");
        postVO = postClientService.findOne(postId);
        log.info("initPost() end");
    }

    public void initPostList() {
        log.info("initPostList() start");
        postVOs = postClientService.findByDivisionId(selectedDivisionId);
        log.info("initPostList() end");
    }

    private void initDivisions() {
        log.info("initDivisions() start");
        divisionVOs = divisionAdminClientService.getByPostId(SecurityUtils.getCurrentPostId());
        log.info("initDivisions() end");
    }

    private void initDelegations() {
        log.info("initDelegations() start");
        delegations = userDelegationClientService.findByDelegateFrom(SecurityUtils.getCurrentPostId());
        log.info("initDelegations() end");
    }

    public void initUserName() {
        log.info("initUserName() start");
        if (postId != null && postId != 0) {
            userName = userProfileClientService.getUserNameByPostId(postId);
        }
        if (delegateToId != null && delegateToId != 0) {
            dialogUserName = userProfileClientService.getUserNameByPostId(delegateToId);
        }
        log.info("initUserName() end");
    }

    public void initDelegationDialog() {
        log.info("initDelegationDialog() start");
        if (delegation != null) {
            PostVO delegateTo = delegation.getDelegateTo();
            this.delegateToId = delegateTo.getPostId();
            this.selectedDivisionId = delegateTo.getDivision().getDivisionId();
            initPostList();
            initUserName();
        } else {
            delegation = new DelegationVO();
        }
        log.info("initDelegationDialog() end");
    }

    public void deleteDelegation() {
        log.info("deleteDelegation() start");
        if (this.delegation.getUserDelegationId() == null || this.delegation.getUserDelegationId() == 0) {
            this.delegations.remove(this.delegation);
        } else {
            if (this.delegation.getEffectiveStartDate().after(appResourceUtils.getBusinessDate())
                || this.delegation.getEffectiveEndDate().before(appResourceUtils.getBusinessDate())) {
                this.delegation.setStatus(CoreConstant.STATUS_INACTIVE);
                userDelegationClientService.saveDelegation(this.delegation);
                initDelegations();
            } else {
                Messages.addError(null, "Cannot delete delegation.");
            }
        }
        log.info("deleteDelegation() end");
    }

    public void save() {
        log.info("save() start");
        delegations.stream().forEach(item -> {
            if (item.getUserDelegationId() == null || item.getUserDelegationId() == 0) {
                userDelegationClientService.saveDelegation(item);
            }
        });
        initPostVO();
        initUserName();
        initDelegations();
        log.info("save() end");
    }

    public void saveDelegation() {
        log.info("saveDelegation() start");
        boolean validateDatePeriod = ValidationUtils.validateDatePeriod(this.delegation.getEffectiveStartDate(),
            this.delegation.getEffectiveEndDate());
        if (!validateDatePeriod) {
            Messages.addError(null, "Invalid Period.");
        } else {
            this.delegation.setDelegateFrom(SecurityUtils.getCurrentPostId());
            this.delegation.setStatus(CoreConstant.STATUS_ACTIVE);
            this.delegation.setEngName(dialogUserName);
            Optional<PostVO> optional
                = this.postVOs.parallelStream().filter(post -> post.getPostId().equals(this.delegateToId)).findFirst();
            PostVO postVO = optional.get();
            if (postVO != null) {
                this.delegation.setDelegateTo(postVO);
            }

            delegations.add(delegation);

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

}
