package hk.oro.iefas.web.funds.maintenance.fundsparameter.service;

import java.util.List;

import hk.oro.iefas.domain.funds.vo.FundsParameterVO;

/**
 * @version $Revision: 2117 $ $Date: 2018-04-18 18:46:56 +0800 (週三, 18 四月 2018) $
 * @author $Author: plau $
 */
public interface FundsParameterClientService {

    /**
     * Load Funds Parameter
     * 
     * @param N/A
     * @return FundsParameterVO
     */
    List<FundsParameterVO> loadParameter();

    /**
     * Find one currency by id
     * 
     * @param curcyId
     * @return CurrencyVO
     */
    void saveParameter(List<FundsParameterVO> fundsParameter);
}
