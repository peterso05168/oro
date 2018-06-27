package hk.oro.iefas.web.funds.investment.cashrequirement.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.domain.funds.vo.DailyCashRequirementResultVO;
import hk.oro.iefas.domain.funds.vo.InvestmentTypeVO;
import hk.oro.iefas.domain.funds.vo.SearchDailyCashRequirementCriteriaVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.investment.cashrequirement.service.CashRequirementClientService;
import hk.oro.iefas.web.funds.maintenance.deposittarget.service.FundsCommonClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class CashRequirementSearchView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private FundsCommonClientService fundsCommonClientService;

    @Inject
    private CashRequirementClientService cashRequirementClientService;

    @Getter
    @Setter
    private List<InvestmentTypeVO> investmentTypes;

    @Getter
    @Setter
    private SearchDailyCashRequirementCriteriaVO criteriaVO = new SearchDailyCashRequirementCriteriaVO();

    @Getter
    @Setter
    private LazyDataModel<DailyCashRequirementResultVO> dataModel;

    @PostConstruct
    private void init() {
        investmentTypes = fundsCommonClientService.searchInvestmentType();
    }

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + criteriaVO);
        dataModel = null;
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        criteriaVO = new SearchDailyCashRequirementCriteriaVO();
        dataModel = null;
        log.info("reset() end");
    }
}
