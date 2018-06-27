package hk.oro.iefas.web.system.divisionadministration.post.delegation.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.SearchPostCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchPostResultVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
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
public class PostDelegationSearchView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DivisionAdminClientService divisionAdminClientService;

    @Getter
    @Setter
    private List<DivisionVO> divisionVOs;

    @Getter
    @Setter
    private SearchPostCriteriaVO criteria = new SearchPostCriteriaVO();

    @Getter
    @Setter
    private LazyDataModel<SearchPostResultVO> postResultDataModel;

    @PostConstruct
    private void init() {
        log.info("======PostSearchView init======");
        divisionVOs = divisionAdminClientService.getByPostId(SecurityUtils.getCurrentPostId());
        log.info("init() end");
    }

}
