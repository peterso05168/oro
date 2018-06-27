package hk.oro.iefas.web.casemgt.casedetailenquiry.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CaseAccountInfoVO;
import hk.oro.iefas.domain.shroff.vo.SearchOldCaseAccountCriteriaVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseAccountClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Named
public class CaseAccountClientServiceImpl extends BaseClientService implements CaseAccountClientService {

    @Override
    public CaseAccountInfoVO findOne(Integer caseAccountId) {
        log.info("findOne() start - and depositCardId = " + caseAccountId);
        ResponseEntity<CaseAccountInfoVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_CASE_ACCOUNT_DETAIL, caseAccountId, CaseAccountInfoVO.class);
        CaseAccountInfoVO body = responseEntity.getBody();
        log.info("findOne() end - result = " + body);
        return body;
    }

    @Override
    public List<CaseAccountInfoVO> findCaseAccountByCaseId(Integer caseId) {
        log.info("findCaseAccountByCaseId() start - caseId = " + caseId);
        HttpEntity<Integer> entity = new HttpEntity<Integer>(caseId);
        ResponseEntity<List<CaseAccountInfoVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_FIND_CASE_ACCOUNT_BY_CASE_ID, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<CaseAccountInfoVO>>() {});
        List<CaseAccountInfoVO> body = responseEntity.getBody();
        log.info("findCaseAccountByCaseId() end - " + body);
        return body;
    }

    @Override
    public Integer save(CaseAccountInfoVO caseAccount) {
        log.info("create() start - and caseAccount = " + caseAccount);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_CASE_ACCOUNT_SAVE, caseAccount, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end - and depositCardId = " + body);
        return body;
    }

    @Override
    public CaseAccountInfoVO findByAccountNumber(String accountNumber) {
        log.info("findByAccountNumber() start - and AccountNumber = " + accountNumber);
        ResponseEntity<CaseAccountInfoVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_FIND_CASE_ACCOUNT_BY_AC_NO, accountNumber, CaseAccountInfoVO.class);
        CaseAccountInfoVO body = responseEntity.getBody();
        log.info("findByAccountNumber() end - and returnVal = " + body);
        return body;
    }

    @Override
    public List<CaseAccountInfoVO> findOldCaseAccountByAccountType(SearchOldCaseAccountCriteriaVO criteria) {
        log.info("findOldCaseAccountByAccountType() start - criteria = " + criteria);
        HttpEntity<SearchOldCaseAccountCriteriaVO> entity = new HttpEntity<SearchOldCaseAccountCriteriaVO>(criteria);
        ResponseEntity<List<CaseAccountInfoVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_FIND_OLD_CASE_ACCOUNT_BY_ACCOUNT_TYPE, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<CaseAccountInfoVO>>() {});
        List<CaseAccountInfoVO> body = responseEntity.getBody();
        log.info("findCaseAccountByCaseId() end - " + body);
        return body;
    }
}
