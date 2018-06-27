package hk.oro.iefas.web.funds.investment.fundsdeposit.view;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.bank.vo.CalculationOfFundsAvailableVO;
import hk.oro.iefas.domain.bank.vo.SearchPlacingDepositsCriteriaVO;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.investment.fundsdeposit.service.PlacingDepositsClientService;
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
public class PlacingDepositsSearchView extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private PlacingDepositsClientService placingDepositsClientService;

    @Getter
    @Setter
    private SearchPlacingDepositsCriteriaVO criteriaVO = new SearchPlacingDepositsCriteriaVO();

    @Getter
    @Setter
    private Date investmentDate;

    @Getter
    @Setter
    private LazyDataModel<CalculationOfFundsAvailableVO> dataModel;

    @Getter
    @Setter
    private Integer calculationOfFundsAvailableId;

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + criteriaVO);
        dataModel = new SimpleLazyDataModel<CalculationOfFundsAvailableVO, SearchPlacingDepositsCriteriaVO>(
            RequestUriConstant.CLIENT_URI_PLACING_DEPOSITS_LIST, criteriaVO);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        criteriaVO = new SearchPlacingDepositsCriteriaVO();
        dataModel = null;
        log.info("reset() end");
    }

    public String createPlacingDeposit() throws ParseException {
        if (placingDepositsClientService.existsByInvestmentDate(this.investmentDate)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "The funds available for placing deposits of that day already exists.", ""));
            return null;
        } else {
            Faces.setFlashAttribute("investmentDate", this.investmentDate);
            return EDIT_PAGE;
        }
    }

    public void createHandleClose(CloseEvent event) {
        log.info("createHandleClose() start ");
        investmentDate = null;
        log.info("createHandleClose() end ");
    }

    public void changeStatus() {
        CalculationOfFundsAvailableVO calculationOfFundsAvailable
            = placingDepositsClientService.searchPlacingDeposits(calculationOfFundsAvailableId);
        if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(calculationOfFundsAvailable.getStatus())) {
            calculationOfFundsAvailable.setStatus(CoreConstant.STATUS_INACTIVE);
        } else {
            calculationOfFundsAvailable.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        placingDepositsClientService.savePlacingDeposits(calculationOfFundsAvailable);
    }
}
