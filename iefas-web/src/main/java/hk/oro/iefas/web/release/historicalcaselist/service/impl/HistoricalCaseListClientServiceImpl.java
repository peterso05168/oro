package hk.oro.iefas.web.release.historicalcaselist.service.impl;

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
import hk.oro.iefas.domain.release.vo.HistoricalCaseListDetailVO;
import hk.oro.iefas.domain.release.vo.HistoricalCaseListResultVO;
import hk.oro.iefas.domain.release.vo.RelHistListVO;
import hk.oro.iefas.domain.release.vo.SearchHistoricalCaseListVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherDetailVO;
import hk.oro.iefas.domain.system.vo.HolidayResultVO;
import hk.oro.iefas.domain.system.vo.HolidayVO;
import hk.oro.iefas.domain.system.vo.ImportFileVO;
import hk.oro.iefas.domain.system.vo.ImportHolidayResultVO;
import hk.oro.iefas.domain.system.vo.SearchHolidayVO;
import hk.oro.iefas.domain.system.vo.UploadedHolidayVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.release.historicalcaselist.service.HistoricalCaseListClientService;
import hk.oro.iefas.web.system.systemadministration.holiday.service.HolidayClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2487 $ $Date: 2018-05-18 17:54:15 +0800 (Fri, 18 May 2018) $
 * @author $Author: cwen $
 */
@Slf4j
@Named
public class HistoricalCaseListClientServiceImpl extends BaseClientService implements HistoricalCaseListClientService {

    @Autowired
    private AppResourceUtils appResourceUtils;
    
    private static final String CSV_FILE_EXTENDS = ".csv";
    private static final int CSV_FILE_DESC_MAX_LENGTH = 200;
    private static final int CSV_FILE_SIZE_MAX = 5 * 1024 * 1024 * Byte.BYTES;

    @Override
    public HistoricalCaseListDetailVO findHistCaseListDetail(Integer histCaseListId) {
        log.info("findHistCaseListDetail() start - histCaseListId: " + histCaseListId);
        ResponseEntity<HistoricalCaseListDetailVO> responseEntity = super.postForEntity(
            RequestUriConstant.CLIENT_FIND_HISTORICAL_CASE_LIST_ITEM, histCaseListId, HistoricalCaseListDetailVO.class);
        HistoricalCaseListDetailVO historicalCaseListDetailVO = responseEntity.getBody();
        log.info("findHistCaseListDetail() end - " + historicalCaseListDetailVO);
        return historicalCaseListDetailVO;
    }
    
    @Override
    public Integer saveHistoricalCaseList(HistoricalCaseListDetailVO historicalCaseListDetailVO) {
        log.info("saveHistoricalCaseList() start - " + historicalCaseListDetailVO);
        ResponseEntity<Integer> responseEntity = super.postForEntity(
            RequestUriConstant.CLIENT_SAVE_HISTORICAL_CASE_LIST_ITEM, historicalCaseListDetailVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveHistoricalCaseList() end HistoricalCaseListId: " + body);
        return body;
    }

    @Override
    public HistoricalCaseListResultVO searchHistoricalCaseList(SearchHistoricalCaseListVO searchDto) {
        log.info("findByCriteria() start - " + searchDto);
        HistoricalCaseListResultVO result
            = postForObject(RequestUriConstant.CLIENT_SEARCH_HISTORICAL_CASE_LIST, searchDto, HistoricalCaseListResultVO.class);
        List<RelHistListVO> historicalCaseList = result.getResultList();
        /*Date today = DateUtils.getCurrentDay();
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
        }*/
        log.info("findByCriteria() end - " + historicalCaseList);
        return result;
    }

}
