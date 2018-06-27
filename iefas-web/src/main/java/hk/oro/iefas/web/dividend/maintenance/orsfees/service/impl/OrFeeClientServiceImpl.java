package hk.oro.iefas.web.dividend.maintenance.orsfees.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.CalculationMaintenanceVO;
import hk.oro.iefas.domain.dividend.vo.CaseFeeTypeVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.maintenance.orsfees.service.OrFeeClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class OrFeeClientServiceImpl extends BaseClientService implements OrFeeClientService {

    @Override
    public CaseFeeTypeVO searchORFeeItemWithCalculationMethod(Integer caseFeeTypeId) {
        log.info("searchORFeeItemWithCalculationMethod start - param: " + caseFeeTypeId);
        CaseFeeTypeVO body = this.postForObject(RequestUriConstant.CLIENT_URI_ORFEE_SEARCH_ORFEEITEM,
            caseFeeTypeId == null ? 0 : caseFeeTypeId, CaseFeeTypeVO.class);
        log.info("searchORFeeItemWithCalculationMethod end - return: " + body);
        return body;
    }

    @Override
    public Boolean validatesSaveORFeeItemWithCalculationMethod(List<CalculationMaintenanceVO> calculationMaintenances) {
        log.info("saveORFeeItemWithCalculationMethodValidate start - param: " + calculationMaintenances);
        Boolean body = this.postForObject(RequestUriConstant.CLIENT_URI_ORFEE_VALIDATE_SAVE_ORFEEITEM,
            calculationMaintenances, Boolean.class);
        log.info("saveORFeeItemWithCalculationMethodValidate end - return: " + body);
        return body;
    }

    @Override
    public Integer saveORFeeItemWithCalculationMethod(CaseFeeTypeVO caseFeeTypeVO) {
        log.info("sveORFeeItemWithCalculationMethod start - param: " + caseFeeTypeVO);
        Integer body
            = this.postForObject(RequestUriConstant.CLIENT_URI_ORFEE_SAVE_ORFEE_ITEM, caseFeeTypeVO, Integer.class);
        log.info("sveORFeeItemWithCalculationMethod end - return: " + body);
        return body;
    }

    @Override
    public CaseFeeTypeVO searchORFeeItemWithAnalysisCodeCharged(Integer caseFeeTypeId) {
        log.info("searchORFeeItemWithAnalysisCodeCharged start - param: " + caseFeeTypeId);
        ResponseEntity<CaseFeeTypeVO> response = this.postForEntity(
            RequestUriConstant.CLIENT_URI_ORFEE_SEARCH_ORFEEITEM_WITH_ANALYSIS, caseFeeTypeId, CaseFeeTypeVO.class);
        CaseFeeTypeVO result = response.getBody();
        log.info("searchORFeeItemWithAnalysisCodeCharged end - param: " + result);
        return result;
    }

}
