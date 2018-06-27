package hk.oro.iefas.web.dividend.dividendprocess.dividendcheque.service.impl;

import java.util.List;

import hk.oro.iefas.domain.dividend.vo.DividendChequeVO;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (Sun, 10 Jun 2018) $
 * @author $Author: vicki.huang $
 */
public interface DividendChequeClientService {
    DividendChequeVO searchDividendCheque(Integer dividendChequeId);

    Integer saveDividendCheque(DividendChequeVO dividendChequeVO);

    boolean saveDividendChequeList(List<DividendChequeVO> dividendChequeVOList);

    public List<DividendChequeVO> searchDivChequeListByDivScheId(List<Integer> divScheItemIdList);
}
