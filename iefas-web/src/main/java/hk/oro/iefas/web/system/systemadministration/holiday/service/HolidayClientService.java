package hk.oro.iefas.web.system.systemadministration.holiday.service;

import org.primefaces.model.UploadedFile;

import hk.oro.iefas.domain.system.vo.HolidayResultVO;
import hk.oro.iefas.domain.system.vo.HolidayVO;
import hk.oro.iefas.domain.system.vo.ImportFileVO;
import hk.oro.iefas.domain.system.vo.ImportHolidayResultVO;
import hk.oro.iefas.domain.system.vo.SearchHolidayVO;

public interface HolidayClientService {

    HolidayVO loadHoliday(Integer holidayId);

    HolidayVO saveHoliday(HolidayVO holidayVO);

    boolean createHoliday(HolidayVO holidayVO);

    void deleteHoliday(HolidayVO holidayVO);

    HolidayResultVO searchHoliday(SearchHolidayVO criteria);

    String downloadHolidayTemplate();

    ImportHolidayResultVO importListTemplate(UploadedFile uploadedFile);

    int confirmUploadRecord(ImportHolidayResultVO importHolidayResultVO);

}
