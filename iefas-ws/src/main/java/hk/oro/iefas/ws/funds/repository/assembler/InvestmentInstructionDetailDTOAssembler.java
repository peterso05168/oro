package hk.oro.iefas.ws.funds.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.funds.dto.InvestmentInstructionDetailDTO;
import hk.oro.iefas.domain.funds.entity.InvItem;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (Thu, 24 May 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class InvestmentInstructionDetailDTOAssembler
    extends PagingAssemblerSupport<InvestmentInstructionDetailDTO, InvItem> {

    @Autowired
    private AccountInvestmentItemDTOAssembler accountInvestmentItemDTOAssembler;

    @Override
    public InvestmentInstructionDetailDTO toDTO(InvItem invItem) {
        InvestmentInstructionDetailDTO dto = null;
        if (invItem != null) {
            dto = DataUtils.copyProperties(invItem, InvestmentInstructionDetailDTO.class);
            dto.setAccountInvestmentItem(accountInvestmentItemDTOAssembler.toDTO(invItem));
        }
        return dto;
    }

}
