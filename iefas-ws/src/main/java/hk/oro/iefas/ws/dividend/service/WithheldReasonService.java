package hk.oro.iefas.ws.dividend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.dividend.dto.SearchWithheldReasonDTO;
import hk.oro.iefas.domain.dividend.dto.WithheldReasonDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 3087 $ $Date: 2018-06-12 18:02:20 +0800 (週二, 12 六月 2018) $
 * @author $Author: noah.liang $
 */

public interface WithheldReasonService {
    public Page<WithheldReasonDTO> searchWithheldReason(SearchDTO<SearchWithheldReasonDTO> withheldReasonDTO);

    public Integer saveWithheldReason(WithheldReasonDTO withheldReason);

    public WithheldReasonDTO searchByWithheldReasonId(Integer withheldReasonId);
    
    public List<WithheldReasonDTO> searchWithheldReasonList();
}
