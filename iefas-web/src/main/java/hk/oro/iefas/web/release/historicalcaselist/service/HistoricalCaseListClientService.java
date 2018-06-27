package hk.oro.iefas.web.release.historicalcaselist.service;

import org.primefaces.model.UploadedFile;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.release.vo.HistoricalCaseListDetailVO;
import hk.oro.iefas.domain.release.vo.HistoricalCaseListResultVO;
import hk.oro.iefas.domain.release.vo.SearchHistoricalCaseListVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherDetailVO;
import hk.oro.iefas.domain.system.vo.HolidayResultVO;
import hk.oro.iefas.domain.system.vo.HolidayVO;
import hk.oro.iefas.domain.system.vo.ImportFileVO;
import hk.oro.iefas.domain.system.vo.ImportHolidayResultVO;
import hk.oro.iefas.domain.system.vo.SearchHolidayVO;

public interface HistoricalCaseListClientService {

	HistoricalCaseListDetailVO findHistCaseListDetail(Integer histCaseListId);
	
	Integer saveHistoricalCaseList(HistoricalCaseListDetailVO historicalCaseListDetailVO);

    HistoricalCaseListResultVO searchHistoricalCaseList(SearchHistoricalCaseListVO criteria);

}
