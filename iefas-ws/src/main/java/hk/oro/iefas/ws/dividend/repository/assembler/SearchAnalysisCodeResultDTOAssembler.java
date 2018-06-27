package hk.oro.iefas.ws.dividend.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.shroff.dto.SearchAnalysisCodeResultDTO;
import hk.oro.iefas.domain.shroff.entity.AnalysisCode;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class SearchAnalysisCodeResultDTOAssembler
    extends PagingAssemblerSupport<SearchAnalysisCodeResultDTO, AnalysisCode> {

    @Override
    public SearchAnalysisCodeResultDTO toDTO(AnalysisCode entity) {
        if (entity != null) {
            SearchAnalysisCodeResultDTO result = new SearchAnalysisCodeResultDTO();
            result.setAnalysisCodeId(entity.getAnalysisCodeId());
            result.setAnalysisCode(entity.getAnalysisCode());
            result.setAnalysisCodeType(
                entity.getAnalysisCodeType() != null ? entity.getAnalysisCodeType().getAnalysisCodeTypeName() : null);
            result
                .setVoucherType(entity.getVoucherType() != null ? entity.getVoucherType().getVoucherTypeName() : null);
            result.setShortName(entity.getShortName());
            result.setFullName(entity.getFullName());
            result.setStatus(entity.getStatus());
            result.setRealizationFee(entity.getRealizationFee());
            return result;
        }
        return null;
    }

}
