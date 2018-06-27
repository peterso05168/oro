package hk.oro.iefas.web.system.systemadministration.holiday.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.system.vo.HolidayVO;
import hk.oro.iefas.domain.system.vo.ImportHolidayResultVO;
import hk.oro.iefas.domain.system.vo.SearchHolidayVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.systemadministration.holiday.service.HolidayClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2570 $ $Date: 2018-05-24 14:35:00 +0800 (週四, 24 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class HolidayMaintenanceView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private HolidayClientService holidayClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private SearchHolidayVO criteria = new SearchHolidayVO();

    @Getter
    @Setter
    private HolidayVO addHolidayVO;

    @Getter
    @Setter
    private LazyDataModel<HolidayVO> holidayResultDataModel;

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

    @PostConstruct
    private void init() {
        log.info("====== HolidayMaintenanceView init ======");
        today = DateUtils.getCurrentDay();
        addHolidayVO = new HolidayVO();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        criteria.setYear(cal.get(Calendar.YEAR));
        dateFormat = appResourceUtils.getDateFormat();
    }

    public void searchHoliday() {
        log.info("search() start");
        log.info("Criteria: " + criteria);
        if (holidayResultDataModel == null) {
            holidayResultDataModel = new HolidayLazyDataModel(criteria, holidayClientService);
        }
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        criteria = new SearchHolidayVO();
        holidayResultDataModel = null;
        log.info("reset() end");
    }

    public void deleteHoliday() {
        log.info("deleteHoliday() Start");
        log.info("holidayId: " + holidayId);
        if (holidayId != null && holidayId.intValue() > 0) {
            HolidayVO holidayVO = holidayClientService.loadHoliday(holidayId);
            if (holidayVO != null) {
                holidayClientService.deleteHoliday(holidayVO);
                log.info("deleteHoliday() End");
                return;
            }
        }
        Messages.addGlobalError("Delete Failed!");
        log.info("deleteHoliday() End");
    }

    public void createHoliday() {
        log.info("createHoliday() start");
        log.info("addHolidayVO: " + addHolidayVO);
        boolean saveSuccess = holidayClientService.createHoliday(addHolidayVO);
        if (saveSuccess) {
            RequestContext.getCurrentInstance().execute("PF('addDialog').hide()");
            RequestContext.getCurrentInstance().update("searchForm");
        } else {
            RequestContext.getCurrentInstance().update("addForm");
        }
        log.info("createHoliday() end");
    }

    // Use <p:resrtInput /> instead
    // @Deprecated
    public void createHolidayVo() {
        log.info("createHolidayVo() start");
        addHolidayVO = new HolidayVO();
        log.info("createHolidayVo() end");
    }

    public void convertYear() {
        Integer input = criteria.getYear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.getCurrentDate());
        int thisYear = cal.get(Calendar.YEAR) - 2000;
        if (input != null) {
            int inputYear = Integer.valueOf(input);
            if (inputYear < 100 && inputYear > 80) {
                criteria.setYear(1900 + inputYear);
            } else if (inputYear <= thisYear) {
                criteria.setYear(2000 + inputYear);
            }
        }
        return;
    }

    public void uploadHolidayTemplate() {
        if (uploadedFile == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a file", null));
            return;
        } else {
            uploadResultVo = holidayClientService.importListTemplate(uploadedFile);
            if (uploadResultVo != null && !uploadResultVo.getUploadedHolidayList().isEmpty()) {
                RequestContext.getCurrentInstance().execute("PF('holidayImportDialog').hide()");
                RequestContext.getCurrentInstance().execute("PF('importResultDialog').show()");
                // RequestContext.getCurrentInstance().update("importResultForm");
            }
        }
        return;
    }

    public void confirmUploadRecord() {
        int result = holidayClientService.confirmUploadRecord(uploadResultVo);
        if (result > 0) {
            RequestContext.getCurrentInstance().execute("PF('importResultDialog').hide()");
        }
    }

    public void initUpload() {
        RequestContext.getCurrentInstance().update("importHolidayForm");
    }

    public void cancelUpload() {
        RequestContext.getCurrentInstance().execute("PF('importResultDialog').hide()");
        RequestContext.getCurrentInstance().execute("PF('holidayImportDialog').show()");
    }
}
