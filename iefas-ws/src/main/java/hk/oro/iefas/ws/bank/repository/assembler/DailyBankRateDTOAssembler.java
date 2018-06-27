package hk.oro.iefas.ws.bank.repository.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.dto.CurrencyBasicInfoDTO;
import hk.oro.iefas.domain.bank.dto.DailyBankRateDTO;
import hk.oro.iefas.domain.bank.entity.BankRateItem;
import hk.oro.iefas.domain.bank.entity.Currency;

/**
 * @version $Revision: 3030 $ $Date: 2018-06-11 18:10:24 +0800 (週一, 11 六月 2018) $
 * @author $Author: marvel.ma $
 */
public class DailyBankRateDTOAssembler {

    public static DailyBankRateDTO toDTO(BankRateItem bankRateItem) {
        if (bankRateItem == null) {
            return null;
        }
        DailyBankRateDTO dailyBankRateDTO = DataUtils.copyProperties(bankRateItem, DailyBankRateDTO.class);
        Currency currency = bankRateItem.getCurrency();
        if (currency != null) {
            dailyBankRateDTO.setCurrencyBasicInfo(
                new CurrencyBasicInfoDTO(currency.getCurcyId(), currency.getCurcyName(), currency.getCurcyCode()));
        }

        return dailyBankRateDTO;
    }

    public static List<DailyBankRateDTO> toDTOs(Iterable<BankRateItem> bankRateItems) {
        List<DailyBankRateDTO> bankRateDTOs = null;
        if (bankRateItems != null) {
            bankRateDTOs = new ArrayList<>();
            for (BankRateItem bankRateItem : bankRateItems) {
                bankRateDTOs.add(toDTO(bankRateItem));
            }

        }
        return bankRateDTOs;
    }

    public static List<DailyBankRateDTO> toDTOs(List<BankRateItem> bankRateItems) {
        if (bankRateItems == null) {
            return null;
        }
        return bankRateItems.stream().map(rateItem -> toDTO(rateItem)).collect(Collectors.toList());
    }
}
