package hk.oro.iefas.web.funds.maintenance.currencyrate.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.CloseEvent;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.core.util.ValidationUtils;
import hk.oro.iefas.domain.bank.vo.CurrencyRateVO;
import hk.oro.iefas.domain.bank.vo.CurrencyVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
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
public class CurrencyRateEditView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private CommonConstantClientService commonConstantClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    @Setter
    private CurrencyVO currencyVO;

    @Getter
    @Setter
    private BigDecimal rateAmount;

    @Getter
    @Setter
    private CurrencyRateVO currencyRateVO;

    @Getter
    @Setter
    private Integer curcyId;

    @Getter
    @Setter
    private List<CurrencyRateVO> filteredCurrencyRates;

    @PostConstruct
    private void init() {
        log.info("======CurrencyRateEditView init======");

        curcyId = Faces.getRequestParameter("curcyId", Integer.class);
        log.debug("curcyId: " + curcyId);
        if (curcyId != null) {
            currencyVO = currencyClientService.findOne(curcyId);
            setCurrentRateValue();
        } else {
            currencyVO = new CurrencyVO();
        }
        filterCurrencyRate();

        currencyRateVO = new CurrencyRateVO();
        statusVOs = commonConstantClientService.searchStatusList();
    }

    private void filterCurrencyRate() {
        filteredCurrencyRates = new ArrayList<>();
        List<CurrencyRateVO> currencyRates = this.currencyVO.getCurrencyRates();
        if (currencyRates != null && currencyRates.size() > 0) {
            for (CurrencyRateVO currencyRateVO : currencyRates) {
                if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(currencyRateVO.getStatus())) {
                    filteredCurrencyRates.add(currencyRateVO);
                }
            }
        }
    }

    public void save() {
        log.info("save() start " + this.currencyVO);

        Integer curcyId = currencyClientService.save(this.currencyVO);

        this.currencyVO = currencyClientService.findOne(curcyId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        log.info("save() end " + this.currencyVO);
    }

    public void addCurrencyRate() {
        log.info("addCurrencyRate() start ");
        boolean validateDatePeriod
            = ValidationUtils.validateDatePeriod(currencyRateVO.getEffectiveFrom(), currencyRateVO.getEffectiveTo());
        if (!validateDatePeriod) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Period.", null));
        } else {
            List<CurrencyRateVO> currencyRates = this.currencyVO.getCurrencyRates();
            if (CommonUtils.isEmpty(currencyRates)) {
                currencyRates = new ArrayList<>();
                this.currencyVO.setCurrencyRates(currencyRates);
            }

            if (!currencyRates.contains(this.currencyRateVO)) {
                this.currencyRateVO.setStatus(CoreConstant.STATUS_ACTIVE);
                currencyRates.add(this.currencyRateVO);
            }
            filterCurrencyRate();
            hideComponent("rateDialog");
        }

        log.info("addCurrencyRate() end ");
    }

    public void handleClose(CloseEvent event) {
        log.info("handleClose() start ");
        this.currencyRateVO = new CurrencyRateVO();
        setCurrentRateValue();
        log.info("handleClose() end ");
    }

    private void setCurrentRateValue() {
        List<CurrencyRateVO> currencyRates = this.currencyVO.getCurrencyRates();
        if (currencyRates != null && currencyRates.size() > 0) {
            Date currentDay = DateUtils.getCurrentDay();
            Optional<CurrencyRateVO> first = currencyRates.stream()
                .filter(rate -> (rate.getEffectiveFrom().before(currentDay)
                    || DateUtils.isSameDay(rate.getEffectiveFrom(), currentDay))
                    && (rate.getEffectiveTo().after(currentDay)
                        || DateUtils.isSameDay(rate.getEffectiveTo(), currentDay)))
                .findFirst();
            if (first.isPresent()) {
                rateAmount = first.get().getRateAmount();
            }
        }
    }

    public void delete() {
        List<CurrencyRateVO> currencyRates = this.currencyVO.getCurrencyRates();
        if (currencyRates != null && currencyRates.size() > 0) {
            for (CurrencyRateVO currencyRateVO : currencyRates) {
                if (currencyRateVO.equals(this.currencyRateVO)) {
                    currencyRateVO.setStatus(CoreConstant.STATUS_INACTIVE);
                }
            }
        }
        filterCurrencyRate();
    }

}
