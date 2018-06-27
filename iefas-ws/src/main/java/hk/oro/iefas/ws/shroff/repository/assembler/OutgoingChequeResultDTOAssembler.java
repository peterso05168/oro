package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.shroff.dto.OutgoingChequeResultDTO;
import hk.oro.iefas.domain.shroff.entity.ShrCheque;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Component
public class OutgoingChequeResultDTOAssembler extends PagingAssemblerSupport<OutgoingChequeResultDTO, ShrCheque> {

    @Override
    public OutgoingChequeResultDTO toDTO(ShrCheque entity) {
        OutgoingChequeResultDTO outgoingChequeResultDTO = null;
        if (entity != null) {
            outgoingChequeResultDTO = new OutgoingChequeResultDTO();
            outgoingChequeResultDTO.setChequeId(entity.getChequeId());
            if (entity.getChequeNo() != null)
                outgoingChequeResultDTO.setChequeNumber(Integer.valueOf(entity.getChequeNo()));
            if (entity.getShrVcrInfo() != null) {
                outgoingChequeResultDTO.setVoucherNumber(entity.getShrVcrInfo().getVoucherNo());
            }
            outgoingChequeResultDTO.setChequeDate(entity.getChequeDate());
            outgoingChequeResultDTO.setChequeDate(entity.getChequeDate());
            outgoingChequeResultDTO.setPayee(entity.getPayee());
            if (entity.getCurcy() != null) {
                outgoingChequeResultDTO.setCurrencyName(entity.getCurcy().getCurcyName());
            }
            outgoingChequeResultDTO.setAmount(entity.getChequeAmount());
            outgoingChequeResultDTO.setStatus(entity.getStatus());
            outgoingChequeResultDTO.setChequeTypeId(entity.getChequeTypeId());
        }
        return outgoingChequeResultDTO;
    }
}
