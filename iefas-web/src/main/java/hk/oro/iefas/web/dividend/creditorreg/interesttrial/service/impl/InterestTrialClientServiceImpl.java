package hk.oro.iefas.web.dividend.creditorreg.interesttrial.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.dividend.vo.CaseInterestTrialVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleItemVO;
import hk.oro.iefas.domain.dividend.vo.InterestTrialAdjudicationVO;
import hk.oro.iefas.domain.dividend.vo.SearchInterestTrialCriteriaVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.creditorreg.interesttrial.service.InterestTrialClientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
public class InterestTrialClientServiceImpl extends BaseClientService implements InterestTrialClientService {

    @Override
    public CaseInterestTrialVO searchInterestTrialList(SearchInterestTrialCriteriaVO criteria) {
        log.info("searchInterestTrialList - start and criteria =" + criteria);
        CaseInterestTrialVO result = null;
        ResponseEntity<CaseInterestTrialVO> response = this
            .postForEntity(RequestUriConstant.CLIENT_URI_SEARCH_INTERESTTRIALLIST, criteria, CaseInterestTrialVO.class);
        result = response.getBody();
        log.info("searchInterestTrialList - end and result =" + result);
        return result;
    }

    @Override
    public InterestTrialAdjudicationVO searchInterestTrialById(Integer interestTrialAdjudicationId) {
        log.info("searchInterestTrial - start and interestTrialAdjudicationId =" + interestTrialAdjudicationId);
        InterestTrialAdjudicationVO result = null;
        ResponseEntity<InterestTrialAdjudicationVO> response
            = this.postForEntity(RequestUriConstant.CLIENT_URI_SEARCH_INTERESTTRIAL_BY_ID, interestTrialAdjudicationId,
                InterestTrialAdjudicationVO.class);
        result = response.getBody();
        log.info("searchInterestTrial - end and result =" + result);
        return result;
    }

    @Override
    public Integer saveInterestTrial(InterestTrialAdjudicationVO interestTrialAdjudication) {
        log.info("saveInterestTrial - start and interestTrialAdjudication =" + interestTrialAdjudication);
        Integer result = null;
        ResponseEntity<Integer> response = this.postForEntity(RequestUriConstant.CLIENT_URI_SAVE_INTERESTTRIAL,
            interestTrialAdjudication, Integer.class);
        result = response.getBody();
        log.info("saveInterestTrial - end and result =" + result);
        return result;
    }

    @Override
    public InterestTrialAdjudicationVO createInterestTrial(Integer creditorId) {
        log.info("createInterestTrial - start and creditorId =" + creditorId);
        InterestTrialAdjudicationVO result = null;
        ResponseEntity<InterestTrialAdjudicationVO> response = this.postForEntity(
            RequestUriConstant.CLIENT_URI_CREATE_INTERESTTRIAL, creditorId, InterestTrialAdjudicationVO.class);
        result = response.getBody();
        log.info("createInterestTrial - end and result =" + result);
        return result;
    }

    @Override
    public List<CreditorVO> searchCreditorByCaseNumber(CaseNumberVO caseNumber) {
        log.info("searchCreditorByCaseNumber start - caseNumber - " + caseNumber);
        HttpEntity<CaseNumberVO> entity = new HttpEntity<CaseNumberVO>(caseNumber);
        ResponseEntity<List<CreditorVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_SEARCH_CREDITOR_BY_CASENUMBER, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<CreditorVO>>() {});
        List<CreditorVO> body = responseEntity.getBody();
        log.info("searchCreditorByCaseNumber end - " + body);
        return body;
    }

    @Override
    public List<DividendScheduleItemVO> searchDivScheduleItemByAdjResultId(Integer adjResultId) {
        log.info("searchDivScheduleItemByAdjResultId - start and adjResultId =" + adjResultId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(adjResultId);
        ResponseEntity<List<DividendScheduleItemVO>> response
            = this.exchange(RequestUriConstant.CLIENT_URI_SEARCH_DIVSHCEDULEITEM_BY_ADJRESULTID, HttpMethod.POST,
                entity, new ParameterizedTypeReference<List<DividendScheduleItemVO>>() {});
        List<DividendScheduleItemVO> result = response.getBody();
        log.info("searchDivScheduleItemByAdjResultId - end and result = " + result);
        return result;
    }
}
