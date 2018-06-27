package hk.oro.iefas.ws.dividend.repository.assembler;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleItemDTO;
import hk.oro.iefas.domain.dividend.entity.DivSchedule;
import hk.oro.iefas.domain.dividend.entity.DivScheduleCred;
import hk.oro.iefas.domain.dividend.entity.DivScheduleItem;
import hk.oro.iefas.ws.casemgt.repository.CaseRepository;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import hk.oro.iefas.ws.dividend.repository.DivScheduleItemRepository;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleCredPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleCredRepository;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleItemPredicate;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
@Component
public class DividendScheduleDTOAssembler extends PagingAssemblerSupport<DividendScheduleDTO, DivSchedule> {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private DividendScheduleItemDTOAssembler dividendScheduleItemDTOAssembler;

    @Autowired
    private DividendScheduleCreditorDTOAssembler dividendScheduleCreditorDTOAssembler;

    @Autowired
    private DivScheduleCredRepository divScheduleCredRepository;

    @Autowired
    private DivScheduleItemRepository divScheduleItemRepository;

    @Override
    public DividendScheduleDTO toDTO(DivSchedule entity) {
        DividendScheduleDTO dividendScheduleDTO = null;
        if (entity != null) {
            dividendScheduleDTO = DataUtils.copyProperties(entity, DividendScheduleDTO.class);
            dividendScheduleDTO.setDividendScheduleId(entity.getDivSchdId());
            dividendScheduleDTO.setPledgeType(entity.getPlegeType());
            dividendScheduleDTO.setPaymentEffectiveDate(entity.getPaymentDate());
            List<DivScheduleItem> divScheduleItems = (List<DivScheduleItem>)divScheduleItemRepository
                .findAll(DivScheduleItemPredicate.findByDivScheduleId(entity.getDivSchdId()));
            if (CommonUtils.isNotEmpty(divScheduleItems)) {
                BigDecimal totalDisAmount = BigDecimal.ZERO;
                for (DivScheduleItem divScheduleItem : divScheduleItems) {
                    totalDisAmount = totalDisAmount.add(divScheduleItem.getDistAmount());
                }
                dividendScheduleDTO.setTotalDistributedAmount(totalDisAmount);
                List<DividendScheduleItemDTO> dividendScheduleItemDTOList
                    = dividendScheduleItemDTOAssembler.toDTOList(divScheduleItems);
                dividendScheduleDTO.setDividendScheduleItems(dividendScheduleItemDTOList);
            }
            dividendScheduleDTO.setSubmittedBy(entity.getSubmittedBy());
            dividendScheduleDTO.setSubmittedDate(entity.getSubmittedDate());
            List<DivScheduleCred> divScheduleCredList = (List<DivScheduleCred>)divScheduleCredRepository
                .findAll(DivScheduleCredPredicate.findByScheduleId(entity.getDivSchdId()));
            if (divScheduleCredList != null && !divScheduleCredList.isEmpty()) {
                dividendScheduleDTO.setDividendScheduleCreditorList(
                    dividendScheduleCreditorDTOAssembler.toDTOList(divScheduleCredList));
            }
            Case caseInfo = entity.getCaseInfo();
            if (caseInfo != null) {
                Integer caseId = caseInfo.getCaseId();
                dividendScheduleDTO.setCaseId(caseId);
            }

        }
        return dividendScheduleDTO;
    }

    public DivSchedule toEntity(DividendScheduleDTO dividendScheduleDTO) {
        if (dividendScheduleDTO != null) {
            DivSchedule divSchedule = new DivSchedule();
            divSchedule.setPaymentDate(dividendScheduleDTO.getPaymentEffectiveDate());
            divSchedule.setPlegeType(dividendScheduleDTO.getPledgeType());
            divSchedule.setScheduleType(dividendScheduleDTO.getScheduleType());
            divSchedule.setStatus(dividendScheduleDTO.getStatus());
            Integer caseId = dividendScheduleDTO.getCaseId();
            if (caseId != null && caseId.intValue() > 0) {
                Case caseInfo = caseRepository.findOne(caseId);
                divSchedule.setCaseInfo(caseInfo);
            }
            return divSchedule;
        }
        return null;
    }

}
