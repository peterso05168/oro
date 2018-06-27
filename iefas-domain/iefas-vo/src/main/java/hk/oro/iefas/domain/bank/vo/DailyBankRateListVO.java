package hk.oro.iefas.domain.bank.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@NoArgsConstructor
public class DailyBankRateListVO {

    private BankBasicVO bankBasic;
    private List<DailyBankRateVO> dailyBankRates = new ArrayList<>();
}
