package hk.oro.iefas.web.dividend.dividendprocess.percentagesadjustment.service;

import hk.oro.iefas.domain.dividend.vo.DividendScheduleDistVO;
import hk.oro.iefas.domain.dividend.vo.PercentagesAdjustmentVO;

/**
 * @version $Revision: 2961 $ $Date: 2018-06-06 20:14:53 +0800 (週三, 06 六月 2018) $
 * @author $Author: noah.liang $
 */
public interface PercentagesAdjustmentClientService {

    public boolean savePercentagesAdjustment(PercentagesAdjustmentVO percentagesAdjustmentVO);

    public PercentagesAdjustmentVO searchPercentagesAdjustmentVO(Integer adjudicationResultId);

    public DividendScheduleDistVO searchDivScheduleDistByAppAdjResultItemId(Integer appAdjResultItemId);
}
