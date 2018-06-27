package hk.oro.iefas.web.casemgt.casedetailenquiry.service.impl;

import javax.inject.Named;

import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2768 $ $Date: 2018-05-31 16:03:24 +0800 (週四, 31 五月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
public class CaseClientServiceImpl extends BaseClientService implements CaseClientService {

    @Override
    public CaseVO findOne(Integer caseId) {
        log.info("findOne() start - caseId: " + caseId);
        ResponseEntity<CaseVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_CASE_DETAIL, caseId, CaseVO.class);
        CaseVO body = responseEntity.getBody();
        log.info("findOne() end - result = " + body);
        return body;
    }

    @Override
    public Boolean validateExistsByCaseNo(String caseNo) {
        log.info("validateExistsByCaseNo() start - and caseNo = " + caseNo);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_EXISTS_BY_CASENO, caseNo, Boolean.class);
        Boolean result = responseEntity.getBody();
        log.info("validateExistsByCaseNo() end - result = " + result);
        return result;
    }

    @Override
    public CaseVO findByCaseNumber(CaseNumberVO caseNumber) {
        log.info("findByCaseNumber() start - and caseNumber = " + caseNumber);
        ResponseEntity<CaseVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_CASE_FINDBYCASENO, caseNumber, CaseVO.class);
        CaseVO result = responseEntity.getBody();
        log.info("findByCaseNumber() end - result = " + result);
        return result;
    }

    @Override
    public CaseVO findByWholeCaseNumber(String wholeCaseNumber) {
        log.info("findByWholeCaseNumber start - caseNumber: " + wholeCaseNumber);
        ResponseEntity<CaseVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_CASE_FIND_BY_CASENO, wholeCaseNumber, CaseVO.class);
        CaseVO result = responseEntity.getBody();
        log.info("findByWholeCaseNumber end - " + result);
        return result;
    }
}
