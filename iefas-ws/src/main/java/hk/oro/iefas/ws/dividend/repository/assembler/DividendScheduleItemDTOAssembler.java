package hk.oro.iefas.ws.dividend.repository.assembler;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AdjResult;
import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.dividend.dto.AdjucationResultDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleItemDTO;
import hk.oro.iefas.domain.dividend.dto.WithheldReasonDTO;
import hk.oro.iefas.domain.dividend.entity.DivSchedule;
import hk.oro.iefas.domain.dividend.entity.DivScheduleDist;
import hk.oro.iefas.domain.dividend.entity.DivScheduleItem;
import hk.oro.iefas.domain.dividend.entity.DivWithheldReasonType;
import hk.oro.iefas.ws.adjudication.repository.AdjResultRepository;
import hk.oro.iefas.ws.adjudication.repository.AppAdjResultItemRepository;
import hk.oro.iefas.ws.adjudication.repository.assembler.AdjucationResultDTOAssembler;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;
import hk.oro.iefas.ws.dividend.repository.DivScheduleDistRepository;
import hk.oro.iefas.ws.dividend.repository.predicate.AppAdjResultItemPredicate;
import hk.oro.iefas.ws.dividend.repository.predicate.DivScheduleDistPredicate;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
@Component
public class DividendScheduleItemDTOAssembler extends AssemblerSupport<DividendScheduleItemDTO, DivScheduleItem> {

    @Autowired
    private WithheldReasonDTOAssembler withheldReasonDTOAssembler;

    @Autowired
    private AdjucationResultDTOAssembler adjucationResultDTOAssembler;

    @Autowired
    private DividendScheduleDistDTOAssembler dividendScheduleDistDTOAssembler;

    @Autowired
    private DivScheduleDistRepository divScheduleDistRepository;

    @Autowired
    private AdjResultRepository adjResultRepository;

    @Autowired
    private AppAdjResultItemRepository appAdjResultItemRepository;

    @Override
    public DividendScheduleItemDTO toDTO(DivScheduleItem entity) {
        DividendScheduleItemDTO dto = null;
        if (entity != null) {
            dto = DataUtils.copyProperties(entity, DividendScheduleItemDTO.class);
            dto.setDividendScheduleItemId(entity.getDivScheduleItemId());
            dto.setDistributionAmount(entity.getDistAmount());
            dto.setDistributionPercentage(entity.getDistPercent());
            dto.setVoucherPart(entity.getVoucherPart());
            dto.setWithheldAmount(entity.getWithheldAmount());
            DivWithheldReasonType withheldReasonType = entity.getDivWithheldReasonType();
            if (withheldReasonType != null) {
                WithheldReasonDTO withheldReasonDTO = withheldReasonDTOAssembler.toDTO(withheldReasonType);
                dto.setWithheldReason(withheldReasonDTO);
            } else {
                dto.setWithheldReason(new WithheldReasonDTO());
            }
            AdjResult adjResult = adjResultRepository.findOne(entity.getAdjResultId());
            if (adjResult != null) {
                AdjucationResultDTO adjucationResultDTO = adjucationResultDTOAssembler.toDTO(adjResult);
                dto.setAdjResult(adjucationResultDTO);
                CreditorDTO creditorDTO = adjucationResultDTO.getCreditor();
                dto.setCreditor(creditorDTO);
                dto.setNatureOfClaim(adjResult.getNatureOfClaim());
                List<AppAdjResultItem> appAdjResultItemList = (List<AppAdjResultItem>)appAdjResultItemRepository
                    .findAll(AppAdjResultItemPredicate.findByAdjResultId(adjResult.getAdjResultId()));
                BigDecimal totalClaimAmount = BigDecimal.ZERO;
                if (appAdjResultItemList != null && !appAdjResultItemList.isEmpty()) {
                    for (AppAdjResultItem appAdjResultItem : appAdjResultItemList) {
                        totalClaimAmount = totalClaimAmount.add(appAdjResultItem.getAdmAmount());
                    }
                    dto.setTotalClaimAmount(totalClaimAmount);
                }
            }
            DivSchedule divSchedule = entity.getDivSchedule();
            if (divSchedule != null) {
                dto.setPaymentEffectiveDate(divSchedule.getPaymentDate());
            }
            List<DivScheduleDist> divScheduleDistList = (List<DivScheduleDist>)divScheduleDistRepository
                .findAll(DivScheduleDistPredicate.findByDivScheduleItemId(entity.getDivScheduleItemId()));
            if (divScheduleDistList != null && !divScheduleDistList.isEmpty()) {
                dto.setDividendScheduleDistList(dividendScheduleDistDTOAssembler.toDTOList(divScheduleDistList));
            }

        }
        return dto;
    }
}
