package hk.oro.iefas.ws.bank.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.dto.BankAccountInfoResultDTO;
import hk.oro.iefas.domain.bank.entity.BankAccount;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class BankAccountDTOAssembler {

    public static Page<BankAccountInfoResultDTO> toResultDTO(Page<BankAccount> page) {
        List<BankAccount> content = page.getContent();
        List<BankAccountInfoResultDTO> dtoList = new ArrayList<BankAccountInfoResultDTO>();
        if (CommonUtils.isNotEmpty(content)) {
            content.stream().forEach(bankAccount -> {
                BankAccountInfoResultDTO bankAccountInfoResultDTO
                    = DataUtils.copyProperties(bankAccount, BankAccountInfoResultDTO.class);
                dtoList.add(bankAccountInfoResultDTO);
            });
        }
        return new PageImpl<>(dtoList, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
            page.getTotalElements());
    }
}
