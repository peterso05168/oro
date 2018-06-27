package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.BusinessUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseAccountInfoDTOAssembler;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 3208 $ $Date: 2018-06-20 10:50:21 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class ReceiptVoucherAccountItemDTOAssembler extends AssemblerSupport<ReceiptVoucherAccountItemDTO, ShrVcrItem> {

    @Autowired
    private CaseAccountInfoDTOAssembler caseAccountInfoDTOAssembler;

    @Override
    public ReceiptVoucherAccountItemDTO toDTO(ShrVcrItem entity) {
        if (entity != null) {
            ReceiptVoucherAccountItemDTO receiptVoucherAccountItemDTO
                = DataUtils.copyProperties(entity, ReceiptVoucherAccountItemDTO.class);
            receiptVoucherAccountItemDTO.setPayerName(entity.getReceiver());

            CaseAccountInfo caseAccountInfo = entity.getCaseAccount();
            if (caseAccountInfo != null) {
                receiptVoucherAccountItemDTO.setAccount(this.caseAccountInfoDTOAssembler.toDTO(caseAccountInfo));

                receiptVoucherAccountItemDTO
                    .setAccountTypeCodeValue(caseAccountInfo.getCaseAccountType().getCaseAcTypeCode());
                Case caseInfo = caseAccountInfo.getCaseInfo();
                if (caseInfo != null) {
                    receiptVoucherAccountItemDTO.setCaseNoValue(BusinessUtils.addZeroToCaseNo(caseInfo.getCaseNo()));
                    receiptVoucherAccountItemDTO.setCaseYearValue(String.valueOf(caseInfo.getCaseYear()));
                    receiptVoucherAccountItemDTO.setCaseTypeCodeValue(caseInfo.getCaseType().getCaseTypeCode());
                }
            }
            return receiptVoucherAccountItemDTO;
        }
        return null;
    }
}
