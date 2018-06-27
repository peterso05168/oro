package hk.oro.iefas.web.ledger.chequehandling.incomingcheque.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.shroff.vo.ChequeVO;
import hk.oro.iefas.domain.shroff.vo.IncomingChequeResultVO;
import hk.oro.iefas.domain.shroff.vo.SearchIncomingChequeVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.ledger.chequehandling.incomingcheque.service.ChequeClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class IncomingChequeSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer chequeId;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private ChequeClientService chequeClientService;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfoVOS;

    @Getter
    @Setter
    private SearchIncomingChequeVO searchIncomingChequeVO;

    @Getter
    @Setter
    private List<StatusVO> statusVOS;

    @Getter
    @Setter
    private LazyDataModel<IncomingChequeResultVO> lazyDataModel;

    @PostConstruct
    private void init() {
        log.info("========== IncomingChequeSearchView init start ================");
        searchIncomingChequeVO = new SearchIncomingChequeVO();
        currencyBasicInfoVOS = currencyClientService.findAll();
        statusVOS = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.CQI.name());
        log.info("========== IncomingChequeSearchView init end ==========");
    }

    public void search() {
        log.info("search() start criteria " + searchIncomingChequeVO);
        lazyDataModel = new SimpleLazyDataModel<>(RequestUriConstant.CLIENT_URI_SEARCH_INCOMING_CHEQUE_LIST_BY_CRITERIA,
            searchIncomingChequeVO);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        lazyDataModel = null;
        searchIncomingChequeVO = new SearchIncomingChequeVO();
        log.info("reset() end");
    }

    public void delete() {
        log.info("delete() start");
        changeStatus(chequeId, CoreConstant.CHEQUE_STATUS_DELETED);
        log.info("delete() end");
    }

    public void onhold() {
        log.info("onhold() start");
        changeStatus(chequeId, CoreConstant.CHEQUE_STATUS_ONHOLD);
        log.info("onhold() end");
    }

    public void reject() {
        log.info("reject() start");
        changeStatus(chequeId, CoreConstant.CHEQUE_STATUS_REJECTED);
        log.info("reject() end");
    }

    public void received() {
        log.info("received() start");
        changeStatus(chequeId, CoreConstant.CHEQUE_STATUS_RECEIVED);
        log.info("received() end");
    }

    private void changeStatus(Integer chequeId, String status) {
        log.info("changeStatus() start - chequeId : " + chequeId + " status : " + status);
        ChequeVO chequeVO = chequeClientService.getIncomingChequeDetail(chequeId);
        if (chequeVO != null) {
            chequeVO.setStatus(status);
            chequeClientService.saveIncomingCheque(chequeVO);
        }
        log.info("changeStatus() end");
    }
}
