package hk.oro.iefas.web.dividend.dividendprocess.percentagesadjustment.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleDistVO;
import hk.oro.iefas.domain.dividend.vo.PercentagesAdjustmentVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.dividendprocess.percentagesadjustment.service.PercentagesAdjustmentClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2961 $ $Date: 2018-06-06 20:14:53 +0800 (週三, 06 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@Service
public class PercentagesAdjustmentClientServiceImpl extends BaseClientService
    implements PercentagesAdjustmentClientService {

    @Override
    public boolean savePercentagesAdjustment(PercentagesAdjustmentVO percentagesAdjustmentVO) {
        log.info("searchPercentagesAdjustmentList - start and percentagesAdjustmentVO=" + percentagesAdjustmentVO);
        ResponseEntity<Boolean> response = this.postForEntity(RequestUriConstant.CLIENT_URI_SAVE_PERCENTAGES_ADJUSTMENT,
            percentagesAdjustmentVO, Boolean.class);
        boolean result = response.getBody();
        log.info("searchPercentagesAdjustmentList - end and result=" + result);
        return result;
    }

    @Override
    public PercentagesAdjustmentVO searchPercentagesAdjustmentVO(Integer adjudicationResultId) {
        log.info("searchPercentagesAdjustmentVO - start and adjudicationResultId=" + adjudicationResultId);
        ResponseEntity<PercentagesAdjustmentVO> response
            = this.postForEntity(RequestUriConstant.CLIENT_URI_SEARCH_PERCENTAGES_ADJUSTMENT, adjudicationResultId,
                PercentagesAdjustmentVO.class);
        PercentagesAdjustmentVO result = response.getBody();
        log.info("searchPercentagesAdjustmentVO - start and percentagesAdjustmentVO=" + result);
        return result;
    }

    @Override
    public DividendScheduleDistVO searchDivScheduleDistByAppAdjResultItemId(Integer appAdjResultItemId) {
        log.info("searchDivScheduleDistByAppAdjResultItemId - start and appAdjResultItemId=" + appAdjResultItemId);
        ResponseEntity<DividendScheduleDistVO> response
            = this.postForEntity(RequestUriConstant.CLIENT_URI_SEARCH_BY_APPADJRESULTITEMID, appAdjResultItemId,
                DividendScheduleDistVO.class);
        DividendScheduleDistVO result = response.getBody();
        log.info("searchDivScheduleDistByAppAdjResultItemId - end and percentagesAdjustmentVO=" + result);
        return result;
    }

}
