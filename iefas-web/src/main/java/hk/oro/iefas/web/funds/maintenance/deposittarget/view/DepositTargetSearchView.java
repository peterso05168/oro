package hk.oro.iefas.web.funds.maintenance.deposittarget.view;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.funds.vo.CashRequirementResultVO;
import hk.oro.iefas.domain.funds.vo.InvestmentTypeVO;
import hk.oro.iefas.domain.funds.vo.SearchCashRequirementCriteriaVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.deposittarget.service.DepositTargetClientService;
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
public class DepositTargetSearchView implements Serializable {

    private static final long serialVersionUID = 131961945917584888L;

    @Inject
    private FundsCommonClientService fundsCommonClientService;

    @Inject
    private DepositTargetClientService cashRequirementClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private Map<String, String> investmentTypes = new LinkedHashMap<>();

    @Getter
    @Setter
    private String investmentType;

    @Getter
    @Setter
    private SearchCashRequirementCriteriaVO searchCriteriaVO = new SearchCashRequirementCriteriaVO();

    @Getter
    @Setter
    private LazyDataModel<CashRequirementResultVO> lazyDataModel;

    @PostConstruct
    private void init() {
        log.info("======CashRequirementSearchView init======");
        this.searchInvestmentType();
    }

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + searchCriteriaVO);
        lazyDataModel = new SimpleLazyDataModel<CashRequirementResultVO, SearchCashRequirementCriteriaVO>(
            RequestUriConstant.CLIENT_URI_CASH_REQUIREMENT_LIST, searchCriteriaVO);
        log.info("search() end");
    }

    public void reset() {
        searchCriteriaVO = new SearchCashRequirementCriteriaVO();
        lazyDataModel = null;
    }

    public void searchInvestmentType() {
        investmentTypes.clear();
        List<InvestmentTypeVO> investmentTypeVOs = fundsCommonClientService.searchInvestmentType();
        if (CommonUtils.isNotEmpty(investmentTypeVOs)) {
            for (InvestmentTypeVO investmentTypeVO : investmentTypeVOs) {
                investmentTypes.put(investmentTypeVO.getInvestmentTypeDesc(),
                    String.valueOf(investmentTypeVO.getInvestmentTypeId().intValue()));
            }

        }
    }

    public void deleteCashRequirement(CashRequirementResultVO cashRequirementResultVO) {
        if (cashRequirementResultVO != null) {
            cashRequirementResultVO.setStatus(CoreConstant.STATUS_INACTIVE);
            Integer caseReqId = cashRequirementClientService.saveCaseRequirement(cashRequirementResultVO);
            if (caseReqId != null && caseReqId > 0) {
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
            }
        }

    }

}
