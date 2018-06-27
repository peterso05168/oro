package hk.oro.iefas.web.dividend.dividendprocess.dividendschedule.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.dividend.vo.ApprovedAdjucationResultItemVO;
import hk.oro.iefas.domain.dividend.vo.CreateDividendScheduleVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleItemVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.dividendprocess.dividendschedule.service.DividendScheduleClientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
public class DividendScheduleClientServiceImpl extends BaseClientService implements DividendScheduleClientService {

    @Override
    public Boolean validateCreateDividendSchedule(CreateDividendScheduleVO createDividendScheduleVO) {
        log.info("validateCreateDividendSchedule start - param: " + createDividendScheduleVO);
        Boolean body = this.postForObject(RequestUriConstant.CLIENT_VALIDATE_CREATE_DIVIDEND_SCHEDULE,
            createDividendScheduleVO, Boolean.class);
        log.info("validateCreateDividendSchedule end - return: " + body);
        return body;
    }

    @Override
    public DividendScheduleVO searchDividendSchedule(Integer dividendScheduleId) {
        log.info("searchDividendSchedule start - param: " + dividendScheduleId);
        ResponseEntity<DividendScheduleVO> response = this.postForEntity(
            RequestUriConstant.CLIENT_SEARCH_DIVIDEND_SCHEDULE, dividendScheduleId, DividendScheduleVO.class);
        DividendScheduleVO result = response.getBody();
        log.info("searchDividendSchedule end - return: " + result);
        return result;
    }

    @Override
    public List<ApprovedAdjucationResultItemVO> findByCreditor(Integer creditorId) {
        log.info("findByCreditor start - creditorId: " + creditorId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(creditorId);
        ResponseEntity<List<ApprovedAdjucationResultItemVO>> response
            = this.exchange(RequestUriConstant.CLIENT_FIND_BY_CREDITOR_ID, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<ApprovedAdjucationResultItemVO>>() {});
        List<ApprovedAdjucationResultItemVO> result = response.getBody();
        log.info("findByCreditor end - return: " + creditorId);
        return result;
    }

    @Override
    public List<CreditorVO> searchCreditorByCaseId(Integer caseId) {
        log.info("searchCreditorByCaseId start - caseId: " + caseId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(caseId);
        ResponseEntity<List<CreditorVO>> response = this.exchange(RequestUriConstant.CLIENT_SEARCH_CREDITOR_BY_CASEID,
            HttpMethod.POST, entity, new ParameterizedTypeReference<List<CreditorVO>>() {});
        List<CreditorVO> result = response.getBody();
        log.info("searchCreditorByCaseId end - return: result =" + result);
        return result;
    }

    @Override
    public BigDecimal searchTotalInterestAmount(Integer creditorId) {
        log.info("searchTotalInterestAmount start - and creditorId =" + creditorId);
        ResponseEntity<BigDecimal> response
            = this.postForEntity(RequestUriConstant.CLIENT_SEARCH_TOTALINTERESTAMOUNT, creditorId, BigDecimal.class);
        BigDecimal result = response.getBody();
        log.info("searchTotalInterestAmount end - return: result =" + result);
        return result;
    }

    @Override
    public DividendScheduleItemVO searchDividendScheduleItemById(Integer divScheduleId) {
        log.info("searchDividendScheduleItemById start - and divScheduleId =" + divScheduleId);
        ResponseEntity<DividendScheduleItemVO> response = this.postForEntity(
            RequestUriConstant.CLIENT_SEARCH_DIVSCHEDULEITEM_BY_ID, divScheduleId, DividendScheduleItemVO.class);
        DividendScheduleItemVO result = response.getBody();
        log.info("searchDividendScheduleItemById end - return: result =" + result);
        return result;
    }

    @Override
    public Integer saveDividendSchedule(DividendScheduleVO dividendScheduleDTO) {
        log.info("saveDividendSchedule start - and dividendScheduleDTO =" + dividendScheduleDTO);
        ResponseEntity<Integer> response
            = this.postForEntity(RequestUriConstant.CLIENT_SAVE_DIVIDENDSCHEDULE, dividendScheduleDTO, Integer.class);
        Integer result = response.getBody();
        log.info("saveDividendSchedule end - return: result =" + result);
        return result;
    }

    @Override
    public BigDecimal findCredTypePercentageByCredTypeId(Integer credTypeId) {
        log.info("findCredTypePercentageByCredTypeId start - and credTypeId =" + credTypeId);
        ResponseEntity<BigDecimal> response
            = this.postForEntity(RequestUriConstant.CLIENT_SEARCH_BY_CREDTYPEID, credTypeId, BigDecimal.class);
        BigDecimal result = response.getBody();
        log.info("findCredTypePercentageByCredTypeId end - return: result =" + result);
        return result;
    }

}
