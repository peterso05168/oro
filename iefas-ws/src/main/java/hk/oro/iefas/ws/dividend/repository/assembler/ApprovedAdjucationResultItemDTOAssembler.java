package hk.oro.iefas.ws.dividend.repository.assembler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjType;
import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.dividend.dto.AdjudicationTypeDTO;
import hk.oro.iefas.domain.dividend.dto.ApprovedAdjucationResultItemDTO;
import hk.oro.iefas.domain.dividend.entity.DivScheduleDist;
import hk.oro.iefas.domain.dividend.entity.DivScheduleItem;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;
import hk.oro.iefas.ws.dividend.repository.DivScheduleDistRepository;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleDistPredicate;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
@Component
public class ApprovedAdjucationResultItemDTOAssembler
    extends AssemblerSupport<ApprovedAdjucationResultItemDTO, AppAdjResultItem> {

    @Autowired
    private AdjTypeDTOAssembler adjTypeDTOAssembler;

    @Autowired
    private DivScheduleDistRepository divScheduleDistRepository;

    @Override
    public ApprovedAdjucationResultItemDTO toDTO(AppAdjResultItem entity) {
        ApprovedAdjucationResultItemDTO adjucationResultItemDTO = null;
        if (entity != null) {
            adjucationResultItemDTO = new ApprovedAdjucationResultItemDTO();
            adjucationResultItemDTO.setAdjudicationType(adjTypeDTOAssembler.toDTO(entity.getAdjType()));
            adjucationResultItemDTO.setApprovedAdjucationResultItemId(entity.getAppResultItemId());
            adjucationResultItemDTO.setAdmittedAmount(entity.getAdmAmount());
            adjucationResultItemDTO.setAmountPaid(entity.getAmountPaid());
            adjucationResultItemDTO.setPercentPaid(entity.getPercentPaid());
            adjucationResultItemDTO.setStatus(entity.getStatus());
            List<DivScheduleDist> divScheduleDistList = (List<DivScheduleDist>)divScheduleDistRepository
                .findAll(DivScheduleDistPredicate.findByAppAdjResultItemId(entity.getAppResultItemId()));
            BigDecimal toBeDisTributeAmount = BigDecimal.ZERO;
            BigDecimal toBeDistributedPercent = BigDecimal.ZERO;
            if (divScheduleDistList != null && !divScheduleDistList.isEmpty()) {
                for (DivScheduleDist dist : divScheduleDistList) {
                    DivScheduleItem divScheduleItem = dist.getDivScheduleItem();
                    if (divScheduleItem != null
                        && !divScheduleItem.getStatus().equalsIgnoreCase(CoreConstant.SCHEDULE_STATUS_CONFIRMED)) {
                        toBeDisTributeAmount
                            = toBeDisTributeAmount.add(CommonUtils.getBigDecimal(dist.getAmountPaid()));
                    }
                }
            }
            adjucationResultItemDTO.setAmountToBeDistributed(toBeDisTributeAmount);

            if (entity.getAdmAmount().compareTo(BigDecimal.ZERO) != 0) {
                toBeDistributedPercent = toBeDisTributeAmount.divide(entity.getAdmAmount(), 4, RoundingMode.HALF_UP);
            }
            adjucationResultItemDTO.setPercentageToBeDistributed(toBeDistributedPercent);
        }
        return adjucationResultItemDTO;
    }

    public AppAdjResultItem toEntity(ApprovedAdjucationResultItemDTO param) {
        AppAdjResultItem adjucationResultItem = null;
        if (param != null) {
            adjucationResultItem = new AppAdjResultItem();
            AdjudicationTypeDTO adjTypeDTO = param.getAdjudicationType();
            AdjType adjType = adjTypeDTOAssembler.toEntity(adjTypeDTO);
            adjucationResultItem.setAdjType(adjType);
            adjucationResultItem.setAdjResultId(param.getApprovedAdjucationResultItemId());
            adjucationResultItem.setAdmAmount(param.getAdmittedAmount());
            adjucationResultItem.setAmountPaid(param.getAmountPaid());
            adjucationResultItem.setPercentPaid(param.getPercentPaid());
            adjucationResultItem.setStatus(param.getStatus());
        }
        return adjucationResultItem;
    }

}
