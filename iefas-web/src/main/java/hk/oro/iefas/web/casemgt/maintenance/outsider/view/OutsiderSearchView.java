package hk.oro.iefas.web.casemgt.maintenance.outsider.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.OutsiderVO;
import hk.oro.iefas.domain.casemgt.vo.SearchOutsiderDetailCriteriaVO;
import hk.oro.iefas.domain.casemgt.vo.SearchOutsiderDetailResultVO;
import hk.oro.iefas.domain.common.vo.OutsiderTypeVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.web.casemgt.maintenance.outsider.service.OutsiderClientService;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2674 $ $Date: 2018-05-28 18:21:29 +0800 (週一, 28 五月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class OutsiderSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer outsiderId;

    @Getter
    @Setter
    private SearchOutsiderDetailCriteriaVO criteria = new SearchOutsiderDetailCriteriaVO();

    @Getter
    @Setter
    private LazyDataModel<SearchOutsiderDetailResultVO> lazyDataModel;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private CommonClientService commonClientService;

    @Inject
    private OutsiderClientService outsiderClientService;

    @Getter
    @Setter
    private List<OutsiderTypeVO> outsiderTypeVOs;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @PostConstruct
    private void init() {
        log.info("OutsiderSearchView init ======");
        statusVOs = commonConstantClientService.searchStatusList();
        outsiderTypeVOs = commonClientService.searchOutsiderTypeList();
        log.info("OutsiderSearchView init end");
    }

    public void changeStatus() {
        log.info("OutsiderSearchView.changeStatus() start");
        OutsiderVO outsider = outsiderClientService.getOutsiderDetail(outsiderId);
        if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(outsider.getStatus())) {
            outsider.setStatus(CoreConstant.STATUS_INACTIVE);
        } else {
            outsider.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        outsiderClientService.saveOutsider(outsider);
        log.info("OutsiderSearchView.changeStatus() end");
    }

    public void search() {
        log.info("search() start");
        this.lazyDataModel = new SimpleLazyDataModel<SearchOutsiderDetailResultVO, SearchOutsiderDetailCriteriaVO>(
            RequestUriConstant.CLIENT_URI_SEARCH_OUTSIDER, criteria);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        lazyDataModel = null;
        criteria = new SearchOutsiderDetailCriteriaVO();
        log.info("reset() end");
    }

}
