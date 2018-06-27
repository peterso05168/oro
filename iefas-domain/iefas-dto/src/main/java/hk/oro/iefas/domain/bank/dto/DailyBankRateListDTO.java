package hk.oro.iefas.domain.bank.dto;

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
public class DailyBankRateListDTO {

    private BankBasicDTO bankBasic;
    private List<DailyBankRateDTO> dailyBankRates = new ArrayList<>();
}
