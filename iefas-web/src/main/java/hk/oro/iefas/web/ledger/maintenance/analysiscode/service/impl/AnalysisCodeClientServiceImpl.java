package hk.oro.iefas.web.ledger.maintenance.analysiscode.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.SearchAnalysisCodeCriteriaVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.maintenance.analysiscode.service.AnalysisCodeClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3048 $ $Date: 2018-06-11 21:14:01 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
@Slf4j
@Service
public class AnalysisCodeClientServiceImpl extends BaseClientService implements AnalysisCodeClientService {

    @Override
    public Integer save(AnalysisCodeVO analysisCodeVO) {
        log.info("saveAnalysisCode() start - AnalysisCodeVO: " + analysisCodeVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_ANALYSIS_CODE_DETAIL, analysisCodeVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveAnalysisCode() end - " + body);
        return body;
    }

    @Override
    public AnalysisCodeVO findOne(Integer analysisCodeId) {
        log.info("getAnalysisCodeDetail() start - analysisCodeId: " + analysisCodeId);
        ResponseEntity<AnalysisCodeVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_GET_ANALYSIS_CODE_DETAIL, analysisCodeId, AnalysisCodeVO.class);
        AnalysisCodeVO body = responseEntity.getBody();
        log.info("getAnalysisCodeDetail() end - result: " + body);
        return body;
    }

    @Override
    public List<AnalysisCodeVO> findByAnalysisCode(String analysisCode) {
        log.info("findByAnalysisCode() start - AnalysisCode: " + analysisCode);
        ResponseEntity<List<AnalysisCodeVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_FIND_ANALYSIS_CODE_BY_ANALYSIS_CODE, HttpMethod.POST,
                new HttpEntity<String>(analysisCode), new ParameterizedTypeReference<List<AnalysisCodeVO>>() {});
        List<AnalysisCodeVO> body = responseEntity.getBody();
        log.info("findByAnalysisCode() end - " + body);
        return body;
    }

    @Override
    public Boolean isAnalysisCodeExistedByCriteria(SearchAnalysisCodeCriteriaVO criteria) {
        log.info("isAnalysisCodeExistedByCriteria() start - criteria: " + criteria);
        
        ResponseEntity<Boolean> responseEntity
        = postForEntity(RequestUriConstant.CLIENT_URI_IS_ANALYSIS_CODE_EXISTS_BY_CRITERIA, criteria, Boolean.class);
        Boolean body = responseEntity.getBody();
        
        log.info("isAnalysisCodeExistedByCriteria() end - " + body);
        return body;
    }

    @Override
    public Boolean existsByAnalysisCode(SearchAnalysisCodeCriteriaVO criteria) {
        log.info("existsByAnalysisCode() start - criteria: " + criteria);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_EXISTS_BY_ANALYSIS_CODE, criteria, Boolean.class);
        Boolean body = responseEntity.getBody();
        log.info("existsByAnalysisCode() end - " + body);
        return body;
    }

}
