package hk.oro.iefas.ws.bank.repository.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.dto.BankRateDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyBasicInfoDTO;
import hk.oro.iefas.domain.bank.entity.BankRate;
import hk.oro.iefas.domain.bank.entity.Currency;

/**
 * @version $Revision: 3030 $ $Date: 2018-06-11 18:10:24 +0800 (週一, 11 六月 2018) $
 * @author $Author: marvel.ma $
 */
public class BankRateDTOAssembler {

    public static Page<BankRateDTO> toResultDTO(Page<BankRate> page) {
        if (page == null) {
            return null;
        }
        List<BankRateDTO> dtos = null;
        List<BankRate> content = page.getContent();
        if (content != null) {
            dtos = content.stream().map(bankRate -> toDTO(bankRate)).collect(Collectors.toList());
        }

        return new PageImpl<>(dtos, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
            page.getTotalElements());
    }

    public static BankRateDTO toDTO(BankRate bankRate) {
        BankRateDTO dto = null;
        if (bankRate != null) {
            dto = DataUtils.copyProperties(bankRate, BankRateDTO.class);
            dto.setInvestmentDate(bankRate.getInvestDate());

            Currency currency = bankRate.getCurrency();
            CurrencyBasicInfoDTO currencyBasicInfoDTO = null;
            if (currency != null) {
                currencyBasicInfoDTO
                    = new CurrencyBasicInfoDTO(currency.getCurcyId(), currency.getCurcyName(), currency.getCurcyCode());
            }
            dto.setCurrencyBasicInfo(currencyBasicInfoDTO);
        }
        return dto;
    }
}
