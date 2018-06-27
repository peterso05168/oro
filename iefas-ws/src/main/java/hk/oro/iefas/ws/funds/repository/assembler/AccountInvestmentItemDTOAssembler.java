package hk.oro.iefas.ws.funds.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.funds.dto.AccountInvestmentItemDTO;
import hk.oro.iefas.domain.funds.entity.InvItem;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 3286 $ $Date: 2018-06-25 17:48:56 +0800 (週一, 25 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class AccountInvestmentItemDTOAssembler extends PagingAssemblerSupport<AccountInvestmentItemDTO, InvItem> {

    @Override
    public AccountInvestmentItemDTO toDTO(InvItem invItem) {
        AccountInvestmentItemDTO dto = null;
        if (invItem != null) {
            dto = DataUtils.copyProperties(invItem, AccountInvestmentItemDTO.class);
        }
        return dto;
    }

}
