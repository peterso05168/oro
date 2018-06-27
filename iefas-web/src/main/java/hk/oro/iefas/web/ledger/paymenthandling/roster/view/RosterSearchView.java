package hk.oro.iefas.web.ledger.paymenthandling.roster.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.RosterResultVO;
import hk.oro.iefas.domain.shroff.vo.RosterVO;
import hk.oro.iefas.domain.shroff.vo.SearchRosterVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.paymenthandling.roster.service.RosterClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2809 $ $Date: 2018-06-01 11:25:16 +0800 (週五, 01 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class RosterSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private Integer rosterId;

    @Inject
    private RosterClientService rosterClientService;

    @Getter
    @Setter
    private LazyDataModel<RosterResultVO> lazyDataModel;

    @Getter
    @Setter
    private SearchRosterVO searchRosterVO;

    @Inject
    private AppResourceUtils appResourceUtils;

    @PostConstruct
    private void init() {
        log.info("rosterSearchView init ======");
        searchRosterVO = new SearchRosterVO();
        log.info("rosterSearchView init end");
    }

    public void search() {
        log.info("search() start");
        this.lazyDataModel = new SimpleLazyDataModel<RosterResultVO, SearchRosterVO>(
            RequestUriConstant.CLIENT_URI_SEARCH_ROSTER_LIST_BY_CRITERIA, searchRosterVO);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        lazyDataModel = null;
        searchRosterVO = new SearchRosterVO();
        log.info("reset() end");
    }

    public void delete() {
        log.info("delete() start");
        RosterVO rosterVO = rosterClientService.getRosterDetail(rosterId);
        rosterVO.setStatus(CoreConstant.STATUS_DELETE);
        rosterClientService.saveRoster(rosterVO);
        log.info("delete() end");
    }
}
