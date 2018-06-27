package hk.oro.iefas.web.funds.maintenance.deposittarget.view;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.ValidationUtils;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.funds.vo.CashRequirementResultVO;
import hk.oro.iefas.domain.funds.vo.InvestmentTypeVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
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
public class DepositTargetEditView implements Serializable {

    private static final long serialVersionUID = -7709480757484132823L;

    @Inject
    private DepositTargetClientService cashRequirementClientService;

    @Inject
    private FundsCommonClientService fundsCommonClientService;

    @Autowired
    private CommonConstantClientService constantClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private CashRequirementResultVO cashRequirementResultVO;

    @Getter
    @Setter
    private Map<String, String> investmentTypes = new LinkedHashMap<>();

    @Getter
    @Setter
    private Map<String, String> statusList = new LinkedHashMap<>();

    @Getter
    @Setter
    private boolean disabledInvestType;

    @Getter
    @Setter
    private boolean disabledStatus;

    @PostConstruct
    private void init() {
        Integer cashReqId = Faces.getRequestParameter("cashReqId", Integer.class);

        investmentTypes.clear();
        List<InvestmentTypeVO> investmentTypeVOs = fundsCommonClientService.searchInvestmentType();
        if (CommonUtils.isNotEmpty(investmentTypeVOs)) {
            for (InvestmentTypeVO investmentTypeVO : investmentTypeVOs) {
                investmentTypes.put(investmentTypeVO.getInvestmentTypeDesc(),
                    String.valueOf(investmentTypeVO.getInvestmentTypeId().intValue()));
            }

        }

        this.statusList.clear();
        List<StatusVO> statusVOlist = constantClientService.searchStatusList();
        if (CommonUtils.isNotEmpty(statusVOlist)) {
            statusVOlist.forEach(status -> this.statusList.put(status.getStatusName(), status.getStatusVal()));
        }

        this.searchCashRequirement(cashReqId);
        log.info("page init end -");
    }

    private void searchCashRequirement(Integer cashReqId) {
        this.cashRequirementResultVO = cashRequirementClientService.searchCashRequirement(cashReqId);
        cashReqId = this.cashRequirementResultVO.getCashRequirementResultId();
        this.disabledInvestType = cashReqId != null && cashReqId > 0;
        this.disabledStatus = cashReqId == null || cashReqId == 0;
    }

    public void saveCaseRequirement() {
        if (cashRequirementResultVO != null) {
            if (validateDatePeriod(cashRequirementResultVO)) {
                Integer caseRequirementId = cashRequirementClientService.saveCaseRequirement(cashRequirementResultVO);
                if (caseRequirementId != null && caseRequirementId > 0) {
                    Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
                    this.searchCashRequirement(caseRequirementId);
                }

            }

        }
    }

    public boolean validateDatePeriod(CashRequirementResultVO resultVO) {
        if (resultVO != null) {
            if (resultVO.getRqmtPeriodFrom() != null && resultVO.getRqmtPeriodTo() != null) {
                boolean validateDatePeriod
                    = ValidationUtils.validateDatePeriod(resultVO.getRqmtPeriodFrom(), resultVO.getRqmtPeriodTo());
                if (!validateDatePeriod) {
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Period.", null));
                    return false;
                }
            }

            Boolean validate = cashRequirementClientService.saveCaseRequirementValidate(resultVO);
            if (validate != null && !validate) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Period Overlap.", null));
                return false;
            }
            return true;
        }

        return false;
    }

}
