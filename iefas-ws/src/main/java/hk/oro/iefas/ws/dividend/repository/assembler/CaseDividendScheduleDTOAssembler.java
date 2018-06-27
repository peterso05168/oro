package hk.oro.iefas.ws.dividend.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.dividend.dto.CaseDividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDTO;
import hk.oro.iefas.domain.dividend.entity.DivSchedule;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseNumberDTOAssembler;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class CaseDividendScheduleDTOAssembler {

    @Autowired
    private DividendScheduleDTOAssembler dividendScheduleDTOAssembler;

    public Page<CaseDividendScheduleDTO> toResultDTO(Page<DivSchedule> page) {
        List<CaseDividendScheduleDTO> dtos = null;
        if (page != null) {
            dtos = new ArrayList<>(1);
            List<DivSchedule> content = page.getContent();
            if (CommonUtils.isNotEmpty(content)) {
                CaseDividendScheduleDTO dto = new CaseDividendScheduleDTO();
                DivSchedule divSchedule = content.get(0);
                if (divSchedule != null) {
                    Case caseInfo = divSchedule.getCaseInfo();
                    if (caseInfo != null) {
                        CaseNumberDTO caseNumberDTO = CaseNumberDTOAssembler.toDTO(caseInfo);
                        dto.setCaseNumber(caseNumberDTO);
                    }

                }

                List<DividendScheduleDTO> list = dividendScheduleDTOAssembler.toDTOList(content);
                dto.setDividendSchedules(list);

                dtos.add(dto);
            }
        }

        return new PageImpl<>(dtos, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
            page.getTotalElements());
    }
}
