package hk.oro.iefas.web.dividend.dividendprocess.orfeecomputation.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.common.vo.CaseTypeVO;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.dividend.vo.CreateOrFeeComputationVO;
import hk.oro.iefas.domain.dividend.vo.CreditorTypeVO;
import hk.oro.iefas.domain.dividend.vo.DividendCalculationVO;
import hk.oro.iefas.domain.dividend.vo.SearchOrFeeComputationCriteriaVO;
import hk.oro.iefas.domain.dividend.vo.ValidateOrFeeComputationVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.common.service.CommonDividendClientService;
import hk.oro.iefas.web.dividend.dividendprocess.orfeecomputation.service.OrFeeComputationClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (週日, 10 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named(value = "feeComputationSearchView")
@ViewScoped
public class OrFeeComputationSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 3893101115750356152L;

    @Autowired
    private CommonClientService commonClientService;

    @Autowired
    private CommonDividendClientService commonDividendClientService;

    @Autowired
    private OrFeeComputationClientService orFeeComputationClientService;

    @Autowired
    private CaseClientService caseClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private SearchOrFeeComputationCriteriaVO criteriaVO;

    @Getter
    private List<ApplicationCodeTableVO> paymentTypes;

    @Getter
    private List<CreditorTypeVO> creditorTypes;

    @Getter
    private List<StatusVO> statusVOs;

    @Getter
    private List<CaseTypeVO> caseTypes;

    @Getter
    private String caseName;

    @Getter
    private CreateOrFeeComputationVO createORFeeComputationVO;

    @Getter
    @Setter
    private CaseNumberVO caseNumber;

    @Getter
    private Map<String, String> statusMap = new HashMap<>();

    @Getter
    private Map<String, String> paymentTypeMap = new HashMap<>();

    @Getter
    private String deleteMsg;

    @PostConstruct
    private void init() {
        log.info("ORFeeComputationSearchView init");

        String deleteMsgCode = Faces.getFlashAttribute(DividendWebConstant.DELETE_MSG_CODE);
        if (deleteMsgCode != null && !"".equals(deleteMsgCode.trim())) {
            deleteMsg = appResourceUtils.getMessage(deleteMsgCode);
            showComponent("msgDialog");
        }

        criteriaVO = new SearchOrFeeComputationCriteriaVO();
        CaseNumberVO caseNumberVO = new CaseNumberVO();
        criteriaVO.setCaseNumber(caseNumberVO);
        criteriaVO.setCreditorType(new CreditorTypeVO());

        paymentTypes = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.DPA.name());
        if (CommonUtils.isNotEmpty(paymentTypes)) {
            paymentTypes.stream().forEach(item -> {
                paymentTypeMap.put(item.getCodeValue(), item.getCodeDesc());
            });
        }

        creditorTypes = commonDividendClientService.searchCreditorType();
        statusVOs = appResourceUtils.getStatusListByGroup(ApplicationCodeTableEnum.OCS.name());
        if (CommonUtils.isNotEmpty(statusVOs)) {
            statusVOs.stream().forEach(item -> {
                statusMap.put(item.getStatusVal(), item.getStatusName());
            });
        }
        caseTypes = commonClientService.searchDividendCaseTypeList();

    }

    @Getter
    @Setter
    private LazyDataModel<DividendCalculationVO> dividendCalculationDataModel;

    public void search() {
        log.info("search() start");
        CaseVO caseVO = caseClientService.findByCaseNumber(criteriaVO.getCaseNumber());
        if (caseVO != null) {
            caseName = caseVO.getCaseName();
        } else {
            caseName = null;
        }

        dividendCalculationDataModel = new SimpleLazyDataModel<DividendCalculationVO, SearchOrFeeComputationCriteriaVO>(
            RequestUriConstant.CLIENT_URI_ORFEECOMPUTATION_LIST, criteriaVO);
        log.info("search() end");
    }

    public void reset() {
        criteriaVO = new SearchOrFeeComputationCriteriaVO();
        criteriaVO.setCaseNumber(new CaseNumberVO());
        criteriaVO.setCreditorType(new CreditorTypeVO());
        dividendCalculationDataModel = null;
        log.info("reset() end");
    }

    public void popUpCreateComputation() {
        createORFeeComputationVO = new CreateOrFeeComputationVO();
        caseNumber = criteriaVO.getCaseNumber();
        if (criteriaVO.getPaymentType() != null && CommonUtils.isNotEmpty(caseTypes)) {
            caseTypes.forEach(type -> {
                if (type.getCaseTypeCode().equals(criteriaVO.getPaymentType())) {
                    createORFeeComputationVO.setPaymentType(criteriaVO.getPaymentType());
                }
            });
        }
        showComponent("createDialog");
    }

    public void createHandleClose(CloseEvent event) {
        log.info("createHandleClose() start ");
        createORFeeComputationVO = null;
        log.info("createHandleClose() end ");
    }

    public String createComputation() {
        if (createORFeeComputationValidate()) {
            Faces.setFlashAttribute("createORFeeComputationVO", createORFeeComputationVO);
            return EDIT_PAGE;
        }
        return null;
    }

    private boolean createORFeeComputationValidate() {
        if (CommonUtils.isNotEmpty(caseTypes) && caseNumber != null && caseNumber.getCaseType() != null) {
            CaseVO caseVO = caseClientService.findByCaseNumber(caseNumber);
            if (caseVO == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.CASE_NUMBER), ""));
                return false;
            }
            createORFeeComputationVO.setVcase(caseVO);
            boolean caseTypeCorrect = false;
            for (CaseTypeVO caseType : caseTypes) {
                if (caseType.getCaseTypeCode().equals(caseNumber.getCaseType())) {
                    caseTypeCorrect = true;
                    break;
                }
            }
            if (!caseTypeCorrect) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_CREATE_ADJRESULT_CASE_TYPE_ERROR), ""));
                return false;
            }

            ValidateOrFeeComputationVO validateOrFeeComputationVO = new ValidateOrFeeComputationVO();
            validateOrFeeComputationVO.setCaseId(caseVO.getCaseId());
            validateOrFeeComputationVO.setCreateValidation(true);
            if (!orFeeComputationClientService.validateCaseCreatable(validateOrFeeComputationVO)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_CREATE_COMPUTATION_CASE_ERROR), ""));
                return false;
            }
        }
        return true;
    }
}
