package hk.oro.iefas.web.system.divisionadministration.role.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.SearchRoleCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchRoleResultVO;
import hk.oro.iefas.domain.security.vo.RoleVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostRoleClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.RoleClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class RoleManagementView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private RoleClientService roleClientService;

    @Inject
    private DivisionAdminClientService divisionAdminClientService;

    @Inject
    private PostRoleClientService postRoleClientService;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private List<DivisionVO> divisionVOs;

    @Getter
    @Setter
    private SearchRoleCriteriaVO criteria = new SearchRoleCriteriaVO();

    @Getter
    @Setter
    private List<SearchRoleResultVO> roleResultVOList;

    @Getter
    @Setter
    private LazyDataModel<SearchRoleResultVO> roleResultDataModel;

    @Getter
    @Setter
    private Integer roleId;

    @PostConstruct
    private void init() {
        log.info("======RoleManagementView init======");
        statusVOs = commonConstantClientService.searchStatusList();
        divisionVOs = divisionAdminClientService.getByPostId(SecurityUtils.getCurrentPostId());
    }

    public void updateRoleStatus() {
        RoleVO roleVO = roleClientService.findOne(roleId);
        if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(roleVO.getStatus())) {
            if (CommonUtils
                .isEmpty(postRoleClientService.findByRole(new SearchRoleCriteriaVO(roleId, null, null, null, null)))) {
                roleVO.setStatus(CoreConstant.STATUS_INACTIVE);
                roleClientService.save(roleVO);
            } else {
                Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_BEING_ASSIGNED,
                    MsgParamCodeConstant.ROLE, MsgParamCodeConstant.POST));
            }
        } else {
            roleVO.setStatus(CoreConstant.STATUS_ACTIVE);
            roleClientService.save(roleVO);
        }
    }
}
