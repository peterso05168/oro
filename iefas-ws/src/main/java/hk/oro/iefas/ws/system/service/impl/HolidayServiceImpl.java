package hk.oro.iefas.ws.system.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.core.exceptions.support.ErrorMsg;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.dto.HolidayResultDTO;
import hk.oro.iefas.domain.system.dto.ImportFileDTO;
import hk.oro.iefas.domain.system.dto.ImportHolidayResultDTO;
import hk.oro.iefas.domain.system.dto.SearchHolidayDTO;
import hk.oro.iefas.domain.system.dto.UploadedHolidayDTO;
import hk.oro.iefas.domain.system.entity.Holiday;
import hk.oro.iefas.ws.system.repository.HolidayRepository;
import hk.oro.iefas.ws.system.repository.assembler.HolidayDTOAssembler;
import hk.oro.iefas.ws.system.repository.predicate.HolidayPredicate;
import hk.oro.iefas.ws.system.service.HolidayService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date: 2018-06-08 18:55:10 +0800 (週五, 08 六月 2018) $
 * @author $Author: cwen $
 */
@Slf4j
@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    private static final String LINE_END = "\r\n";

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public HolidayDTO saveHoliday(HolidayDTO holidayDto) {
        log.info("saveHoliday() start - and holidayDto = " + holidayDto);
        HolidayDTO result = null;
        // Do validation
        List<ErrorMsg> errorList = validateSaveHoliday(holidayDto);
        if (errorList.size() > 0) {
            throw new BusinessException("Validate saveHoliday failed.", errorList);
        } else {
            Holiday holiday = DataUtils.copyProperties(holidayDto, Holiday.class);
            Holiday savedHoliday = holidayRepository.save(holiday);
            result = DataUtils.copyProperties(savedHoliday, HolidayDTO.class);
        }
        log.info("saveHoliday end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createHoliday(HolidayDTO holidayDto) {
        log.info("createHoliday() start - and holidayDto = " + holidayDto);
        if (holidayDto != null && holidayDto.getStatus() == null) {
            holidayDto.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        // Do validation
        List<ErrorMsg> errorList = validateAddHoliday(holidayDto);
        if (errorList.size() > 0) {
            throw new BusinessException("Validate createHoliday failed.", errorList);
        } else {
            Holiday holiday = DataUtils.copyProperties(holidayDto, Holiday.class);
            holidayRepository.save(holiday);
        }
        log.info("createHoliday end ");
        return;
    }

    @Override
    @Transactional(readOnly = true)
    public HolidayResultDTO searchHoliday(SearchHolidayDTO searchDto) {
        log.info("searchHoliday() start - " + searchDto);
        HolidayResultDTO result = new HolidayResultDTO();
        List<HolidayDTO> resultList;
        resultList = holidayRepository.findAll(HolidayDTOAssembler.toDTO(), HolidayPredicate.findByCriteria(searchDto));
        result.setResultList(resultList);
        log.info("searchHoliday() end - " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public HolidayDTO loadHoliday(Integer holidayId) {
        log.info("loadHoliday() start - and holidayId = " + holidayId);
        Holiday holiday = holidayRepository.findOne(holidayId);
        HolidayDTO result = HolidayDTOAssembler.toDTO(holiday);
        log.info("loadHoliday end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteHoliday(HolidayDTO holidayDto) {
        log.info("deleteHoliday() start - and holidayDto = " + holidayDto);
        // Do validation
        List<ErrorMsg> errorList = validateDeleteHoliday(holidayDto);
        if (errorList != null && errorList.size() > 0) {
            throw new BusinessException("Validate deleteHoliday failed.", errorList);
        } else {
            if (holidayDto.getHolidayId() != null && holidayDto.getHolidayId() != 0) {
                Holiday holiday = DataUtils.copyProperties(holidayDto, Holiday.class);
                holiday.setStatus(CoreConstant.STATUS_DELETE);
                log.info("holiday = " + holiday);
                holidayRepository.save(holiday);
            }
            log.info("deleteHoliday end ");
        }
        return;
    }

    private List<ErrorMsg> validateSaveHoliday(HolidayDTO holidayDto) {
        List<ErrorMsg> resultList = new ArrayList<>();
        /** validation 1 - Holiday Description should NOT be null **/
        if (holidayDto != null && holidayDto.getHolidayDesc() == null || holidayDto.getHolidayDesc().trim().isEmpty()) {
            ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.DESCRIPTION);
            resultList.add(error1);
        }
        /** validation 2 - Holiday date should be a valid date.(i.e. 32/03/2018 is invalid) **/
        if (holidayDto.getHolidayDate() != null) {
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false);
            cal.setTime(holidayDto.getHolidayDate());
            try {
                cal.getTime();
            } catch (Exception e) {
                ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_INVALID_FORMAT, MsgParamCodeConstant.DATE);
                resultList.add(error2);
            }
        } else {
            /** validation 3 - Holiday Date should not be empty/null **/
            ErrorMsg error3 = new ErrorMsg(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.DATE);
            resultList.add(error3);
        }
        if (holidayDto.getHolidayDate() != null) {
            /** validation 4 - Holiday Date should not duplicated in DB **/
            if (holidayRepository.exists(HolidayPredicate.findByDate(holidayDto))) {
                ErrorMsg error4 = new ErrorMsg(MsgCodeConstant.MSG_DUPLICATE_VALUE, MsgParamCodeConstant.DATE);
                resultList.add(error4);
            }
            /** validation 5 - Holiday Date should not be earlier than today **/
            Date holidayDate = holidayDto.getHolidayDate();
            Date today = DateUtils.getCurrentDay();
            if (!DateUtils.isSameDay(holidayDate, today) && holidayDate.before(today)) {
                ErrorMsg error5 = new ErrorMsg(MsgCodeConstant.MSG_NOT_BEFORE, MsgParamCodeConstant.DATE,
                    MsgParamCodeConstant.TODAY);
                resultList.add(error5);
            }
        }
        return resultList;
    }

    private List<ErrorMsg> validateDeleteHoliday(HolidayDTO holidayDto) {
        List<ErrorMsg> resultList = new ArrayList<>();
        // Holiday status should be active.
        if (holidayDto.getStatus() != null && !holidayDto.getStatus().equals(CoreConstant.STATUS_ACTIVE)) {
            ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_STATUS_NOT_ACTIVE);
            resultList.add(error1);
        }
        return resultList;
    }

    private List<ErrorMsg> validateAddHoliday(HolidayDTO holidayDto) {
        List<ErrorMsg> resultList = new ArrayList<>();
        /** validation 1 - Holiday Description should NOT be null **/
        if (holidayDto != null && holidayDto.getHolidayDesc() == null || holidayDto.getHolidayDesc().trim().isEmpty()) {
            ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.DESCRIPTION);
            resultList.add(error1);
        }
        /** validation 2 - Holiday date should be a valid date.(i.e. 32/03/2018 is invalid) **/
        if (holidayDto.getHolidayDate() != null) {
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false);
            cal.setTime(holidayDto.getHolidayDate());
            try {
                cal.getTime();
            } catch (Exception e) {
                ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_INVALID_FORMAT, MsgParamCodeConstant.DATE);
                resultList.add(error2);
            }
        } else {
            /** validation 3 - Holiday Date should not be empty/null **/
            ErrorMsg error3 = new ErrorMsg(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.DATE);
            resultList.add(error3);
        }
        if (holidayDto.getHolidayDate() != null) {
            /** validation 4 - Holiday Date should not duplicated in DB **/
            if (holidayRepository.exists(HolidayPredicate.findByDate(holidayDto))) {
                ErrorMsg error4 = new ErrorMsg(MsgCodeConstant.MSG_DUPLICATE_VALUE, MsgParamCodeConstant.DATE);
                resultList.add(error4);
            }
            /** validation 5 - Holiday Date should not be earlier than today **/
            Date holidayDate = holidayDto.getHolidayDate();
            Date today = DateUtils.getCurrentDay();
            if (!DateUtils.isSameDay(holidayDate, today) && holidayDate.before(today)) {
                ErrorMsg error5 = new ErrorMsg(MsgCodeConstant.MSG_NOT_BEFORE, MsgParamCodeConstant.DATE,
                    MsgParamCodeConstant.TODAY);
                resultList.add(error5);
            }
        }
        return resultList;
    }

    @Transactional(readOnly = true)
    @Override
    public String downloadHolidayTemplate(String dateFormat) {
        StringBuilder sb = new StringBuilder();
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(LINE_END);
        try {
            csvFilePrinter = new CSVPrinter(sb, csvFileFormat);
            List<String> headerRecords = new ArrayList<String>();
            headerRecords.add("Holiday Date(" + dateFormat + ")");
            headerRecords.add("Holiday Description(No more than 200 characters)");
            csvFilePrinter.printRecord(headerRecords.toArray());
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                csvFilePrinter.flush();
            } catch (Exception oe) {
                log.error(oe.getMessage());
            }
        }
        return sb.toString();
    }

    @Override
    @Transactional(readOnly = true)
    public ImportHolidayResultDTO importListTemplate(ImportFileDTO importFileDTO) throws Exception {
        log.info("importListTemplate() start - and importFileDTO = " + importFileDTO);
        ImportHolidayResultDTO result = new ImportHolidayResultDTO();
        List<ErrorMsg> errorList = validateImportFile(importFileDTO);
        if (errorList != null && errorList.size() > 0) {
            throw new BusinessException("Validate importListTemplate failed.", errorList);
        } else {
            List<UploadedHolidayDTO> resultList = convertImportFile(importFileDTO);
            result.setUploadedHolidayList(resultList);
        }
        log.info("importListTemplate() end - and result = " + result);
        return result;
    }

    private List<ErrorMsg> validateImportFile(ImportFileDTO importFileDTO) {
        // check if holiday date exists in current db
        List<ErrorMsg> errMsgList = new ArrayList<>();
        List<UploadedHolidayDTO> uploadedHolidayList = importFileDTO.getUploadedHolidayList();
        for (UploadedHolidayDTO holiday : uploadedHolidayList) {
            // check if the date exists
            HolidayDTO holidayDto = new HolidayDTO();
            holidayDto.setHolidayDate(holiday.getHolidayDate());
            if (holidayRepository.exists(HolidayPredicate.findByDate(holidayDto))) {
                ErrorMsg error = new ErrorMsg(MsgCodeConstant.MSG_DUPLICATE_VALUE, MsgParamCodeConstant.DATE);
                errMsgList.add(error);
            }
        }
        return errMsgList;

    }

    private List<UploadedHolidayDTO> convertImportFile(ImportFileDTO importFileDTO) throws Exception {
        return importFileDTO.getUploadedHolidayList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int confirmUploadRecord(ImportHolidayResultDTO importHolidayResultDTO) {
        log.info("confirmUploadRecord() start - " + importHolidayResultDTO);
        int result = 0;
        List<Holiday> holidayList = new ArrayList<>();
        for (UploadedHolidayDTO uploadedHoliday : importHolidayResultDTO.getUploadedHolidayList()) {
            Holiday holiday = new Holiday();
            holiday.setHolidayDate(uploadedHoliday.getHolidayDate());
            holiday.setHolidayDesc(uploadedHoliday.getHolidayDesc());
            holiday.setStatus(CoreConstant.STATUS_ACTIVE);
            holidayList.add(holiday);
        }
        List<Holiday> savedHolidayList = holidayRepository.save(holidayList);
        if (savedHolidayList != null) {
            result = savedHolidayList.size();
        }
        log.info("confirmUploadRecord() end ");
        return result;
    }
}
