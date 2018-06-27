package hk.oro.iefas.ws.dividend.repository.assembler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.DividendCalculationCreditorDTO;
import hk.oro.iefas.domain.dividend.dto.DividendCalculationDTO;
import hk.oro.iefas.domain.dividend.entity.ChargedOrFee;
import hk.oro.iefas.domain.dividend.entity.DivCalCred;
import hk.oro.iefas.domain.dividend.entity.DividendCal;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import hk.oro.iefas.ws.dividend.repository.ChargedOrFeeRepository;
import hk.oro.iefas.ws.dividend.repository.DivCalCredRepository;
import hk.oro.iefas.ws.dividend.repository.predicate.ChargedOrFeePredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivCalCredPredicate;

/**
 * @version $Revision: 2945 $ $Date: 2018-06-06 15:38:06 +0800 (週三, 06 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class DividendCalculationDTOAssembler extends PagingAssemblerSupport<DividendCalculationDTO, DividendCal> {
    @Autowired
    private DivCalCredRepository divCalCredRepository;

    @Autowired
    private ChargedOrFeeRepository chargedOrFeeRepository;

    @Autowired
    private DividendCalculationCreditorDTOAssembler dividendCalculationCreditorDTOAssembler;

    @Override
    public DividendCalculationDTO toDTO(DividendCal entity) {
        DividendCalculationDTO dto = null;
        if (entity != null) {
            Integer divCalId = entity.getDivCalId();
            dto = DataUtils.copyProperties(entity, DividendCalculationDTO.class);
            dto.setDividendCalculationId(divCalId);
            dto.setPaymentDate(entity.getDivDate());
            dto.setPaymentType(entity.getDivType());

            List<DivCalCred> divCalCreds
                = (List<DivCalCred>)divCalCredRepository.findAll(DivCalCredPredicate.findByDivCal(divCalId));
            if (CommonUtils.isNotEmpty(divCalCreds)) {
                StringBuilder sb = new StringBuilder();
                List<DividendCalculationCreditorDTO> dividendCalculationCreditors = new ArrayList<>();
                divCalCreds.forEach(calCred -> {
                    DividendCalculationCreditorDTO dividendCalculationCreditor
                        = dividendCalculationCreditorDTOAssembler.toDTO(calCred);
                    dividendCalculationCreditors.add(dividendCalculationCreditor);
                    sb.append(dividendCalculationCreditor.getCreditorTypeName()).append(",");
                });
                dto.setDividendCalculationCreditors(dividendCalculationCreditors);
                if (sb != null && sb.length() > 0) {
                    dto.setCreditorTypeStr(sb.toString().substring(0, (sb.length() - 1)));
                }
            }

            // to set analysis codes
            List<ChargedOrFee> chargedOrFees
                = (List<ChargedOrFee>)chargedOrFeeRepository.findAll(ChargedOrFeePredicate.findByDivCal(divCalId));
            if (CommonUtils.isNotEmpty(chargedOrFees)) {
                StringBuilder analysisCodes = new StringBuilder();
                StringBuilder chargedAmounts = new StringBuilder();
                chargedOrFees.forEach(chargedOrFee -> {
                    String analysisCode = chargedOrFee.getAnalysisCode().getAnalysisCode();
                    analysisCodes.append(analysisCode).append("/n");
                    BigDecimal chargedAmount = chargedOrFee.getChargedAmount();
                    chargedAmounts.append(chargedAmount != null ? chargedAmount : BigDecimal.ZERO).append("/n");
                });
                dto.setAnalysisCodes(analysisCodes.toString());
                dto.setChargedAmounts(chargedAmounts.toString());
            }
        }
        return dto;
    }
}
