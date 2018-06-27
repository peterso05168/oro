package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.DividendChequeDTO;
import hk.oro.iefas.domain.dividend.entity.DivScheduleItem;
import hk.oro.iefas.domain.dividend.entity.DividendCheque;
import hk.oro.iefas.domain.shroff.entity.ShrCheque;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import hk.oro.iefas.ws.dividend.repository.DivScheduleItemRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.ChequeDTOAssembler;

@Component
public class DividendChequeDTOAssembler extends PagingAssemblerSupport<DividendChequeDTO, DividendCheque> {
    @Autowired
    private ChequeDTOAssembler chequeDTOAssembler;

    @Autowired
    private DividendScheduleItemDTOAssembler dividendScheduleItemDTOAssembler;

    @Autowired
    private DivScheduleItemRepository divScheduleItemRepository;

    @Override
    public DividendChequeDTO toDTO(DividendCheque entity) {
        if (entity != null) {
            DividendChequeDTO dto = DataUtils.copyProperties(entity, DividendChequeDTO.class);
            ShrCheque shrCheque = entity.getShrCheque();
            if (shrCheque != null) {
                dto.setCheque(chequeDTOAssembler.toDTO(shrCheque));
            }
            Integer dividendScheduleItemId = entity.getDivScheduleItemId();
            if (dividendScheduleItemId != null && dividendScheduleItemId.intValue() > 0) {
                DivScheduleItem divScheduleItem = divScheduleItemRepository.findOne(dividendScheduleItemId);
                if (divScheduleItem != null) {
                    dto.setDividendScheduleItem(dividendScheduleItemDTOAssembler.toDTO(divScheduleItem));
                }
            }
            return dto;
        }
        return null;
    }

}
