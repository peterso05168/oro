package hk.oro.iefas.web.dividend.dividendprocess.dividendschedule.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.dividend.vo.CreateDividendScheduleVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleVO;
import hk.oro.iefas.domain.dividend.vo.ScheduleTypeVO;
import hk.oro.iefas.domain.dividend.vo.SearchDividendScheduleVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.component.datamodel.SimpleLazyDataModel;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.dividendprocess.dividendschedule.service.DividendScheduleClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class DividendScheduleSearchView extends BaseBean implements Serializable {

    private static final long serialVersionUID = -4775919901735063308L;

    @Inject
    private DividendScheduleClientService dividendScheduleClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private CaseClientService caseClientService;

    @Getter
    @Setter
    private String caseName;

    @Getter
    @Setter
    private SearchDividendScheduleVO searchDividendScheduleVO;

    @Getter
    @Setter
    private LazyDataModel<DividendScheduleVO> lazyDataModel;

    @Getter
    private Map<String, String> paymentTypes = new LinkedHashMap<>();

    @Getter
    private Map<String, String> dividendScheduleStatuses = new LinkedHashMap<>();

    @Getter
    private Map<String, String> dividendScheduleTypes = new LinkedHashMap<>();

    @Getter
    @Setter
    private List<ApplicationCodeTableVO> selectScheduleTypes;

    @Getter
    @Setter
    private CreateDividendScheduleVO createDividendScheduleVO = new CreateDividendScheduleVO();

    @Getter
    private Map<String, String> statusMap = new HashMap<String, String>();
    
    @PostConstruct
    private void init() {
        log.info("DividendScheduleSearchView init start-");
        searchDividendScheduleVO = new SearchDividendScheduleVO();
        searchDividendScheduleVO.setCaseNumber(new CaseNumberVO());
        List<ApplicationCodeTableVO> paymentTypeList
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.DPA.name());
        if (CommonUtils.isNotEmpty(paymentTypeList)) {
            this.paymentTypes.clear();
            paymentTypeList
                .forEach(type -> this.paymentTypes.put(type.getCodeDesc(), String.valueOf(type.getCodeValue())));
        }

        List<ApplicationCodeTableVO> dividendScheduleStatusList
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.DSS.name());
        if (CommonUtils.isNotEmpty(dividendScheduleStatusList)) {
            this.dividendScheduleStatuses.clear();
            dividendScheduleStatusList.forEach(
                type -> this.dividendScheduleStatuses.put(type.getCodeDesc(), String.valueOf(type.getCodeValue())));
        }

        List<ApplicationCodeTableVO> dividendScheduleTypeList
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.DST.name());
        if (CommonUtils.isNotEmpty(dividendScheduleTypeList)) {
            this.dividendScheduleTypes.clear();
            dividendScheduleTypeList.forEach(
                type -> this.dividendScheduleTypes.put(type.getCodeDesc(), String.valueOf(type.getCodeValue())));
            this.selectScheduleTypes = dividendScheduleTypeList.stream()
                .filter(code -> (code.getCodeValue().equals("ADS") || code.getCodeValue().equals("AIS")))
                .collect(Collectors.toList());
        }
        this.createDividendScheduleVO.setCaseNumber(new CaseNumberVO());
        this.createDividendScheduleVO.setScheduleType(new ScheduleTypeVO());
        List<ApplicationCodeTableVO> statusVOs
        = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.DSS.name());
        if (CommonUtils.isNotEmpty(statusVOs)) {
            statusVOs.forEach(item -> {
                statusMap.put(item.getCodeValue(), item.getCodeDesc());
            });
        }
        log.info("DividendScheduleSearchView init end-");
    }

    public void search() {
        log.info("search() start");
        this.caseName = null;
        CaseVO caseVO = caseClientService.findByCaseNumber(searchDividendScheduleVO.getCaseNumber());
        if (caseVO != null) {
            this.caseName = caseVO.getCaseName();
        }
        lazyDataModel = new SimpleLazyDataModel<DividendScheduleVO, SearchDividendScheduleVO>(
            RequestUriConstant.CLIENT_SEARCH_DIVIDEND_SCHEDULE_LIST, searchDividendScheduleVO);
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        this.lazyDataModel = null;
        this.caseName = null;
        this.searchDividendScheduleVO = new SearchDividendScheduleVO();
        this.searchDividendScheduleVO.setCaseNumber(new CaseNumberVO());
        createDividendScheduleVO = new CreateDividendScheduleVO();
        this.createDividendScheduleVO.setCaseNumber(new CaseNumberVO());
        this.createDividendScheduleVO.setScheduleType(new ScheduleTypeVO());
        log.info("reset() end");
    }

    public String toEdit() {
        log.info("toEdit() start");
        boolean validatorResult
            = dividendScheduleClientService.validateCreateDividendSchedule(this.createDividendScheduleVO);
        if (validatorResult) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_CREATE_DIVIDENDSCHEDULE_ERROR), ""));
            log.info("toEdit() end return null");
            return null;
        } else {
            Faces.setFlashAttribute("createDividendScheduleVO", this.createDividendScheduleVO);
        }
        log.info("toEdit() end");
        return EDIT_PAGE;
    }
}
