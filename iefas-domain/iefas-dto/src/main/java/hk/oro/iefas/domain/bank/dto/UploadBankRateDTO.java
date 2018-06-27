package hk.oro.iefas.domain.bank.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UploadBankRateDTO extends TxnDTO {

    private Integer bankRateId;
    private Date investmentDate;
    private CurrencyBasicInfoDTO currencyBasicInfo;
    private List<DailyBankRateListDTO> dailyBankRateLists = new ArrayList<>();
    private String status;
}
