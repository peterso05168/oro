package hk.oro.iefas.web.funds.maintenance.currencyrate.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.vo.CurrencyResultVO;
import hk.oro.iefas.domain.bank.vo.CurrencySearchVO;
import hk.oro.iefas.domain.bank.vo.CurrencyVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class CurrencyRateSearchView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CommonConstantClientService commonConstantClientService;

    @Autowired
    private CurrencyClientService currencyClientService;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private CurrencySearchVO currencySearchVO = new CurrencySearchVO();

    @Getter
    @Setter
    private LazyDataModel<CurrencyResultVO> currencyRateDataModel;

    @Getter
    @Setter
    private Integer curcyId;

    @PostConstruct
    private void init() {
        log.info("======CurrencyRateSearchView init======");
        statusVOs = commonConstantClientService.searchStatusList();

    }

    public void changeStatus() {
        CurrencyVO currencyVO = currencyClientService.findOne(curcyId);
        if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(currencyVO.getStatus())) {
            currencyVO.setStatus(CoreConstant.STATUS_INACTIVE);
        } else {
            currencyVO.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        currencyClientService.save(currencyVO);
    }

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + currencySearchVO);
        currencyRateDataModel = new SimpleLazyDataModel<CurrencyResultVO, CurrencySearchVO>(
            RequestUriConstant.CLIENT_URI_CURRENCY_LIST, currencySearchVO);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        currencySearchVO = new CurrencySearchVO();
        currencyRateDataModel = null;
        log.info("reset() end");
    }
}
