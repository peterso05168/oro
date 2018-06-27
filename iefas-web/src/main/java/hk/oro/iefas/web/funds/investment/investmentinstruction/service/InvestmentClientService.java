package hk.oro.iefas.web.funds.investment.investmentinstruction.service;

import hk.oro.iefas.domain.funds.vo.InvestmentInstructionDetailVO;

/**
 * @version $Revision: 2743 $ $Date: 2018-05-30 19:14:53 +0800 (週三, 30 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface InvestmentClientService {
    InvestmentInstructionDetailVO searchInvestmentOption(Integer invItemId);
}
