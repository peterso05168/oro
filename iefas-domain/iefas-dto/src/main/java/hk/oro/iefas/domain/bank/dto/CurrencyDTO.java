package hk.oro.iefas.domain.bank.dto;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 981 $ $Date: 2018-01-31 10:26:00 +0800 (週三, 31 一月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDTO extends TxnDTO {

    private Integer curcyId;

    @NotBlank
    @Size(max = 100)
    private String curcyName;

    @NotBlank
    @Size(max = 5)
    private String curcyCode;

    private String status;

    private List<CurrencyRateDTO> currencyRates;
}
