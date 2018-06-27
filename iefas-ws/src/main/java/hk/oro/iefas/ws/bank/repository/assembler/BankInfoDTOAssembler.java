package hk.oro.iefas.ws.bank.repository.assembler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.dto.BankAccountInfoResultDTO;
import hk.oro.iefas.domain.bank.dto.BankInfoDTO;
import hk.oro.iefas.domain.bank.entity.BankInfo;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class BankInfoDTOAssembler {

    public static Page<BankAccountInfoResultDTO> toResultDTO(Page<BankInfo> page) {
        List<BankInfo> content = page.getContent();
        List<BankAccountInfoResultDTO> dtoList = new ArrayList<BankAccountInfoResultDTO>();
        if (CommonUtils.isNotEmpty(content)) {
            for (BankInfo bankInfo : content) {
                BankAccountInfoResultDTO bankAccountInfoResultDTO
                    = DataUtils.copyProperties(bankInfo, BankAccountInfoResultDTO.class);
                bankAccountInfoResultDTO.setAvaiableRoom(getAvailableRoom(bankInfo));
                dtoList.add(bankAccountInfoResultDTO);
            }
        }
        return new PageImpl<>(dtoList, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
            page.getTotalElements());
    }

    public static BankInfoDTO toDTO(BankInfo bankInfo) {
        if (bankInfo != null) {
            BankInfoDTO bankInfoDTO = DataUtils.copyProperties(bankInfo, BankInfoDTO.class);
            bankInfoDTO.setAvaiableRoom(getAvailableRoom(bankInfo));
            return bankInfoDTO;
        }
        return null;
    }

    public static List<BankInfoDTO> toDTOs(List<BankInfo> bankInfos) {
        List<BankInfoDTO> list = new ArrayList<BankInfoDTO>();
        if (CommonUtils.isNotEmpty(bankInfos)) {
            bankInfos.stream().forEach(bankInfo -> {
                BankInfoDTO bankBasicDTO = DataUtils.copyProperties(bankInfo, BankInfoDTO.class);
                list.add(bankBasicDTO);
            });
        }
        return list;
    }

    private static BigDecimal getAvailableRoom(BankInfo bankInfo) {
        Integer bankDepositlimit = bankInfo.getBankDepositlimit();
        if (bankDepositlimit != null && bankDepositlimit.intValue() > 0) {
            if (bankInfo.getBankDeposit() != null && bankInfo.getBankDeposit().getBankInvestAmount() != null) {
                return new BigDecimal(bankDepositlimit - bankInfo.getBankDeposit().getBankInvestAmount().doubleValue());
            } else {
                return new BigDecimal(bankDepositlimit);
            }
        } else {
            return new BigDecimal(0);
        }
    }

}
