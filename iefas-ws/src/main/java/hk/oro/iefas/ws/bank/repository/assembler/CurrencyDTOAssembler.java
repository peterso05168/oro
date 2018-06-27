package hk.oro.iefas.ws.bank.repository.assembler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.bank.dto.CurrencyBasicInfoDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyResultDTO;
import hk.oro.iefas.domain.bank.entity.Currency;
import hk.oro.iefas.domain.bank.entity.CurrencyRate;

/**
 * @version $Revision: 1088 $ $Date: 2018-02-08 11:15:43 +0800 (週四, 08 二月 2018) $
 * @author $Author: marvel.ma $
 */
public class CurrencyDTOAssembler {

    public static Page<CurrencyResultDTO> toResultDTO(Page<Currency> page) {
        List<Currency> content = page.getContent();
        List<CurrencyResultDTO> dtoList = new ArrayList<>();
        if (CommonUtils.isNotEmpty(content)) {
            Date currentDay = DateUtils.getCurrentDay();
            content.stream().forEach(currency -> {
                CurrencyResultDTO currencyRateResultDTO = DataUtils.copyProperties(currency, CurrencyResultDTO.class);

                BigDecimal rateAmount = new BigDecimal(0);
                List<CurrencyRate> currencyRates = currency.getCurrencyRates();
                if (CommonUtils.isNotEmpty(currencyRates)) {
                    Optional<CurrencyRate> first = currencyRates.stream()
                        .filter(rate -> (rate.getEffectiveFrom().before(currentDay)
                            || DateUtils.isSameDay(rate.getEffectiveFrom(), currentDay))
                            && (rate.getEffectiveTo().after(currentDay)
                                || DateUtils.isSameDay(rate.getEffectiveTo(), currentDay)))
                        .findFirst();
                    if (first.isPresent()) {
                        rateAmount = first.get().getRateAmount();
                    }
                }
                currencyRateResultDTO.setRateAmount(rateAmount);

                dtoList.add(currencyRateResultDTO);
            });
        }

        return new PageImpl<>(dtoList, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
            page.getTotalElements());
    }

    public static CurrencyDTO toDTO(Currency currency) {
        if (currency != null) {
            CurrencyDTO currencyDTO = DataUtils.copyProperties(currency, CurrencyDTO.class);
            return currencyDTO;
        }
        return null;
    }

    public static List<CurrencyBasicInfoDTO> toDTOs(List<Currency> currencies) {
        List<CurrencyBasicInfoDTO> list = new ArrayList<CurrencyBasicInfoDTO>();
        for (Currency currency : currencies) {
            list.add(DataUtils.copyProperties(currency, CurrencyBasicInfoDTO.class));
        }
        return list;
    }

}
