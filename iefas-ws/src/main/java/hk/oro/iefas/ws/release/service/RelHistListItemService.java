package hk.oro.iefas.ws.release.service;

import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.dto.HolidayResultDTO;
import hk.oro.iefas.domain.system.dto.ImportFileDTO;
import hk.oro.iefas.domain.system.dto.ImportHolidayResultDTO;
import hk.oro.iefas.domain.system.dto.SearchHolidayDTO;

public interface RelHistListItemService {

    HolidayDTO saveHoliday(HolidayDTO holidayDTO);

    void createHoliday(HolidayDTO holidayDTO);

    HolidayResultDTO searchHoliday(SearchHolidayDTO searchDto);

    HolidayDTO loadHoliday(Integer holidayId);

    void deleteHoliday(HolidayDTO holidayDTO);

	String downloadHolidayTemplate(String dateFormat);

	ImportHolidayResultDTO importListTemplate(ImportFileDTO importFileDTO) throws Exception;

	int confirmUploadRecord(ImportHolidayResultDTO importHolidayResultDTO);
}
