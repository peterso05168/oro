package hk.oro.iefas.web.dividend.creditorreg.adjudication.view;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.dividend.vo.AdjucationResultVO;
import hk.oro.iefas.domain.dividend.vo.SearchAdjudicationCriteriaVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3085 $ $Date: 2018-06-12 17:09:16 +0800 (週二, 12 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class AdjudicationSearchView extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Autowired
    private CaseClientService caseClientService;

    @Getter
    @Setter
    private SearchAdjudicationCriteriaVO criteriaVO;

    @Getter
    @Setter
    private CaseNumberVO caseNumberVO;

    @Getter
    @Setter
    private LazyDataModel<AdjucationResultVO> preAdjucationResultDataModel;

    @Getter
    @Setter
    private LazyDataModel<AdjucationResultVO> ordAdjucationResultDataModel;

    @Getter
    private String caseName;

    @Getter
    @Setter
    private List<StatusVO> statusVOs;

    @Getter
    private String deleteMsg;

    @PostConstruct
    private void init() {
        String deleteMsgCode = Faces.getFlashAttribute(DividendWebConstant.DELETE_MSG_CODE);
        if (deleteMsgCode != null && !"".equals(deleteMsgCode.trim())) {
            deleteMsg = appResourceUtils.getMessage(deleteMsgCode);
            showComponent("msgDialog");
        }

        log.info("==============AdjudicationSearchView init===================");
        criteriaVO = new SearchAdjudicationCriteriaVO();
        caseNumberVO = new CaseNumberVO();
        criteriaVO.setCaseNumber(caseNumberVO);
        statusVOs = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.ADJ.name());
    }

    public void popUpAdjudication() {
        caseNumberVO = criteriaVO.getCaseNumber();
        showComponent("createDialog");
    }

    public void createHandleClose(CloseEvent event) {
        log.info("createHandleClose() start ");
        caseNumberVO = new CaseNumberVO();
        log.info("createHandleClose() end ");
    }

    public String createAdjudication() throws ParseException {
        CaseVO caseVO = caseClientService.findByCaseNumber(this.caseNumberVO);
        if (caseVO == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.CASE_NUMBER), ""));
            return null;
        } else {
            Faces.setFlashAttribute(DividendWebConstant.CASE_VO, caseVO);
            return EDIT_PAGE;
        }
    }

    public void searchAdjudicationList() {
        log.info("===========searchAdjudicationList start=============");
        CaseVO caseVO = caseClientService.findByCaseNumber(criteriaVO.getCaseNumber());
        if (caseVO != null) {
            caseName = caseVO.getCaseName();
            criteriaVO.setCaseId(caseVO.getCaseId());
        } else {
            caseName = null;
            criteriaVO.setCaseId(null);
        }

        preAdjucationResultDataModel = new SimpleLazyDataModel<AdjucationResultVO, SearchAdjudicationCriteriaVO>(
            RequestUriConstant.CLIENT_URI_PRE_ADJUDICATION_LIST, criteriaVO);

        ordAdjucationResultDataModel = new SimpleLazyDataModel<AdjucationResultVO, SearchAdjudicationCriteriaVO>(
            RequestUriConstant.CLIENT_URI_ORD_ADJUDICATION_LIST, criteriaVO);
    }

    public void reset() {
        criteriaVO = new SearchAdjudicationCriteriaVO();
        criteriaVO.setCaseNumber(null);
        preAdjucationResultDataModel = null;
        ordAdjucationResultDataModel = null;
    }
}
