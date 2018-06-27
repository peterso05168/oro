package hk.oro.iefas.ws.release.service;

import hk.oro.iefas.domain.release.dto.HistoricalCaseListDetailDTO;
import hk.oro.iefas.domain.release.dto.HistoricalCaseListResultDTO;
import hk.oro.iefas.domain.release.dto.SearchHistoricalCaseListDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherDetailDTO;
import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.dto.HolidayResultDTO;
import hk.oro.iefas.domain.system.dto.ImportFileDTO;
import hk.oro.iefas.domain.system.dto.ImportHolidayResultDTO;
import hk.oro.iefas.domain.system.dto.SearchHolidayDTO;

public interface HistoricalCaseListService {

    HistoricalCaseListResultDTO searchHistoricalCaseList(SearchHistoricalCaseListDTO searchDto);
    
    HistoricalCaseListDetailDTO findHistCaseItem(Integer historicalCaseListId);
    
    Integer saveHistoricalCaseList(HistoricalCaseListDetailDTO historicalCaseListDetailDTO);
    
}
