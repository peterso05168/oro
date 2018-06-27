package hk.oro.iefas.web.ledger.paymenthandling.generatechequefile.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.shroff.vo.ChequeFileResultVO;
import hk.oro.iefas.domain.shroff.vo.SearchChequeFileVO;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3231 $ $Date: 2018-06-20 18:08:11 +0800 (週三, 20 六月 2018) $
 * @author $Author: garrett.han $
 */
@ViewScoped
@Slf4j
@Named
public class GenerateChequeFileSearchView extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private SearchChequeFileVO criteria;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfoVOS;

    @Getter
    @Setter
    private LazyDataModel<ChequeFileResultVO> lazyDataModel;


    @Getter
    @Setter
    private List<ChequeFileResultVO> chequeFileResultVOS;

    @Inject
    private CurrencyClientService currencyClientService;

    @PostConstruct
    public void init() {
        log.info("=============== GenerateChequeFileSearchView init start =================");
        criteria = new SearchChequeFileVO();
        currencyBasicInfoVOS = currencyClientService.findAll();
        log.info("=============== GenerateChequeFileSearchView init end ===================");
    }

    public void search() {
        log.info("search start");
        log.info("search end");
    }

    public void reset() {
        log.info("reset start");
        lazyDataModel = null;
        criteria = new SearchChequeFileVO();
        log.info("reset end");
    }

    public void generate() {
        log.info("generate start");
        log.info("generate end");
    }
}
