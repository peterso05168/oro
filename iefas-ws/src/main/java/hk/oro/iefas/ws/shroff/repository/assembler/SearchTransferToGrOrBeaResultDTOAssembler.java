package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.shroff.dto.SearchTransferToGrOrBeaResultDTO;
import hk.oro.iefas.domain.shroff.entity.ShrTxfGrBea;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 3189 $ $Date: 2018-06-19 13:34:32 +0800 (週二, 19 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class SearchTransferToGrOrBeaResultDTOAssembler
    extends PagingAssemblerSupport<SearchTransferToGrOrBeaResultDTO, ShrTxfGrBea> {

    @Override
    public SearchTransferToGrOrBeaResultDTO toDTO(ShrTxfGrBea entity) {
        if (entity != null) {
            SearchTransferToGrOrBeaResultDTO dto = new SearchTransferToGrOrBeaResultDTO();
            dto.setTransferId(entity.getTransferId());
            dto.setTransferNo(entity.getTransferNo());
            dto.setTransferType(entity.getTransferType().getTxfAmountTypeName());
            dto.setProcessDate(entity.getProcessDate());
            dto.setCutOffDate(entity.getCutOffDate());
            dto.setTotalAmount(entity.getTotalAmount());
            dto.setPaymentVoucherNumber(entity.getVoucher().getVoucherNo());

            return dto;
        }
        return null;
    }

}
