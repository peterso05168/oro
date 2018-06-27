package hk.oro.iefas.web.dividend.creditorreg.adjudication.service;

import hk.oro.iefas.domain.dividend.vo.AdjucationVO;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (週日, 10 六月 2018) $
 * @author $Author: vicki.huang $
 */
public interface AdjudicationClientService {
    AdjucationVO searchAdjudication(Integer adjucationId);

    Integer saveAdjudication(AdjucationVO adjucationVO);
}
