package hk.oro.iefas.web.system.systemadministration.divisionadministrator.view;

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
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.organization.vo.DivisionAdminVO;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.domain.organization.vo.SearchDivisionAdminResultVO;
import hk.oro.iefas.domain.organization.vo.SearchDivisionCriteriaVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import hk.oro.iefas.web.system.profile.service.UserProfileClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class DivisionAdministratorSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private DivisionAdminClientService divisionAdminClientService;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private PostClientService postClientService;

    @Inject
    private UserProfileClientService userProfileClientService;

    @Getter
    @Setter
    private List<DivisionVO> divisionVOs;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private List<PostVO> postVOs;

    @Getter
    @Setter
    private SearchDivisionCriteriaVO criteria = new SearchDivisionCriteriaVO();

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private Integer divisionAdminId;

    @Getter
    @Setter
    private Integer divisionId;

    @Getter
    @Setter
    private Integer postId;

    @Getter
    @Setter
    private DivisionAdminVO divisionAdminVO;

    @Getter
    @Setter
    private SearchDivisionAdminResultVO divisionAdminResultVO;

    @Getter
    @Setter
    private LazyDataModel<SearchDivisionAdminResultVO> lazyDataModel;

    @PostConstruct
    public void init() {
        log.info("init() start ");
        statusVOs = commonConstantClientService.searchStatusList();
        divisionVOs = divisionAdminClientService.getByPostId(SecurityUtils.getCurrentPostId());
        divisionAdminVO = new DivisionAdminVO();
        divisionAdminVO.setDivision(new DivisionVO());
        divisionAdminVO.setPost(new PostVO());
        log.info("init() end ");
    }

    public void search() {
        log.info("search() start ");
        lazyDataModel = new SimpleLazyDataModel<SearchDivisionAdminResultVO, SearchDivisionCriteriaVO>(
            RequestUriConstant.CLIENT_URI_DIVISION_ADMIN_BY_DIVISION, criteria);
        log.info("search() end ");
    }

    public void reset() {
        log.info("reset() start");
        criteria = new SearchDivisionCriteriaVO();
        userName = null;
        lazyDataModel = null;
        log.info("reset() end");
    }

    public void deleteDivisionAdmin() {
        log.info("deleteDivisionAdmin() start");
        if (divisionAdminId != null) {
            DivisionAdminVO divisionAdminVO = divisionAdminClientService.findOne(divisionAdminId);
            if (divisionAdminVO.getStatus().equals(CoreConstant.STATUS_ACTIVE)) {
                divisionAdminVO.setStatus(CoreConstant.STATUS_DELETE);
                divisionAdminClientService.save(divisionAdminVO);
            }
        }
        log.info("deleteDivisionAdmin() end");
    }

    public void dialogHandleOpen() {
        log.info("dialogHandleOpen() start");
        if (divisionAdminId != null) {
            divisionAdminVO = divisionAdminClientService.findOne(divisionAdminId);
            divisionId = divisionAdminVO.getDivision().getDivisionId();
            getPostByDivision();
            postId = divisionAdminVO.getPost().getPostId();
            getUserNameByPost();
        } else {
            divisionAdminVO = new DivisionAdminVO();
            divisionAdminVO.setDivision(new DivisionVO());
            divisionAdminVO.setPost(new PostVO());
        }
        showComponent("createDialog");
        log.info("dialogHandleOpen() end");
    }

    public void dialogHandleClose() {
        log.info("dialogHandleClose() start");
        divisionAdminId = null;
        divisionAdminVO = null;
        divisionId = null;
        postId = null;
        userName = null;
        log.info("dialogHandleClose() end");
    }

    public void save() {
        log.info("save() start");
        DivisionAdminVO record
            = divisionAdminClientService.findByDivisionAndPost(new SearchDivisionCriteriaVO(divisionId, null, postId));
        if (record != null) {
            if (record.getStatus().equals(CoreConstant.STATUS_ACTIVE)) {
                Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_DUPLICATE_VALUE,
                    MsgParamCodeConstant.DIVISION_ADMIN));
            } else {
                record.setStatus(CoreConstant.STATUS_ACTIVE);
                divisionAdminClientService.save(record);
                hideComponent("createDialog");
            }
        } else {
            divisionVOs.stream().forEach(item -> {
                if (item.getDivisionId().equals(divisionId)) {
                    divisionAdminVO.setDivision(item);
                }
            });
            postVOs.stream().forEach(item -> {
                if (item.getPostId().equals(postId)) {
                    divisionAdminVO.setPost(item);
                }
            });
            divisionAdminVO.setStatus(CoreConstant.STATUS_ACTIVE);
            divisionAdminClientService.save(divisionAdminVO);
            hideComponent("createDialog");
        }
        log.info("save() end");
    }

    public void getPostByDivision() {
        log.info("getPostByDivision() start ");
        postId = 0;
        postVOs = postClientService.findByDivisionId(divisionId);
        getUserNameByPost();
        log.info("getPostByDivision() end ");
    }

    public void getUserNameByPost() {
        log.info("getUserNameByPost() start ");
        userName = userProfileClientService.getUserNameByPostId(postId);
        log.info("getUserNameByPost() end ");
    }
}
