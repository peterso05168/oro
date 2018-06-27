/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.post.view;

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
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.domain.organization.vo.RankVO;
import hk.oro.iefas.domain.organization.vo.SearchPostCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchPostResultVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import hk.oro.iefas.web.system.divisionadministration.post.service.RankClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import hk.oro.iefas.web.system.profile.service.UserProfileClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class PostSearchView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private PostClientService postClientService;

    @Inject
    private RankClientService rankClientService;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private DivisionAdminClientService divisionAdminClientService;

    @Inject
    private UserProfileClientService userProfileClientService;

    @Getter
    @Setter
    private List<RankVO> ranks;

    @Getter
    @Setter
    private List<DivisionVO> divisions;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private SearchPostCriteriaVO criteria;

    @Getter
    @Setter
    private LazyDataModel<SearchPostResultVO> postResultDataModel;

    @Getter
    @Setter
    private Integer postId;

    @PostConstruct
    private void init() {
        log.info("======PostSearchView init======");

        ranks = rankClientService.findAll();
        statusVOs = commonConstantClientService.searchStatusList();
        divisions = divisionAdminClientService.getByPostId(SecurityUtils.getCurrentPostId());
        criteria = new SearchPostCriteriaVO();
    }

    public void changeStatus() {
        PostVO postVO = postClientService.findOne(postId);
        if (CoreConstant.STATUS_ACTIVE.equals(postVO.getStatus())) {
            if (CommonUtils.isNotBlank(userProfileClientService.getUserNameByPostId(postId))) {
                Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_BEING_ASSIGNED,
                    MsgParamCodeConstant.POST, MsgParamCodeConstant.USER));
            } else {
                postVO.setStatus(CoreConstant.STATUS_INACTIVE);
                postClientService.save(postVO);
            }
        } else {
            postVO.setStatus(CoreConstant.STATUS_ACTIVE);
            postClientService.save(postVO);
        }

    }

}
