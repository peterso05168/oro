package hk.oro.iefas.ws.casemgt.repository.assembler;

import hk.oro.iefas.domain.casemgt.dto.SearchOutsiderDetailResultDTO;
import hk.oro.iefas.domain.casemgt.entity.Outsider;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @version $Revision: 2674 $ $Date: 2018-05-28 18:21:29 +0800 (週一, 28 五月 2018) $
 * @author $Author: garrett.han $
 */
@Component
public class SearchOutsiderDetailResultDTOAssembler
    extends PagingAssemblerSupport<SearchOutsiderDetailResultDTO, Outsider> {
    @Override
    public SearchOutsiderDetailResultDTO toDTO(Outsider entity) {
        SearchOutsiderDetailResultDTO result = null;
        if (entity != null) {
            result = new SearchOutsiderDetailResultDTO();
            result.setOutsiderId(entity.getOutsiderId());
            result.setOutsiderName(entity.getOutsiderName());
            result.setStatus(entity.getStatus());
            if (entity.getOutsiderType() != null) {
                result.setOutsiderTypeName(entity.getOutsiderType().getOutsiderTypeName());
            }
        }
        return result;
    }
}
