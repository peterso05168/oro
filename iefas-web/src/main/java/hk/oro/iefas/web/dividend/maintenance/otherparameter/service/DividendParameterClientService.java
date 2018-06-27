package hk.oro.iefas.web.dividend.maintenance.otherparameter.service;

import java.util.List;

import hk.oro.iefas.domain.dividend.vo.DividendParameterVO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface DividendParameterClientService {

    List<DividendParameterVO> searchDividendParameter();

    boolean saveDividendParameter(List<DividendParameterVO> parameters);
}
