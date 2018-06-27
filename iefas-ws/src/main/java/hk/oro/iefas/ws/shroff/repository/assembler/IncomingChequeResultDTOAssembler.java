package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.shroff.dto.IncomingChequeResultDTO;
import hk.oro.iefas.domain.shroff.entity.ShrCheque;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2694 $ $Date: 2018-05-29 17:45:26 +0800 (週二, 29 五月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class IncomingChequeResultDTOAssembler extends PagingAssemblerSupport<IncomingChequeResultDTO, ShrCheque> {
    @Override
    public IncomingChequeResultDTO toDTO(ShrCheque entity) {
        IncomingChequeResultDTO incomingChequeResultDTO = null;
        if (entity != null) {
            incomingChequeResultDTO = new IncomingChequeResultDTO();
            incomingChequeResultDTO.setChequeId(entity.getChequeId());
            incomingChequeResultDTO.setNameOfPayer(entity.getPayee());
            incomingChequeResultDTO.setChequeNumber(Integer.valueOf(entity.getChequeNo()));
            incomingChequeResultDTO.setBankCode(entity.getBankCode());
            incomingChequeResultDTO.setAmount(entity.getChequeAmount());
            if (entity.getCurcy() != null) {
                incomingChequeResultDTO.setCurrencyName(entity.getCurcy().getCurcyName());
            }
            if (entity.getCaseInfo() != null && entity.getCaseInfo().getPost() != null) {
                incomingChequeResultDTO.setCaseOfficer(entity.getCaseInfo().getPost().getPostTitle());
            }
            incomingChequeResultDTO.setReceiveDate(entity.getReceiptDate());
            incomingChequeResultDTO.setStatus(entity.getStatus());
        }
        return incomingChequeResultDTO;
    }
}
