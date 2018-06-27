package hk.oro.iefas.ws.release.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.core.exceptions.support.ErrorMsg;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.domain.release.dto.HistoricalCaseListDetailDTO;
import hk.oro.iefas.domain.release.dto.HistoricalCaseListResultDTO;
import hk.oro.iefas.domain.release.dto.RelHistListDTO;
import hk.oro.iefas.domain.release.dto.RelHistListItemDTO;
import hk.oro.iefas.domain.release.dto.SearchHistoricalCaseListDTO;
import hk.oro.iefas.domain.release.entity.RelHistList;
import hk.oro.iefas.domain.release.entity.RelHistListItem;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherBasicInfoDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.domain.shroff.entity.VoucherType;
import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.dto.HolidayResultDTO;
import hk.oro.iefas.domain.system.dto.ImportFileDTO;
import hk.oro.iefas.domain.system.dto.ImportHolidayResultDTO;
import hk.oro.iefas.domain.system.dto.SearchHolidayDTO;
import hk.oro.iefas.domain.system.dto.UploadedHolidayDTO;
import hk.oro.iefas.domain.system.entity.Holiday;
import hk.oro.iefas.ws.release.repository.HistoricalCaseListRepository;
import hk.oro.iefas.ws.release.repository.RelHistListItemRepository;
import hk.oro.iefas.ws.release.repository.assembler.HistoricalCaseListDTOAssembler;
import hk.oro.iefas.ws.release.repository.assembler.HistoricalCaseListDetailDTOAssembler;
import hk.oro.iefas.ws.release.repository.predicate.HistoricalCaseListPredicate;
import hk.oro.iefas.ws.release.service.HistoricalCaseListService;
import hk.oro.iefas.ws.release.service.RelHistListItemService;
import hk.oro.iefas.ws.shroff.repository.assembler.ReceiptVoucherDetailDTOAssembler;
import hk.oro.iefas.ws.system.repository.HolidayRepository;
import hk.oro.iefas.ws.system.repository.assembler.HolidayDTOAssembler;
import hk.oro.iefas.ws.system.repository.predicate.HolidayPredicate;
import hk.oro.iefas.ws.system.service.HolidayService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date: 2018-05-18 17:54:15 +0800 (Fri, 18 May 2018) $
 * @author $Author: cwen $
 */
@Slf4j
@Service
public class HistoricalCaseListServiceImpl implements HistoricalCaseListService {

    @Autowired
    private HistoricalCaseListRepository historicalCaseListRepository;
    
    @Autowired
    private RelHistListItemRepository relHistListItemRepository;
    
    @Autowired
    private HistoricalCaseListDetailDTOAssembler historicalCaseListDetailDTOAssembler;
    
    private static final String LINE_END = "\r\n";
    
    private static final String CSV_FILE_EXTENDS = ".csv";
    private static final int CSV_FILE_DESC_MAX_LENGTH = 200;
    private static final int CSV_FILE_SIZE_MAX = 5 * 1024 * 1024 * Byte.BYTES;

    @Override
    public HistoricalCaseListResultDTO searchHistoricalCaseList(SearchHistoricalCaseListDTO searchDto) {
        log.info("searchHistoricalCaseList() start - " + searchDto);
        HistoricalCaseListResultDTO result = new HistoricalCaseListResultDTO();
        List<RelHistListDTO> resultList;
        resultList = historicalCaseListRepository.findAll(HistoricalCaseListDTOAssembler.toDTO(), HistoricalCaseListPredicate.findByCriteria(searchDto));
        result.setResultList(resultList);
        log.info("searchHistoricalCaseList() end - " + result);
        return result;
    }
    
    @Transactional(readOnly = true)
    @Override
    public HistoricalCaseListDetailDTO findHistCaseItem(Integer historicalCaseListId) {
        log.info("findHistCaseItem() start - VoucherId: " + historicalCaseListId);
        RelHistList historicalCaseList = historicalCaseListRepository.findOne(historicalCaseListId);
        List<RelHistListItem> historicalCaseListItems = relHistListItemRepository.findByHistCaseListId(historicalCaseListId);
        HistoricalCaseListDetailDTO dto = historicalCaseListDetailDTOAssembler.toDTO(historicalCaseList, historicalCaseListItems);
        log.info("findHistCaseItem() end - " + dto);
        return dto;
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveHistoricalCaseList(HistoricalCaseListDetailDTO historicalCaseListDetailDTO) {
        log.info("saveHistoricalCaseList() start - " + historicalCaseListDetailDTO);
        RelHistListDTO histCaseListBasicInformation = historicalCaseListDetailDTO.getHistoricalCaseListInformation();
        RelHistList relHistList = DataUtils.copyProperties(histCaseListBasicInformation, RelHistList.class);
        /*VoucherType voucherType = voucherTypeRepository.findByVoucherTypeCode(ShroffConstant.VT_REC);
        voucher.setVoucherType(voucherType);
        voucher = voucherRepository.save(voucher);*/
        Integer historicalCaseListId = relHistList.getHistCaseListId();

        List<RelHistListItemDTO> histCaseListItemList
            = historicalCaseListDetailDTO.getHistoricalCaseListDetailItems();
        if (CommonUtils.isNotEmpty(histCaseListItemList)) {
        	histCaseListItemList.stream().forEach(item -> {
                if (CoreConstant.STATUS_INACTIVE.equals(item.getStatus())) {
                	relHistListItemRepository.deleteItem(item.getHistCaseListItemId(), CoreConstant.STATUS_DELETE);
                } else if (CoreConstant.STATUS_ACTIVE.equals(item.getStatus())) {
                	RelHistListItem histCaseListItem = DataUtils.copyProperties(item, RelHistListItem.class);
                	histCaseListItem.setHistCaseListId(historicalCaseListId);
                    relHistListItemRepository.save(histCaseListItem);
                }
            });
        }

        log.info("saveHistoricalCaseList() end - " + historicalCaseListId);
        return historicalCaseListId;
    }

}
