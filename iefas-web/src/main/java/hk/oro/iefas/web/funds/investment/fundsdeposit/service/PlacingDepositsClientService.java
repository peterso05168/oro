package hk.oro.iefas.web.funds.investment.fundsdeposit.service;

import java.util.Date;

import hk.oro.iefas.domain.bank.vo.CalculationOfFundsAvailableVO;

/**
 * @version $Revision: 2743 $ $Date: 2018-05-30 19:14:53 +0800 (週三, 30 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface PlacingDepositsClientService {
    CalculationOfFundsAvailableVO searchPlacingDeposits(Integer calculationOfFundsAvailableId);

    Integer savePlacingDeposits(CalculationOfFundsAvailableVO availableVO);

    Boolean existsByInvestmentDate(Date invtDate);
}
