package hk.oro.iefas.web.dividend.dividendprocess.orfeecomputation.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.CaseFeeMaintenanceVO;
import hk.oro.iefas.domain.dividend.vo.CreateOrFeeComputationVO;
import hk.oro.iefas.domain.dividend.vo.DividendCalculationVO;
import hk.oro.iefas.domain.dividend.vo.ValidateOrFeeComputationVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.dividendprocess.orfeecomputation.service.OrFeeComputationClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3124 $ $Date: 2018-06-13 17:47:21 +0800 (週三, 13 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
public class OrFeeComputationClientServiceImpl extends BaseClientService implements OrFeeComputationClientService {

    @Override
    public Boolean validateCaseCreatable(ValidateOrFeeComputationVO validateOrFeeComputationVO) {
        log.info("validateCaseCreatable() start - validateOrFeeComputationVO: " + validateOrFeeComputationVO);
        ResponseEntity<Boolean> responseEntity = postForEntity(RequestUriConstant.CLIENT_URI_CASE_CREATABLE_VALIDATION,
            validateOrFeeComputationVO, Boolean.class);
        Boolean body = responseEntity.getBody();
        log.info("validateCaseCreatable() end return : " + body);
        return body;
    }

    @Override
    public Integer saveORFeeComputation(DividendCalculationVO dividendCalculationVO) {
        log.info("saveORFeeComputation() start - dividendCalculationVO: " + dividendCalculationVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_ORFEECOMPUTATION_SAVE, dividendCalculationVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveORFeeComputation() end return : " + body);
        return body;
    }

    @Override
    public DividendCalculationVO searchORFeeComputation(Integer orFeeComputationId) {
        log.info("searchORFeeComputation() start - orFeeComputationId: " + orFeeComputationId);
        ResponseEntity<DividendCalculationVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_ORFEECOMPUTATION_DETAIL, orFeeComputationId, DividendCalculationVO.class);
        DividendCalculationVO body = responseEntity.getBody();
        log.info("searchORFeeComputation() end - " + body);
        return body;
    }

    @Override
    public DividendCalculationVO searchORFeeComputation(CreateOrFeeComputationVO createORFeeComputationVO) {
        log.info("searchORFeeComputation() start - createORFeeComputationVO: " + createORFeeComputationVO);
        ResponseEntity<DividendCalculationVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_ORFEECOMPUTATION_INIT, createORFeeComputationVO, DividendCalculationVO.class);
        DividendCalculationVO body = responseEntity.getBody();
        log.info("searchORFeeComputation() end - " + body);
        return body;
    }

    @Override
    public List<CaseFeeMaintenanceVO> findCaseFeeMainsByType(String caseFeeType) {
        log.info("findCaseFeeMains start");
        ResponseEntity<List<CaseFeeMaintenanceVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_CASE_FEE_MAIN_LIST, HttpMethod.POST,
                new HttpEntity<String>(caseFeeType), new ParameterizedTypeReference<List<CaseFeeMaintenanceVO>>() {});

        List<CaseFeeMaintenanceVO> body = postForEntity.getBody();
        log.info("findCaseFeeMains end return:" + body);
        return body;
    }
}
