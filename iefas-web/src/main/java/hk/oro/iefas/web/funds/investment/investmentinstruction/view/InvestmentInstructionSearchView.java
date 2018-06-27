package hk.oro.iefas.web.funds.investment.investmentinstruction.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.funds.vo.AccountInvestmentItemVO;
import hk.oro.iefas.domain.funds.vo.InvestmentStatusVO;
import hk.oro.iefas.domain.funds.vo.SearchInvestmentInstructiontCriteriaVO;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.deposittarget.service.FundsCommonClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2743 $ $Date: 2018-05-30 19:14:53 +0800 (週三, 30 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class InvestmentInstructionSearchView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private FundsCommonClientService fundsCommonClientService;

    @Getter
    @Setter
    private List<InvestmentStatusVO> statusVOs;

    @Getter
    @Setter
    private SearchInvestmentInstructiontCriteriaVO criteriaVO = new SearchInvestmentInstructiontCriteriaVO();

    @Getter
    @Setter
    private LazyDataModel<AccountInvestmentItemVO> investmentItemDataModel;

    @Getter
    @Setter
    private Integer bankInfoId;

    @PostConstruct
    private void init() {
        statusVOs = fundsCommonClientService.searchInvestmentStatus();
        criteriaVO.setInvestmentStatus(new InvestmentStatusVO());
    }

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + criteriaVO);
        investmentItemDataModel
            = new SimpleLazyDataModel<AccountInvestmentItemVO, SearchInvestmentInstructiontCriteriaVO>(
                RequestUriConstant.CLIENT_URI_INVESTMENT_INSTRUCTION_LIST, criteriaVO);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        criteriaVO = new SearchInvestmentInstructiontCriteriaVO();
        criteriaVO.setInvestmentStatus(new InvestmentStatusVO());
        investmentItemDataModel = null;
        log.info("reset() end");
    }
}
