package hk.oro.iefas.web.system.systemadministration.holiday.view;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.security.vo.RolePrivilegeVO;
import hk.oro.iefas.domain.security.vo.RoleVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeResultVO;
import hk.oro.iefas.domain.system.vo.HolidayVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionAdminClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.DivisionPrivilegeClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.PrivilegeClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.RoleClientService;
import hk.oro.iefas.web.system.divisionadministration.role.service.RolePrivilegeClientService;
import hk.oro.iefas.web.system.systemadministration.holiday.service.HolidayClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class HolidayDetailView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private HolidayClientService holidayClientService;

    @Getter
    @Setter
    private SearchPrivilegeCriteriaVO criteria = new SearchPrivilegeCriteriaVO();

    @Getter
    @Setter
    private HolidayVO holidayVO;

    @Getter
    @Setter
    private Integer holidayId;

    @Getter
    @Setter
    private Date today;

    @PostConstruct
    private void init() {
        log.info("======HolidayDetailView init======");
        today = DateUtils.getCurrentDay();
        holidayId = Faces.getRequestParameter("holidayId", Integer.class);
        if (holidayId != null && holidayId.intValue() > 0) {
            holidayVO = holidayClientService.loadHoliday(holidayId);
        } else {
            holidayVO = new HolidayVO();
        }
    }

    public void saveHoliday() {
        log.info("saveHoliday() start " + this.holidayVO);
        HolidayVO savedHoliday = holidayClientService.saveHoliday(holidayVO);
        this.holidayVO = savedHoliday;
        // Messages.addGlobalInfo("Save Successfully!");
        // Messages.addGlobalError("Save Failed, Please check your inputs.");
        log.info("saveHoliday() end");
    }

}
