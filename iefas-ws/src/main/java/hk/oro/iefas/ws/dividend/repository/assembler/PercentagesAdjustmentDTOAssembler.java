package hk.oro.iefas.ws.dividend.repository.assembler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.casemgt.entity.CaseCred;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.PercentagesAdjustmentDTO;
import hk.oro.iefas.domain.dividend.entity.DivSchedule;
import hk.oro.iefas.ws.adjudication.repository.AppAdjResultItemRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CreditorDTOAssembler;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import hk.oro.iefas.ws.dividend.repository.predicate.AppAdjResultItemPredicate;

/**
 * @version $Revision: 2961 $ $Date: 2018-06-06 20:14:53 +0800 (週三, 06 六月 2018) $
 * @author $Author: noah.liang $
 */
@Component
public class PercentagesAdjustmentDTOAssembler extends PagingAssemblerSupport<PercentagesAdjustmentDTO, AdjResult> {

    @Autowired
    private CreditorDTOAssembler creditorDTOAssembler;

    @Autowired
    private DividendScheduleDTOAssembler dividendScheduleDTOAssembler;

    @Autowired
    private ApprovedAdjucationResultItemDTOAssembler approvedAdjucationResultItemDTOAssembler;

    @Autowired
    private CreditorTypeDTOAssembler creditorTypeDTOAssembler;

    @Autowired
    private AppAdjResultItemRepository appAdjResultItemRepository;

    public PercentagesAdjustmentDTO toDTO(AdjResult adjResult) {
        PercentagesAdjustmentDTO percentagesAdjustmentDTO = null;
        if (adjResult != null) {
            percentagesAdjustmentDTO = new PercentagesAdjustmentDTO();
            Integer adjResultId = adjResult.getAdjResultId();
            if (adjResultId != null && adjResultId.intValue() > 0) {
                percentagesAdjustmentDTO.setAdjudicationResultId(adjResultId);
            }
            CaseCred caseCred = adjResult.getCaseCred();
            if (caseCred != null) {
                percentagesAdjustmentDTO.setCreditor(creditorDTOAssembler.toDTO(caseCred));
                if (caseCred.getCaseInfo() != null) {
                    List<DivSchedule> divSchedules = adjResult.getCaseCred().getCaseInfo().getDivSchedules();
                    if (divSchedules != null) {
                        List<DividendScheduleDTO> divScheduleDTOList
                            = dividendScheduleDTOAssembler.toDTOList(divSchedules);
                        percentagesAdjustmentDTO.setDividendSchedule(divScheduleDTOList);
                    }
                }
            }
            List<AppAdjResultItem> appAdjResultItemList = (List<AppAdjResultItem>)appAdjResultItemRepository
                .findAll(AppAdjResultItemPredicate.findByAdjResultId(adjResult.getAdjResultId()));
            BigDecimal distributePercent = BigDecimal.valueOf(1);
            if (appAdjResultItemList != null) {
                appAdjResultItemList = appAdjResultItemList.stream()
                    .sorted((n1, n2) -> n1.getAdjType().getOrdering().compareTo(n2.getAdjType().getOrdering()))
                    .collect(Collectors.toList());
                for (AppAdjResultItem appAdj : appAdjResultItemList) {
                    if (distributePercent.compareTo(appAdj.getPercentPaid()) != 0) {
                        distributePercent
                            = appAdj.getPercentPaid().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                        break;
                    }
                }
            }
            percentagesAdjustmentDTO.setDistributedPrecentage(distributePercent);
            if (appAdjResultItemList != null) {
                percentagesAdjustmentDTO
                    .setAppAdjResultItem(approvedAdjucationResultItemDTOAssembler.toDTOList(appAdjResultItemList));
            }
            percentagesAdjustmentDTO.setCreditorType(creditorTypeDTOAssembler.toDTO(adjResult.getCreditorType()));
            percentagesAdjustmentDTO.setStatus(adjResult.getStatus());
        }
        return percentagesAdjustmentDTO;
    }

}
