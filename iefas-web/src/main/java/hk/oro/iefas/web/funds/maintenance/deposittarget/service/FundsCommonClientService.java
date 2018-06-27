package hk.oro.iefas.web.funds.maintenance.deposittarget.service;

import java.util.List;

import hk.oro.iefas.domain.funds.vo.InvestmentStatusVO;
import hk.oro.iefas.domain.funds.vo.InvestmentTypeVO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface FundsCommonClientService {

    public List<InvestmentTypeVO> searchInvestmentType();

    public List<InvestmentStatusVO> searchInvestmentStatus();
}
