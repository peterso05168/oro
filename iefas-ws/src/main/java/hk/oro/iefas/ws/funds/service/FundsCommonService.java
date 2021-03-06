package hk.oro.iefas.ws.funds.service;

import java.util.List;

import hk.oro.iefas.domain.funds.dto.InvestmentTypeDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface FundsCommonService {

    public List<InvestmentTypeDTO> searchInvestmentType();
}
