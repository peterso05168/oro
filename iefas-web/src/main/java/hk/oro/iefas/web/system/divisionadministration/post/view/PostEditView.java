package hk.oro.iefas.web.system.divisionadministration.post.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.domain.organization.vo.RankVO;
import hk.oro.iefas.domain.organization.vo.SearchPostCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchRoleCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchRoleResultVO;
import hk.oro.iefas.domain.security.vo.PostRoleVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostRoleClientService;
import hk.oro.iefas.web.system.divisionadministration.post.service.RankClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.RoleClientService;
import hk.oro.iefas.web.system.profile.service.UserProfileClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2176 $ $Date: 2018-04-23 18:25:01 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class PostEditView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private PostRoleClientService postRoleClientService;

    @Inject
    private RoleClientService roleClientService;

    @Inject
    private UserProfileClientService userProfileClientService;

    @Inject
    private RankClientService rankClientService;

    @Inject
    private DivisionAdminClientService divisionAdminClientService;

    @Inject
    private PostClientService postClientService;

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
    private List<RankVO> rankVOs;

    @Getter
    @Setter
    private RankVO limitVO;

    @Getter
    @Setter
    private String voucherLimit;

    @Getter
    @Setter
    private String paymentLimit;

    @Getter
    @Setter
    private List<DivisionVO> divisionVOs;

    @Getter
    @Setter
    private SearchPostCriteriaVO criteria = new SearchPostCriteriaVO();

    @Getter
    @Setter
    private List<SearchRoleResultVO> roleDataList;

    @Getter
    @Setter
    private List<SearchRoleResultVO> selectedRoleList;

    @Getter
    @Setter
    private List<SearchRoleResultVO> originalRoleList;

    @Getter
    @Setter
    private List<SearchRoleResultVO> tempRoleList;

    @PostConstruct
    private void init() {
        log.info("======PostEditView init======");
        rankVOs = rankClientService.findAll();
        divisionVOs = divisionAdminClientService.getByPostId(SecurityUtils.getCurrentPostId());

        postId = Faces.getRequestParameter("postId", Integer.class);
        if (postId != null && postId != 0) {
            postVO = postClientService.findOne(postId);
            userName = userProfileClientService.getUserNameByPostId(postId);
            originalRoleList = roleClientService.findByDivisionId(postVO.getDivision().getDivisionId());
            roleDataList = postRoleClientService.findByPostId(postId);
            selectedRoleList = roleDataList;
            getRankApprovalLimit();
        } else {
            postVO = new PostVO();
            postVO.setDivision(new DivisionVO());
            postVO.setRank(new RankVO());
        }
    }

    public void save() {
        log.info("save() starts " + this.postVO);
        if (CommonUtils.isNotEmpty(selectedRoleList)) {
            Integer postId = postClientService.save(postVO);
            this.postVO = postClientService.findOne(postId);

            List<PostRoleVO> resultList = new ArrayList<PostRoleVO>();
            resultList.addAll(addPostRole());
            if (CommonUtils.isNotEmpty(roleDataList)) {
                resultList.addAll(deletePostRole());
            }
            resultList.stream().forEach(item -> {
                postRoleClientService.save(item);
            });
            originalRoleList = roleClientService.findByDivisionId(postVO.getDivision().getDivisionId());
            roleDataList = postRoleClientService.findByPostId(postId);
            selectedRoleList = roleDataList;
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        } else {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_MUST_SELECT, MsgParamCodeConstant.ROLE));
        }
    }

    public List<PostRoleVO> addPostRole() {
        List<PostRoleVO> resultList = new ArrayList<PostRoleVO>();
        PostRoleVO tempRecord = null;
        tempRoleList = new ArrayList<>();
        tempRoleList.addAll(selectedRoleList);
        if (CommonUtils.isNotEmpty(roleDataList)) {
            for (int i = tempRoleList.size() - 1; i >= 0; i--) {
                SearchRoleResultVO tempVO = tempRoleList.get(i);
                tempRecord = postRoleClientService.findByPostAndRole(new SearchRoleCriteriaVO(tempVO.getRoleId(),
                    this.postVO.getPostId(), null, null, CoreConstant.STATUS_INACTIVE));
                if (tempRecord != null) {
                    tempRecord.setStatus(CoreConstant.STATUS_ACTIVE);
                    resultList.add(tempRecord);
                    tempRoleList.remove(i);
                } else {
                    for (int j = roleDataList.size() - 1; j >= 0; j--) {
                        SearchRoleResultVO tempRoleVO = roleDataList.get(j);
                        if (tempVO.getRoleId().equals(tempRoleVO.getRoleId())) {
                            tempRoleList.remove(i);
                            break;
                        }
                    }
                }
            }
            tempRoleList.stream().forEach(item -> {
                resultList.add(genPostRoleVO(item, CoreConstant.STATUS_ACTIVE));
            });
        } else {
            selectedRoleList.stream().forEach(item -> {
                resultList.add(genPostRoleVO(item, CoreConstant.STATUS_ACTIVE));
            });
        }
        return resultList;
    }

    public List<PostRoleVO> deletePostRole() {
        List<PostRoleVO> resultList = new ArrayList<PostRoleVO>();
        tempRoleList = new ArrayList<>();
        tempRoleList.addAll(roleDataList);
        for (int i = tempRoleList.size() - 1; i >= 0; i--) {
            SearchRoleResultVO tempVO = tempRoleList.get(i);
            for (int j = selectedRoleList.size() - 1; j >= 0; j--) {
                SearchRoleResultVO tempRoleVO = selectedRoleList.get(j);
                if (tempVO.getRoleId().equals(tempRoleVO.getRoleId())) {
                    tempRoleList.remove(i);
                    break;
                }
            }
        }
        tempRoleList.stream().forEach(item -> {
            resultList.add(genPostRoleVO(item, CoreConstant.STATUS_INACTIVE));
        });
        return resultList;
    }

    private PostRoleVO genPostRoleVO(SearchRoleResultVO record, String status) {
        PostRoleVO result = new PostRoleVO();
        if (record.getPostId() != null && record.getPostId() != 0) {
            SearchRoleCriteriaVO criteria = new SearchRoleCriteriaVO();
            criteria.setPostId(record.getPostId());
            criteria.setRoleId(record.getRoleId());
            result = postRoleClientService.findByPostAndRole(criteria);
        } else {
            result.setPost(postVO);
            result.setRole(roleClientService.findOne(record.getRoleId()));
        }
        result.setStatus(status);
        return result;
    }

    public void getRoleListByDivision() {
        log.info("getRoleListByDivision() start");
        originalRoleList = roleClientService.findByDivisionId(postVO.getDivision().getDivisionId());
        log.info("getRoleListByDivision() end");
    }

    public void getRankApprovalLimit() {
        log.info("getRankApprovalLimit() start");
        limitVO = rankClientService.findOne(postVO.getRank().getRankId());
        if (limitVO != null) {
            voucherLimit = limitVO.getVoucherLimit().toString();
            paymentLimit = limitVO.getPaymentLimit().toString();
        } else {
            voucherLimit = "";
            paymentLimit = "";
        }
        log.info("getRankApprovalLimit() end");
    }
}
