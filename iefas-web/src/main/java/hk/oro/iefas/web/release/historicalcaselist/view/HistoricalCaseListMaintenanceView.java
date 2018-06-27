package hk.oro.iefas.web.release.historicalcaselist.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.common.vo.StatusVO;
import hk.oro.iefas.domain.release.vo.RelHistListVO;
import hk.oro.iefas.domain.release.vo.SearchHistoricalCaseListVO;
import hk.oro.iefas.domain.system.vo.HolidayVO;
import hk.oro.iefas.domain.system.vo.ImportHolidayResultVO;
import hk.oro.iefas.domain.system.vo.SearchHolidayVO;
import hk.oro.iefas.web.common.service.CommonConstantClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.release.historicalcaselist.service.HistoricalCaseListClientService;
import hk.oro.iefas.web.system.systemadministration.holiday.service.HolidayClientService;
import hk.oro.iefas.web.system.systemadministration.holiday.view.HolidayLazyDataModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2487 $ $Date: 2018-05-18 17:54:15 +0800 (Fri, 18 May 2018) $
 * @author $Author: cwen $
 */
@Slf4j
@Named
@ViewScoped
public class HistoricalCaseListMaintenanceView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private HistoricalCaseListClientService historicalCaseListClientService;
    
    @Inject
    private CommonConstantClientService commonConstantClientService;
    
    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private SearchHistoricalCaseListVO criteria = new SearchHistoricalCaseListVO();

    @Getter
    @Setter
    private HolidayVO addHolidayVO;

    @Getter
    @Setter
    private LazyDataModel<RelHistListVO> historicalCaseListResultDataModel;

    @Getter
    @Setter
    private Integer holidayId;

    @Getter
    @Setter
    private Date today;
    
    @Getter
    @Setter
    private String dateFormat;
    
    @Setter
    @Getter
    private UploadedFile uploadedFile;
    
    @Setter
    @Getter
    private ImportHolidayResultVO uploadResultVo;
    
    @Getter
    @Setter
    private List<StatusVO> statusVOs;
    
    @PostConstruct
    private void init() {
        log.info("====== HistoricalCaseListMaintenanceView init ======");
        statusVOs = commonConstantClientService.searchStatusList();
        today = DateUtils.getCurrentDay();
        //addHolidayVO = new HolidayVO();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        criteria.setListDate(today);
        dateFormat = appResourceUtils.getDateFormat();
    }

    public void searchHistoricalCaseList() {
        log.info("search() start");
        log.info("Criteria: " + criteria);
        if (historicalCaseListResultDataModel == null) {
        	historicalCaseListResultDataModel = new HistoricalCaseListLazyDataModel(criteria, historicalCaseListClientService);
        }
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        criteria = new SearchHistoricalCaseListVO();
        historicalCaseListResultDataModel = null;
        log.info("reset() end");
    }
}
