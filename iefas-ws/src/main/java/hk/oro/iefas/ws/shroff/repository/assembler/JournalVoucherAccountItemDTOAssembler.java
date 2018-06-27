/**
 * 
 */
package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.BusinessUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseAccountInfoDTOAssembler;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 3208 $ $Date: 2018-06-20 10:50:21 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class JournalVoucherAccountItemDTOAssembler extends AssemblerSupport<JournalVoucherAccountItemDTO, ShrVcrItem> {

    @Autowired
    private CaseAccountInfoDTOAssembler caseAccountInfoDTOAssembler;

    @Override
    public JournalVoucherAccountItemDTO toDTO(ShrVcrItem entity) {
        if (entity != null) {
            JournalVoucherAccountItemDTO journalVoucherAccountItemDTO
                = DataUtils.copyProperties(entity, JournalVoucherAccountItemDTO.class);
            journalVoucherAccountItemDTO.setParticulars(entity.getNature());

            if (ShroffConstant.CR.equals(entity.getDebitCredit())) {
                journalVoucherAccountItemDTO.setAmountCr(entity.getVoucherAmount());
            } else if (ShroffConstant.DR.equals(entity.getDebitCredit())) {
                journalVoucherAccountItemDTO.setAmountDr(entity.getVoucherAmount());
            }

            CaseAccountInfo caseAccountInfo = entity.getCaseAccount();
            if (caseAccountInfo != null) {
                journalVoucherAccountItemDTO.setAccount(caseAccountInfoDTOAssembler.toDTO(caseAccountInfo));
                journalVoucherAccountItemDTO.setCaseName(caseAccountInfo.getCaseInfo().getCaseName());

                journalVoucherAccountItemDTO
                    .setAccountTypeCodeValue(caseAccountInfo.getCaseAccountType().getCaseAcTypeCode());
                Case caseInfo = caseAccountInfo.getCaseInfo();
                if (caseInfo != null) {
                    journalVoucherAccountItemDTO.setCaseNoValue(BusinessUtils.addZeroToCaseNo(caseInfo.getCaseNo()));
                    journalVoucherAccountItemDTO.setCaseYearValue(String.valueOf(caseInfo.getCaseYear()));
                    journalVoucherAccountItemDTO.setCaseTypeCodeValue(caseInfo.getCaseType().getCaseTypeCode());
                }
            }

            return journalVoucherAccountItemDTO;
        }
        return null;
    }

}
