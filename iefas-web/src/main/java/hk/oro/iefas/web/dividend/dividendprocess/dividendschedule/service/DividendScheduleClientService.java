package hk.oro.iefas.web.dividend.dividendprocess.dividendschedule.service;

import java.math.BigDecimal;
import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.dividend.vo.ApprovedAdjucationResultItemVO;
import hk.oro.iefas.domain.dividend.vo.CreateDividendScheduleVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleItemVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleVO;

public interface DividendScheduleClientService {

    public Boolean validateCreateDividendSchedule(CreateDividendScheduleVO createDividendScheduleVO);

    public DividendScheduleVO searchDividendSchedule(Integer dividendScheduleId);

    public List<ApprovedAdjucationResultItemVO> findByCreditor(Integer creditorId);

    public List<CreditorVO> searchCreditorByCaseId(Integer caseId);

    public BigDecimal searchTotalInterestAmount(Integer creditorId);

    public DividendScheduleItemVO searchDividendScheduleItemById(Integer divScheduleId);

    public Integer saveDividendSchedule(DividendScheduleVO dividendScheduleDTO);

    public BigDecimal findCredTypePercentageByCredTypeId(Integer credTypeId);

}
