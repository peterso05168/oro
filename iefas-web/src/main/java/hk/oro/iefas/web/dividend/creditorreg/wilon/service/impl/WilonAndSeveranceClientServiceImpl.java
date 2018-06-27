package hk.oro.iefas.web.dividend.creditorreg.wilon.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.dividend.vo.CreateWilonAndSeveranceVO;
import hk.oro.iefas.domain.dividend.vo.WilonAndSeverancePayVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.creditorreg.wilon.service.WilonAndSeveranceClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2961 $ $Date: 2018-06-06 20:14:53 +0800 (週三, 06 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@Named
public class WilonAndSeveranceClientServiceImpl extends BaseClientService implements WilonAndSeveranceClientService {

    @Override
    public List<CreditorVO> searchCreditorByCaseNumber(CaseNumberVO caseNumber) {
        log.info("searchCreditorByCaseNumber() start - searchWilonCriteria - " + caseNumber);
        HttpEntity<CaseNumberVO> entity = new HttpEntity<CaseNumberVO>(caseNumber);
        ResponseEntity<List<CreditorVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_SEARCH_CREDITOR_BY_CASENUMBER, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<CreditorVO>>() {});
        List<CreditorVO> body = responseEntity.getBody();
        log.info("searchCreditorByCaseNumber() end - " + body);
        return body;
    }

    @Override
    public Integer saveWILONAndSeverancePay(WilonAndSeverancePayVO wilonAndSeverancePay) {
        log.info("searchForCreateWILONAndSeverancePay() start - wilonAndSeverancePay - " + wilonAndSeverancePay);
        ResponseEntity<Integer> response = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_WILON_AND_SEVERANCE_PAY,
            wilonAndSeverancePay, Integer.class);
        Integer result = response.getBody();
        log.info("searchForCreateWILONAndSeverancePay() end and result=" + result);
        return result;
    }

    @Override
    public WilonAndSeverancePayVO searchWILONAndSeverancePay(Integer wilonAndSeverancePayId) {
        log.info("searchWILONAndSeverancePay() start - wilonAndSeverancePay - " + wilonAndSeverancePayId);
        ResponseEntity<WilonAndSeverancePayVO> response
            = this.postForEntity(RequestUriConstant.CLIENT_URI_SEARCH_BY_WILON_AND_SEVERANCE_ID, wilonAndSeverancePayId,
                WilonAndSeverancePayVO.class);
        WilonAndSeverancePayVO result = response.getBody();
        log.info("searchWILONAndSeverancePay() end and result = " + result);
        return result;
    }

    @Override
    public CreditorVO searchCreditorById(Integer creditorId) {
        log.info("searchCreditorById() start and creditorId = " + creditorId);
        CreditorVO result = null;
        ResponseEntity<CreditorVO> response
            = this.postForEntity(RequestUriConstant.CLIENT_URI_FIND_CREDITOR_BY_ID, creditorId, CreditorVO.class);
        result = response.getBody();
        log.info("searchCreditorById() end and result = " + result);
        return result;
    }

    @Override
    public boolean createWILONAndSeveranceValidate(CreateWilonAndSeveranceVO createWILONAndSeverance) {
        log.info("createWILONAndSeveranceValidate() start and CreateWILONAndSeveranceDTO = " + createWILONAndSeverance);
        ResponseEntity<Boolean> response = this.postForEntity(
            RequestUriConstant.CLIENT_URI_CREATE_WILON_AND_SEVERANCE_VALIDATE, createWILONAndSeverance, Boolean.class);
        boolean result = response.getBody();
        log.info("createWILONAndSeveranceValidate() end and result = " + result);
        return result;
    }
}
