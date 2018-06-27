package hk.oro.iefas.web.ledger.paymenthandling.paymentfile.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.shroff.vo.PaymentFileResultVO;
import hk.oro.iefas.domain.shroff.vo.PaymentFileTypeVO;
import hk.oro.iefas.domain.shroff.vo.SearchPaymentFileVO;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.ledger.paymenthandling.paymentfile.service.PaymentFileClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@ViewScoped
@Slf4j
@Named
public class PaymentFileSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private SearchPaymentFileVO criteria;

    @Getter
    @Setter
    private LazyDataModel<PaymentFileResultVO> lazyDataModel;

    @Getter
    @Setter
    private List<StatusVO> statusVOS;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfoVOS;

    @Getter
    @Setter
    private List<PaymentFileTypeVO> paymentFileTypeVOS;

    @Inject
    private PaymentFileClientService paymentFileClientService;

    @Inject
    private CommonClientService commonClientService;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private CurrencyClientService currencyClientService;

    @PostConstruct
    public void init() {
        log.info("==================== PaymentFileSearchView init start=================");
        currencyBasicInfoVOS = currencyClientService.findAll();
        paymentFileTypeVOS = commonClientService.searchPaymentFileTypeList();
        statusVOS = commonConstantClientService.searchStatusList();
        criteria = new SearchPaymentFileVO();
        log.info("==================== PaymentFileSearchView init end ===================");
    }

    public void search() {
        log.info("search start");
        lazyDataModel = new SimpleLazyDataModel<PaymentFileResultVO, SearchPaymentFileVO>(
            RequestUriConstant.CLIENT_URI_PAYMENT_FILE_SEARCH_PAYMENT_FILE, criteria);
        log.info("search end");
    }

    public void reset() {
        log.info("reset start");
        criteria = new SearchPaymentFileVO();
        lazyDataModel = null;
        log.info("reset end");
    }
}
