package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.shroff.dto.ChequeFileResultDTO;
import hk.oro.iefas.domain.shroff.entity.ShrCheque;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Component
public class ChequeFileResultDTOAssembler extends PagingAssemblerSupport<ChequeFileResultDTO, ShrCheque> {
    @Override
    public ChequeFileResultDTO toDTO(ShrCheque entity) {
        ChequeFileResultDTO result = null;
        if (entity != null) {
            result = new ChequeFileResultDTO();
            result.setItemNumber(entity.getFileItemNo());
            result.setChequeDate(entity.getChequeDate());
            if (entity.getShrVcrInfo() != null) {
                result.setVoucherNumber(entity.getShrVcrInfo().getVoucherNo());
            }
            result.setPayee(entity.getPayee());
            if (entity.getCurcy() != null) {
                result.setCurrency(entity.getCurcy().getCurcyName());
            }
            result.setAmount(entity.getChequeAmount());
        }
        return result;
    }
}
