package hk.oro.iefas.web.system.systemadministration.holiday.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.omnifaces.util.Messages;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.core.exceptions.support.ErrorMsg;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.system.vo.HolidayResultVO;
import hk.oro.iefas.domain.system.vo.HolidayVO;
import hk.oro.iefas.domain.system.vo.ImportFileVO;
import hk.oro.iefas.domain.system.vo.ImportHolidayResultVO;
import hk.oro.iefas.domain.system.vo.SearchHolidayVO;
import hk.oro.iefas.domain.system.vo.UploadedHolidayVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.systemadministration.holiday.service.HolidayClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class HolidayClientServiceImpl extends BaseClientService implements HolidayClientService {

    @Autowired
    private AppResourceUtils appResourceUtils;

    private static final String CSV_FILE_EXTENDS = ".csv";
    private static final int CSV_FILE_DESC_MAX_LENGTH = 200;
    private static final int CSV_FILE_SIZE_MAX = 5 * 1024 * 1024 * Byte.BYTES;

    @Override
    public HolidayVO loadHoliday(Integer holidayId) {
        log.info("findOne() start - holidayId: " + holidayId);
        ResponseEntity<HolidayVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_HOLIDAY_LOAD, holidayId, HolidayVO.class);
        HolidayVO result = responseEntity.getBody();
        Date today = DateUtils.getCurrentDay();
        if (result.getHolidayDate().before(today)) {
            result.setExpireIndc(true);
            if (DateUtils.isSameDay(result.getHolidayDate(), today)) {
                result.setExpireIndc(false);
            }
        } else {
            result.setExpireIndc(false);
        }
        log.info("findOne() end - " + result);
        return result;
    }

    @Override
    public HolidayVO saveHoliday(HolidayVO holidayVO) {
        log.info("saveHoliday() start - " + holidayVO);
        HolidayVO result = holidayVO;
        try {
            ResponseEntity<HolidayVO> responseEntity
                = postForEntity(RequestUriConstant.CLIENT_URI_HOLIDAY_SAVE, holidayVO, HolidayVO.class);
            result = responseEntity.getBody();
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        } catch (BusinessException e) {
            List<ErrorMsg> errors = e.getErrors();
            for (ErrorMsg error : errors) {
                Messages.addGlobalError(appResourceUtils.getMessage(error.getCode(), error.getFields()));
            }
        } catch (Exception e) {
            Messages.addGlobalError("System Error");
        }
        log.info("saveHoliday() end - result = " + result);
        return result;
    }

    @Override
    public boolean createHoliday(HolidayVO holidayVO) {
        log.info("addHoliday() start - " + holidayVO);
        try {
            postForEntity(RequestUriConstant.CLIENT_URI_HOLIDAY_CREATE, holidayVO, Integer.class);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
            return true;
        } catch (BusinessException e) {
            List<ErrorMsg> errors = e.getErrors();
            for (ErrorMsg error : errors) {
                Messages.addGlobalError(appResourceUtils.getMessage(error.getCode(), error.getFields()));
            }
        } catch (Exception e) {
            Messages.addGlobalError("System Error");
        }
        log.info("addHoliday() end");
        return false;
    }

    @Override
    public HolidayResultVO searchHoliday(SearchHolidayVO searchDto) {
        log.info("findByCriteria() start - " + searchDto);
        HolidayResultVO result
            = postForObject(RequestUriConstant.CLIENT_URI_HOLIDAY_LIST, searchDto, HolidayResultVO.class);
        List<HolidayVO> holidayList = result.getResultList();
        Date today = DateUtils.getCurrentDay();
        if (holidayList != null && holidayList.size() > 0) {
            for (HolidayVO holiday : holidayList) {
                if (holiday.getHolidayDate().before(today)) {
                    holiday.setExpireIndc(true);
                    if (DateUtils.isSameDay(holiday.getHolidayDate(), today)) {
                        holiday.setExpireIndc(false);
                    }
                } else {
                    holiday.setExpireIndc(false);
                }
            }
        }
        log.info("findByCriteria() end - " + holidayList);
        return result;
    }

    @Override
    public void deleteHoliday(HolidayVO holidayVO) {
        log.info("delete() start - " + holidayVO);
        try {
            postForEntity(RequestUriConstant.CLIENT_URI_HOLIDAY_DELETE, holidayVO, Integer.class);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_DELETE_SUCCESS));
        } catch (BusinessException e) {
            List<ErrorMsg> errors = e.getErrors();
            for (ErrorMsg error : errors) {
                Messages.addGlobalError(appResourceUtils.getMessage(error.getCode(), error.getFields()));
            }
        } catch (Exception e) {
            Messages.addGlobalError("System Error");
        }
        log.info("delete() end ");
        return;
    }

    @Override
    public String downloadHolidayTemplate() {
        log.info("downloadHolidayTemplate() start ");
        String result = this.getForObject(RequestUriConstant.CLIENT_URI_HOLIDAY_DOWNLOAD, String.class);
        log.info("downloadHolidayTemplate() end ");
        return result;
    }

    @Override
    public ImportHolidayResultVO importListTemplate(UploadedFile uploadedFile) {
        log.info("importListTemplate() start ");
        ImportFileVO importFileVO = new ImportFileVO();
        ImportHolidayResultVO result = null;
        List<ErrorMsg> errorList = new ArrayList<>();
        List<UploadedHolidayVO> uploadedHolidayList = new ArrayList<>();
        InputStream inputstream = null;
        int fileSize = 0;
        try {
            inputstream = uploadedFile.getInputstream();
            fileSize = inputstream.available();
        } catch (IOException e3) {
            ErrorMsg error = new ErrorMsg(MsgCodeConstant.MSG_SAVE_FAIL);
            errorList.add(error);
        }
        // Check upload file is not empty.
        if (fileSize > 0) {
            String fileName = uploadedFile.getFileName();
            // Check upload file type = .csv
            if (!fileName.endsWith(CSV_FILE_EXTENDS)) {
                ErrorMsg error1 = new ErrorMsg(MsgCodeConstant.MSG_INVALID_FORMAT, MsgParamCodeConstant.FILE);
                errorList.add(error1);
            }
            // Check upload file size < 5mb
            if (uploadedFile.getSize() > CSV_FILE_SIZE_MAX) {
                ErrorMsg error2 = new ErrorMsg(MsgCodeConstant.MSG_FILE_TOO_LARGE);
                errorList.add(error2);
            }

            BufferedReader reader = null;
            CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
            reader = new BufferedReader(new InputStreamReader(inputstream));
            Iterable<CSVRecord> records = null;
            try {
                records = format.parse(reader);
            } catch (IOException e1) {
                ErrorMsg error3 = new ErrorMsg(MsgCodeConstant.MSG_SAVE_FAIL);
                errorList.add(error3);
            }
            int recordCount = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            for (CSVRecord record : records) {
                recordCount++;
                String holidayDateStr = record.get(0);
                String holidayDesc = record.get(1);
                Date holidayDate = null;
                try {
                    // check the date is valid date
                    holidayDate = sdf.parse(holidayDateStr);
                } catch (Exception e) {
                    ErrorMsg error4 = new ErrorMsg(MsgCodeConstant.MSG_INVALID_FORMAT, MsgParamCodeConstant.DATE);
                    errorList.add(error4);
                }
                // check the holiday description is <= 200 character
                if (holidayDesc.length() > CSV_FILE_DESC_MAX_LENGTH) {
                    ErrorMsg error5 = new ErrorMsg(MsgCodeConstant.MSG_OUT_OF_RANGE, MsgParamCodeConstant.DESCRIPTION);
                    errorList.add(error5);
                }
                UploadedHolidayVO uploadedHolidayVO = new UploadedHolidayVO();
                uploadedHolidayVO.setHolidayDate(holidayDate);
                uploadedHolidayVO.setHolidayDesc(holidayDesc);
                uploadedHolidayList.add(uploadedHolidayVO);
            }
        } else {
            ErrorMsg error6 = new ErrorMsg(MsgCodeConstant.MSG_MUST_SELECT, MsgParamCodeConstant.FILE);
            errorList.add(error6);
        }
        if (errorList != null && errorList.size() > 0) {
            for (ErrorMsg error : errorList) {
                Messages.addGlobalError(appResourceUtils.getMessage(error.getCode(), error.getFields()));
            }
        } else {
            importFileVO.setUploadedHolidayList(uploadedHolidayList);
            try {
                result = this.postForObject(RequestUriConstant.CLIENT_URI_HOLIDAY_IMPORT_TEMPLATE, importFileVO,
                    ImportHolidayResultVO.class);
                // Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SUBMIT_SUCCESS));
            } catch (BusinessException e) {
                List<ErrorMsg> errors = e.getErrors();
                for (ErrorMsg error : errors) {
                    Messages.addGlobalError(appResourceUtils.getMessage(error.getCode(), error.getFields()));
                }
            } catch (Exception e) {
                Messages.addGlobalError("System Error");
            }
        }
        log.info("importListTemplate() end ");
        return result;
    }

    @Override
    public int confirmUploadRecord(ImportHolidayResultVO importHolidayResultVO) {
        int result = 0;
        try {
            result = this.postForObject(RequestUriConstant.CLIENT_URI_HOLIDAY_CONFIRM_UPLOAD, importHolidayResultVO,
                Integer.class);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_CONFIRM_SUCCESS));
        } catch (Exception e) {
            log.error("", e);
            Messages.addGlobalError("System Error");
        }
        return result;
    }

}
