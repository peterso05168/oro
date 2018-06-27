package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.AppAdjResultItem;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDistDTO;
import hk.oro.iefas.domain.dividend.entity.DivScheduleDist;
import hk.oro.iefas.domain.dividend.entity.DivScheduleItem;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

@Component
public class DividendScheduleDistDTOAssembler extends AssemblerSupport<DividendScheduleDistDTO, DivScheduleDist> {

    @Autowired
    private ApprovedAdjucationResultItemDTOAssembler approvedAdjucationResultItemDTOAssembler;

    @Override
    public DividendScheduleDistDTO toDTO(DivScheduleDist entity) {
        if (entity != null) {
            DividendScheduleDistDTO dto = DataUtils.copyProperties(entity, DividendScheduleDistDTO.class);
            AppAdjResultItem appAdjResultItem = entity.getAppAdjResultItem();
            if (appAdjResultItem != null) {
                dto.setAppAdjResultItem(approvedAdjucationResultItemDTOAssembler.toDTO(appAdjResultItem));
            }
            DivScheduleItem divScheduleItem = entity.getDivScheduleItem();
            if (divScheduleItem != null) {
                dto.setDivScheduleItemId(divScheduleItem.getDivScheduleItemId());
            }
            return dto;
        }
        return null;
    }

}
