package hk.oro.iefas.ws.casemgt.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.casemgt.dto.SearchProofOfDebtResultDTO;
import hk.oro.iefas.domain.counter.entity.CtrProofDebt;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 3298 $ $Date: 2018-06-26 13:33:59 +0800 (週二, 26 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class SearchProofOfDebtResultDTOAssembler
    extends PagingAssemblerSupport<SearchProofOfDebtResultDTO, CtrProofDebt> {

    @Override
    public SearchProofOfDebtResultDTO toDTO(CtrProofDebt t) {
        SearchProofOfDebtResultDTO dto = null;
        if (t != null) {
            dto = new SearchProofOfDebtResultDTO();
            dto.setProofOfDebtId(t.getProofOfDebtId());
            dto.setProofNumber(t.getProofNo());
            dto.setDateOfReceipt(t.getReceiptDate());
            dto.setCommonCreditorName(t.getCommonCreditorName());
            dto.setSectionName(t.getSectionName());
            dto.setStatus(t.getStatus());
            if (t.getCaseInfo() != null) {
                dto.setCaseNumber(t.getCaseInfo().getWholeCaseNo());
                dto.setCaseName(t.getCaseInfo().getCaseName());
            }
        }
        return dto;
    }

}
