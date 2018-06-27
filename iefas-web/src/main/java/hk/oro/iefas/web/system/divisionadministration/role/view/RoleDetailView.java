package hk.oro.iefas.web.system.divisionadministration.role.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.security.vo.RolePrivilegeVO;
import hk.oro.iefas.domain.security.vo.RoleVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeResultVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionPrivilegeClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.RoleClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.RolePrivilegeClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class RoleDetailView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private RoleClientService roleClientService;

    @Inject
    private DivisionAdminClientService divisionAdminClientService;

    @Inject
    private RolePrivilegeClientService rolePrivilegeClientService;

    @Inject
    private DivisionPrivilegeClientService divisionPrivilegeClientService;

    @Getter
    @Setter
    private List<DivisionVO> divisionVOs;

    @Getter
    @Setter
    private SearchPrivilegeCriteriaVO criteria = new SearchPrivilegeCriteriaVO();

    @Getter
    @Setter
    private RoleVO roleVO;

    @Getter
    @Setter
    private LazyDataModel<SearchPrivilegeResultVO> privilegeDataModel;

    @Getter
    @Setter
    private List<SearchPrivilegeResultVO> selectedPrivilegeList;

    @Getter
    @Setter
    private List<SearchPrivilegeResultVO> originalPrivilegeList;

    @Getter
    @Setter
    private List<SearchPrivilegeResultVO> tempPrivilegeList;

    @Getter
    @Setter
    private List<SearchPrivilegeResultVO> rolePrivilegeList;

    @Getter
    @Setter
    private Integer roleId;

    @PostConstruct
    private void init() {
        log.info("======RoleDetailView init======");
        divisionVOs = divisionAdminClientService.getByPostId(SecurityUtils.getCurrentPostId());

        roleId = Faces.getRequestParameter("roleId", Integer.class);
        log.debug("roleId: " + roleId);
        if (roleId != null && roleId.intValue() > 0) {
            roleVO = roleClientService.findOne(roleId);
            getPrivilegeListByDivision();
            selectPrivilege();
        } else {
            roleVO = new RoleVO();
            roleVO.setDivision(new DivisionVO());
        }
    }

    public void save() {
        log.info("save() start " + this.roleVO);
        if (CommonUtils.isNotEmpty(selectedPrivilegeList)) {
            Integer roleId = roleClientService.save(roleVO);
            this.roleVO = roleClientService.findOne(roleId);
            List<RolePrivilegeVO> resultList = new ArrayList<RolePrivilegeVO>();
            resultList.addAll(addRolePrivilege());
            if (CommonUtils.isNotEmpty(rolePrivilegeList)) {
                resultList.addAll(deleteRolePrivilege());
            }
            resultList.stream().forEach(item -> {
                rolePrivilegeClientService.save(item);
            });
            getPrivilegeListByDivision();
            selectPrivilege();
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        } else {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_MUST_SELECT, MsgParamCodeConstant.PRIVILEGE));
        }
    }

    private List<RolePrivilegeVO> addRolePrivilege() {
        List<RolePrivilegeVO> resultList = new ArrayList<RolePrivilegeVO>();
        RolePrivilegeVO tempRolePrivilege = null;
        tempPrivilegeList = new ArrayList<>();
        tempPrivilegeList.addAll(selectedPrivilegeList);
        if (CommonUtils.isNotEmpty(rolePrivilegeList)) {
            for (int i = tempPrivilegeList.size() - 1; i >= 0; i--) {
                SearchPrivilegeResultVO tempVO = tempPrivilegeList.get(i);
                tempRolePrivilege = rolePrivilegeClientService
                    .findByRoleAndPrivilege(new SearchPrivilegeCriteriaVO(this.roleVO.getRoleId(), null, null, null,
                        tempVO.getPrivilege().getPrivilegeId(), CoreConstant.STATUS_INACTIVE));
                if (tempRolePrivilege != null) {
                    tempRolePrivilege.setStatus(CoreConstant.STATUS_ACTIVE);
                    resultList.add(tempRolePrivilege);
                    tempPrivilegeList.remove(i);
                } else {
                    for (int j = rolePrivilegeList.size() - 1; j >= 0; j--) {
                        SearchPrivilegeResultVO tempRoleVO = rolePrivilegeList.get(j);
                        if (tempVO.getPrivilege().getPrivilegeId().equals(tempRoleVO.getPrivilege().getPrivilegeId())) {
                            tempPrivilegeList.remove(i);
                            break;
                        }
                    }
                }
            }
            tempPrivilegeList.stream().forEach(item -> {
                resultList.add(genRolePrivilegeVO(item, CoreConstant.STATUS_ACTIVE));
            });
        } else {
            selectedPrivilegeList.stream().forEach(item -> {
                resultList.add(genRolePrivilegeVO(item, CoreConstant.STATUS_ACTIVE));
            });
        }
        return resultList;
    }

    private List<RolePrivilegeVO> deleteRolePrivilege() {
        List<RolePrivilegeVO> resultList = new ArrayList<RolePrivilegeVO>();
        tempPrivilegeList = new ArrayList<>();
        tempPrivilegeList.addAll(rolePrivilegeList);
        for (int i = tempPrivilegeList.size() - 1; i >= 0; i--) {
            SearchPrivilegeResultVO tempVO = tempPrivilegeList.get(i);
            for (int j = selectedPrivilegeList.size() - 1; j >= 0; j--) {
                SearchPrivilegeResultVO tempRoleVO = selectedPrivilegeList.get(j);
                if (tempVO.getPrivilege().getPrivilegeId().equals(tempRoleVO.getPrivilege().getPrivilegeId())) {
                    tempPrivilegeList.remove(i);
                    break;
                }
            }
        }
        tempPrivilegeList.stream().forEach(item -> {
            resultList.add(genRolePrivilegeVO(item, CoreConstant.STATUS_INACTIVE));
        });
        return resultList;
    }

    private RolePrivilegeVO genRolePrivilegeVO(SearchPrivilegeResultVO record, String status) {
        RolePrivilegeVO result = new RolePrivilegeVO();
        if (record.getRoleId() != null && record.getRoleId() != 0) {
            SearchPrivilegeCriteriaVO criteria = new SearchPrivilegeCriteriaVO();
            criteria.setPrivilegeId(record.getPrivilege().getPrivilegeId());
            criteria.setRoleId(record.getRoleId());
            result = rolePrivilegeClientService.findByRoleAndPrivilege(criteria);
        } else {
            result.setRole(roleVO);
            result.setPrivilege(record.getPrivilege());
        }
        result.setStatus(status);
        return result;
    }

    public void selectPrivilege() {
        log.info("selectPrivilege start");
        if (roleVO != null && roleVO.getRoleId() > 0) {
            SearchPrivilegeCriteriaVO criteria = new SearchPrivilegeCriteriaVO();
            criteria.setRoleId(roleVO.getRoleId());
            criteria.setStatus(CoreConstant.STATUS_ACTIVE);
            rolePrivilegeList = rolePrivilegeClientService.findByCriteria(criteria);
            selectedPrivilegeList = rolePrivilegeList;
        }
    }

    public void getPrivilegeListByDivision() {
        log.info("getPrivilegeListByDivision() start");
        criteria = new SearchPrivilegeCriteriaVO();
        criteria.setDivisionId(roleVO.getDivision().getDivisionId());
        criteria.setStatus(CoreConstant.STATUS_ACTIVE);
        originalPrivilegeList = divisionPrivilegeClientService.findByCriteria(criteria);
        log.info("getPrivilegeListByDivision() end");
    }
}
