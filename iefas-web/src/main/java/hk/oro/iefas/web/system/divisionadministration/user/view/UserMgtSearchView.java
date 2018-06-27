/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.user.view;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.domain.security.vo.SearchUserCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchUserResultVO;
import hk.oro.iefas.domain.security.vo.UserAccountVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserAccountClientService;
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
public class UserMgtSearchView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private DivisionAdminClientService divisionAdminClientService;

    @Inject
    private PostClientService postClientService;

    @Inject
    private UserAccountClientService userAccountClientService;

    @Getter
    @Setter
    private SearchUserCriteriaVO searchUserCriteriaVO = new SearchUserCriteriaVO();

    @Getter
    @Setter
    private List<SearchUserResultVO> searchUserResults;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private List<DivisionVO> divisionVOs;

    @Getter
    @Setter
    private List<PostVO> posts;

    @Getter
    @Setter
    private LazyDataModel<SearchUserResultVO> lazyDataModel;

    @Getter
    @Setter
    private Integer userAcId;

    @PostConstruct
    private void init() {
        log.info("======UserMgtView init======");

        statusVOs = commonConstantClientService.searchStatusList();
        divisionVOs = divisionAdminClientService.getByPostId(SecurityUtils.getCurrentPostId());
        posts = postClientService.findByDivisionAdmin(SecurityUtils.getCurrentPostId());
    }

    public void search() {
        log.info("search() start");
        List<Integer> divisionIds
            = divisionVOs.parallelStream().map(division -> division.getDivisionId()).collect(Collectors.toList());
        searchUserCriteriaVO.setDivisionIds(divisionIds);
        lazyDataModel = new UserLazyDataModel(userAccountClientService, searchUserCriteriaVO);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        searchUserCriteriaVO = new SearchUserCriteriaVO();
        lazyDataModel = null;
        log.info("reset() end");
    }

    public void changeStatus() {
        log.info("changeStatus() start");
        UserAccountVO userAccountVO = userAccountClientService.findOne(userAcId);
        if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(userAccountVO.getStatus())) {
            userAccountVO.setStatus(CoreConstant.STATUS_INACTIVE);
        } else {
            userAccountVO.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        userAccountClientService.save(userAccountVO);
        log.info("changeStatus() end");
    }

}
