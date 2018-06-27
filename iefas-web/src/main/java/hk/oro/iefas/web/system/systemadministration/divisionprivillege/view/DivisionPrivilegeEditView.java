package hk.oro.iefas.web.system.systemadministration.divisionprivillege.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.security.vo.DivisionPrivilegeVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeResultVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionPrivilegeClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.PrivilegeClientService;
import hk.oro.iefas.web.system.systemadministration.divisionprivillege.service.DivisionClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class DivisionPrivilegeEditView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Autowired
    private DivisionPrivilegeClientService divisionPrivilegeClientService;

    @Autowired
    private DivisionClientService divisionClientService;

    @Autowired
    private PrivilegeClientService privilegeClientService;

    @Getter
    @Setter
    private DivisionVO divisionVO;

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
    private List<SearchPrivilegeResultVO> divisionPrivilegeList;

    @PostConstruct
    private void init() {
        log.info("=====DivisionPrivilegeEditView init======");
        Integer divisionId = Faces.getRequestParameter("divisionId", Integer.class);
        log.debug("divisionId: " + divisionId);
        divisionVO = divisionClientService.findOne(divisionId);
        originalPrivilegeList = privilegeClientService.findAll();
        divisionPrivilegeList = divisionPrivilegeClientService.findByCriteria(
            new SearchPrivilegeCriteriaVO(null, null, divisionId, null, null, CoreConstant.STATUS_ACTIVE));
        selectedPrivilegeList = divisionPrivilegeList;

    }

    public void save() {
        log.info("save() start " + this.divisionVO);
        if (CommonUtils.isNotEmpty(selectedPrivilegeList)) {
            List<DivisionPrivilegeVO> resultList = new ArrayList<DivisionPrivilegeVO>();
            resultList.addAll(addDivisionPrivilege());
            if (CommonUtils.isNotEmpty(divisionPrivilegeList)) {
                resultList.addAll(deleteDivisionPrivilege());
            }
            resultList.stream().forEach(item -> {
                divisionPrivilegeClientService.save(item);
            });
            originalPrivilegeList = privilegeClientService.findAll();
            divisionPrivilegeList = divisionPrivilegeClientService.findByCriteria(new SearchPrivilegeCriteriaVO(null,
                null, divisionVO.getDivisionId(), null, null, CoreConstant.STATUS_ACTIVE));
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        } else {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_MUST_SELECT, MsgParamCodeConstant.PRIVILEGE));
        }
    }

    private List<DivisionPrivilegeVO> addDivisionPrivilege() {
        List<DivisionPrivilegeVO> resultList = new ArrayList<DivisionPrivilegeVO>();
        DivisionPrivilegeVO tempDivisionPrivilege = null;
        tempPrivilegeList = new ArrayList<>();
        tempPrivilegeList.addAll(selectedPrivilegeList);
        if (CommonUtils.isNotEmpty(divisionPrivilegeList)) {
            for (int i = tempPrivilegeList.size() - 1; i >= 0; i--) {
                SearchPrivilegeResultVO tempVO = tempPrivilegeList.get(i);
                tempDivisionPrivilege = divisionPrivilegeClientService
                    .findByDivisionAndPrivilege(new SearchPrivilegeCriteriaVO(null, null, divisionVO.getDivisionId(),
                        null, tempVO.getPrivilege().getPrivilegeId(), CoreConstant.STATUS_INACTIVE));
                if (tempDivisionPrivilege != null) {
                    tempDivisionPrivilege.setStatus(CoreConstant.STATUS_ACTIVE);
                    resultList.add(tempDivisionPrivilege);
                    tempPrivilegeList.remove(i);
                } else {
                    for (int j = divisionPrivilegeList.size() - 1; j >= 0; j--) {
                        SearchPrivilegeResultVO tempRoleVO = divisionPrivilegeList.get(j);
                        if (tempVO.getPrivilege().getPrivilegeId().equals(tempRoleVO.getPrivilege().getPrivilegeId())) {
                            tempPrivilegeList.remove(i);
                            break;
                        }
                    }
                }
            }
            tempPrivilegeList.stream().forEach(item -> {
                resultList.add(genDivisionPrivilegeVO(item, CoreConstant.STATUS_ACTIVE));
            });
        } else {
            selectedPrivilegeList.stream().forEach(item -> {
                resultList.add(genDivisionPrivilegeVO(item, CoreConstant.STATUS_ACTIVE));
            });
        }
        return resultList;
    }

    private List<DivisionPrivilegeVO> deleteDivisionPrivilege() {
        List<DivisionPrivilegeVO> resultList = new ArrayList<DivisionPrivilegeVO>();
        tempPrivilegeList = new ArrayList<>();
        tempPrivilegeList.addAll(divisionPrivilegeList);
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
            resultList.add(genDivisionPrivilegeVO(item, CoreConstant.STATUS_INACTIVE));
        });
        return resultList;
    }

    private DivisionPrivilegeVO genDivisionPrivilegeVO(SearchPrivilegeResultVO record, String status) {
        DivisionPrivilegeVO result = new DivisionPrivilegeVO();
        if (record.getDivisionId() != null && record.getDivisionId() != 0) {
            SearchPrivilegeCriteriaVO criteria = new SearchPrivilegeCriteriaVO();
            criteria.setPrivilegeId(record.getPrivilege().getPrivilegeId());
            criteria.setDivisionId(record.getDivisionId());
            result = divisionPrivilegeClientService.findByDivisionAndPrivilege(criteria);
        } else {
            result.setDivision(divisionVO);
            result.setPrivilege(record.getPrivilege());
        }
        result.setStatus(status);
        return result;
    }
}
