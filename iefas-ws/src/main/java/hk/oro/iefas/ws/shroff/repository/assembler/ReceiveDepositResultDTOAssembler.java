package hk.oro.iefas.ws.shroff.repository.assembler;

import hk.oro.iefas.domain.counter.entity.CtrCaseDeposit;
import hk.oro.iefas.domain.shroff.dto.ReceiveDepositResultDTO;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @version $Revision: 2768 $ $Date: 2018-05-31 16:03:24 +0800 (週四, 31 五月 2018) $
 * @author $Author: garrett.han $
 */
@Component
public class ReceiveDepositResultDTOAssembler extends PagingAssemblerSupport<ReceiveDepositResultDTO, CtrCaseDeposit> {
    @Override
    public ReceiveDepositResultDTO toDTO(CtrCaseDeposit entity) {
        ReceiveDepositResultDTO result = null;
        if (entity != null) {
            result = new ReceiveDepositResultDTO();
            result.setDepositId(entity.getDepositId());
            result.setAmount(entity.getDepositAmount());
            if (entity.getCurcy() != null) {
                result.setCurrency(entity.getCurcy().getCurcyName());
            }
            result.setDate(entity.getReceiveDate());
            result.setDepositNumber(entity.getDepositNo());
            result.setStatus(entity.getStatus());
            if (entity.getShrReceipt() != null) {
                result.setReceiptNumber(entity.getShrReceipt().getReceiptNo());
            }
            if (entity.getCaseInfo() != null) {
                result.setCaseNumber(entity.getCaseInfo().getWholeCaseNo());
            }
            result.setPayerName(entity.getPayer());
        }
        return result;
    }
}
