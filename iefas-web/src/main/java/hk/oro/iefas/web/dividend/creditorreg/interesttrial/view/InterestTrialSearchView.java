package hk.oro.iefas.web.dividend.creditorreg.interesttrial.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.dividend.vo.InterestTrialAdjudicationVO;
import hk.oro.iefas.domain.dividend.vo.SearchInterestTrialCriteriaVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.creditorreg.interesttrial.service.InterestTrialClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
@ViewScoped
public class InterestTrialSearchView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Autowired
    private InterestTrialClientService interestTrialClientService;

    @Autowired
    private CaseClientService caseClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private SearchInterestTrialCriteriaVO criteria;

    @Getter
    @Setter
    private List<CreditorVO> creditorList;

    @Getter
    @Setter
    private Integer selectCreditorId;

    @Getter
    @Setter
    private CaseNumberVO caseNumber = new CaseNumberVO();

    @Getter
    @Setter
    private LazyDataModel<InterestTrialAdjudicationVO> lazyDataModel;

    @Getter
    @Setter
    private String caseName;

    // @Getter
    // @Setter
    // private Integer intTrAdjId;

    @Getter
    private String deleteMsg;

    @Getter
    private Map<String, String> statusMap = new HashMap<>();

    @PostConstruct
    public void init() {
        log.info("init() - start");
        String deleteMsgCode = Faces.getFlashAttribute(DividendWebConstant.DELETE_MSG_CODE);
        if (deleteMsgCode != null && !"".equals(deleteMsgCode.trim())) {
            deleteMsg = appResourceUtils.getMessage(deleteMsgCode);
            showComponent("msgDialog");
        }

        criteria = new SearchInterestTrialCriteriaVO();
        criteria.setCaseNumber(new CaseNumberVO());

        List<StatusVO> statusVOs = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.WFL.name());
        if (CommonUtils.isNotEmpty(statusVOs)) {
            statusVOs.stream().forEach(item -> {
                statusMap.put(item.getStatusVal(), item.getStatusName());
            });
        }
        log.info("init() - end");
    }

    public void searchInterestTrialList() {
        log.info("searchInterestTrialList() - start");
        CaseVO caseVO = caseClientService.findByCaseNumber(criteria.getCaseNumber());
        if (caseVO != null) {
            this.caseName = caseVO.getCaseName();
            criteria.setCaseId(caseVO.getCaseId());
        } else {
            this.caseName = null;
            criteria.setCaseId(null);
        }
        this.lazyDataModel = new SimpleLazyDataModel<InterestTrialAdjudicationVO, SearchInterestTrialCriteriaVO>(
            RequestUriConstant.CLIENT_URI_SEARCH_INTERESTTRIALLIST, criteria);
        log.info("searchInterestTrialList() - end");
    }

    public String toEdit() {
        log.info("toEdit() - start");
        if (this.selectCreditorId == null || this.selectCreditorId.intValue() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.CREDITOR_NAME), ""));
            return null;
        }
        InterestTrialAdjudicationVO interestTrialAdjudication
            = this.interestTrialClientService.createInterestTrial(this.selectCreditorId);
        if (interestTrialAdjudication == null) {
            // TODO the error msg is not clear, the follow would be better:
            // no adjudication of the creditor has been approved or the interest trial has already been created
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_CREATE_INTERESTTRIAL_ERROR), ""));
            return null;
        } else {
            Faces.setFlashAttribute(DividendWebConstant.INTEREST_TRIAL_ADJUDICATION, interestTrialAdjudication);
        }
        log.info("toEdit() - end");
        return EDIT_PAGE;
    }

    public void searchCreditorInfo() {
        log.info("getCreditorInfo() start - and caseNum =" + this.caseNumber);
        this.creditorList = this.interestTrialClientService.searchCreditorByCaseNumber(this.caseNumber);
        log.info("getCreditorInfo() end creditorList - " + creditorList);
    }

    public void reset() {
        log.info("reset - start");
        criteria = new SearchInterestTrialCriteriaVO();
        criteria.setCaseNumber(new CaseNumberVO());
        this.creditorList = null;
        this.selectCreditorId = null;
        this.caseNumber = new CaseNumberVO();
        this.creditorList = null;
        this.lazyDataModel = null;
        log.info("reset - end");
    }

    // public void deleteInterestTrial() {
    // add status checking
    // InterestTrialAdjudicationVO interestTrialAdjudicationVO
    // = interestTrialClientService.searchInterestTrialById(this.intTrAdjId);
    // interestTrialAdjudicationVO.setStatus(CoreConstant.STATUS_INACTIVE);
    // interestTrialClientService.saveInterestTrial(interestTrialAdjudicationVO);
    // }
}
