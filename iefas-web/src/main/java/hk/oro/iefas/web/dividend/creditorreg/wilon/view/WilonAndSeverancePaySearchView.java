package hk.oro.iefas.web.dividend.creditorreg.wilon.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.dividend.vo.CreateWilonAndSeveranceVO;
import hk.oro.iefas.domain.dividend.vo.SearchWilonAndSeverancePayCriteriaVO;
import hk.oro.iefas.domain.dividend.vo.WilonAndSeverancePayVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.creditorreg.wilon.service.WilonAndSeveranceClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3087 $ $Date: 2018-06-12 18:02:20 +0800 (週二, 12 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@Named
@ViewScoped
public class WilonAndSeverancePaySearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private WilonAndSeveranceClientService wilonAndSeveranceClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Autowired
    private CaseClientService caseClientService;

    @Getter
    @Setter
    private SearchWilonAndSeverancePayCriteriaVO criteria = new SearchWilonAndSeverancePayCriteriaVO();

    @Getter
    @Setter
    private CreateWilonAndSeveranceVO createWILONAndSeverance = new CreateWilonAndSeveranceVO();

    @Getter
    @Setter
    private List<CreditorVO> creditorList;

    @Getter
    @Setter
    private Integer selectedCreditorId;

    @Getter
    @Setter
    private LazyDataModel<WilonAndSeverancePayVO> lazyDataModel;

    @Getter
    @Setter
    private String caseName;

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + criteria);
        this.caseName = null;
        if (criteria != null && criteria.getCaseNumber() != null) {
            CaseVO caseVO = caseClientService.findByCaseNumber(criteria.getCaseNumber());
            if (caseVO != null) {
                this.caseName = caseVO.getCaseName();
            }
        }
        this.lazyDataModel = new SimpleLazyDataModel<WilonAndSeverancePayVO, SearchWilonAndSeverancePayCriteriaVO>(
            RequestUriConstant.CLIENT_URI_SEARCH_WILON_AND_SEVERANCE_PAY_LIST, criteria);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        criteria = new SearchWilonAndSeverancePayCriteriaVO();
        this.lazyDataModel = null;
        creditorList = null;
        log.info("reset() end");
    }

    public void createHandleClose(CloseEvent event) {
        log.info("createHandleClose() start ");
        createWILONAndSeverance = new CreateWilonAndSeveranceVO();
        this.creditorList = new ArrayList<CreditorVO>();
        log.info("createHandleClose() end ");
    }

    public String toEdit() {
        log.info("toEdit() - start");
        if (validateCreateWILONAndSeverance()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_CREATE_WILONANDSEVERANCE_ERROR), ""));
            return null;
        }
        Faces.setFlashAttribute(DividendWebConstant.WILONANDSEVRNPAY_Id, 0);
        Faces.setFlashAttribute(DividendWebConstant.CREDITOR_ID, this.selectedCreditorId);
        log.info("toEdit() - end");
        return EDIT_PAGE;
    }

    public void getCreditorInfo() {
        log.info("getCreditorInfo() start");
        this.creditorList
            = wilonAndSeveranceClientService.searchCreditorByCaseNumber(createWILONAndSeverance.getCaseNumber());
        log.info("getCreditorInfo() end creditorList - " + creditorList);
    }

    public boolean validateCreateWILONAndSeverance() {
        log.info("validateCreateWILONAndSeverance() start");
        boolean result = true;
        if (this.selectedCreditorId != null && this.selectedCreditorId.intValue() > 0) {
            this.createWILONAndSeverance.setCreditorId(this.selectedCreditorId);
            result = this.wilonAndSeveranceClientService.createWILONAndSeveranceValidate(this.createWILONAndSeverance);
        }
        log.info("validateCreateWILONAndSeverance() end and return result = " + result);
        return result;
    }
}
