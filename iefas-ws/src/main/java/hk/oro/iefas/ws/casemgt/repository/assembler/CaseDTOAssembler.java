package hk.oro.iefas.ws.casemgt.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import hk.oro.iefas.core.util.BusinessUtils;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchCaseDetailResultDTO;
import hk.oro.iefas.domain.casemgt.entity.Case;

/**
 * @version $Revision: 3208 $ $Date: 2018-06-20 10:50:21 +0800 (週三, 20 六月 2018) $
 * @author $Author: dante.fang $
 */
public class CaseDTOAssembler {

    public static Page<SearchCaseDetailResultDTO> toResultDTO(Page<Case> page) {
        List<Case> content = page.getContent();
        List<SearchCaseDetailResultDTO> dtoList = new ArrayList<>();
        if (CommonUtils.isNotEmpty(content)) {
            content.stream().forEach(item -> {
                SearchCaseDetailResultDTO dto = new SearchCaseDetailResultDTO();
                dto.setCaseId(item.getCaseId());
                dto.setCaseName(item.getCaseName());
                dto.setCaseNo(BusinessUtils.addZeroToCaseNo(item.getCaseNo()));
                dto.setCaseTypeCode(item.getCaseType().getCaseTypeCode());
                dto.setCaseTypeDesc(item.getCaseType().getCaseTypeDesc());
                dto.setCaseYear(String.valueOf(item.getCaseYear()));
                dto.setCaseNumber(item.getWholeCaseNo());
                dto.setOutsiderName(item.getOutsider() != null ? item.getOutsider().getOutsiderName() : null);
                dto.setOutsiderTypeName(
                    item.getOutsider() != null ? item.getOutsider().getOutsiderType().getOutsiderTypeDesc() : null);
                dto.setHandlingOfficerPostTitle(item.getPost().getPostTitle());
                dto.setStatus(item.getStatus());
                dtoList.add(dto);
            });
        }
        return new PageImpl<>(dtoList, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
            page.getTotalElements());
    }

    public static CaseDTO toDTO(Case caseInfo) {
        CaseDTO dto = null;
        if (caseInfo != null) {
            dto = DataUtils.copyProperties(caseInfo, CaseDTO.class);
        }
        return dto;
    }

}
