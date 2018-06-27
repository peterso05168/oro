package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.BusinessUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseAccountInfoDTOAssembler;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 3208 $ $Date: 2018-06-20 10:50:21 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class PaymentVoucherAccountItemDTOAssembler extends AssemblerSupport<PaymentVoucherAccountItemDTO, ShrVcrItem> {

    @Autowired
    private CaseAccountInfoDTOAssembler caseAccountInfoDTOAssembler;

    @Override
    public PaymentVoucherAccountItemDTO toDTO(ShrVcrItem entity) {
        if (entity != null) {
            PaymentVoucherAccountItemDTO paymentVoucherAccountItemDTO
                = DataUtils.copyProperties(entity, PaymentVoucherAccountItemDTO.class);

            if (ShroffConstant.CR.equals(entity.getDebitCredit())) {
                paymentVoucherAccountItemDTO.setAmount(entity.getVoucherAmount());
            }

            CaseAccountInfo caseAccountInfo = entity.getCaseAccount();
            if (caseAccountInfo != null) {
                paymentVoucherAccountItemDTO.setAccount(caseAccountInfoDTOAssembler.toDTO(caseAccountInfo));
                paymentVoucherAccountItemDTO
                    .setAccountTypeCodeValue(caseAccountInfo.getCaseAccountType().getCaseAcTypeCode());
                Case caseInfo = caseAccountInfo.getCaseInfo();
                if (caseInfo != null) {
                    paymentVoucherAccountItemDTO.setCaseNoValue(BusinessUtils.addZeroToCaseNo(caseInfo.getCaseNo()));
                    paymentVoucherAccountItemDTO.setCaseYearValue(String.valueOf(caseInfo.getCaseYear()));
                    paymentVoucherAccountItemDTO.setCaseTypeCodeValue(caseInfo.getCaseType().getCaseTypeCode());
                }
            }

            return paymentVoucherAccountItemDTO;
        }
        return null;
    }

}
