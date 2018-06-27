package hk.oro.iefas.ws.dividend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.dividend.dto.ApprovedAdjucationResultItemDTO;
import hk.oro.iefas.domain.dividend.dto.CreateDividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDistDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleItemDTO;
import hk.oro.iefas.domain.dividend.dto.SearchDividendScheduleDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
public interface DividendScheduleService {

    public Page<DividendScheduleDTO> searchDividendScheduleList(SearchDTO<SearchDividendScheduleDTO> searchDTO);

    public boolean validateCreateDividendSchedule(CreateDividendScheduleDTO dto);

    public DividendScheduleDTO searchDividendSchedule(Integer divScheduleId);

    public BigDecimal findCredTypePercentageByCredTypeId(Integer creditorTypeId);

    public List<DividendScheduleDistDTO> findByDivScheduleItemId(Integer divScheduleItemId);

    public List<ApprovedAdjucationResultItemDTO> findByCreditor(Integer creditorId);

    public List<CreditorDTO> searchCreditorByCaseId(Integer caseId);

    public BigDecimal searchTotalInterestAmount(Integer creditorId);

    public Integer saveDividendSchedule(DividendScheduleDTO dividendSchedule);

    public DividendScheduleItemDTO searchDividendScheduleItemById(Integer divScheduleItemId);

}
