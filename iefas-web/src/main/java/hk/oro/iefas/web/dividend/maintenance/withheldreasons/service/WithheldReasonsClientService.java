package hk.oro.iefas.web.dividend.maintenance.withheldreasons.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.dividend.vo.SearchWithheldReasonVO;
import hk.oro.iefas.domain.dividend.vo.WithheldReasonVO;
import hk.oro.iefas.domain.search.vo.SearchVO;

/**
 * @version $Revision: 3087 $ $Date: 2018-06-12 18:02:20 +0800 (週二, 12 六月 2018) $
 * @author $Author: noah.liang $
 */
public interface WithheldReasonsClientService {

    public Integer saveWithheldReason(WithheldReasonVO withheldReasonVO);

    public Page<WithheldReasonVO> searchWithheldReason(SearchVO<SearchWithheldReasonVO> searchCriteria);

    public WithheldReasonVO searchByWithheldReasonId(Integer withheldReasonId);

    public List<WithheldReasonVO> searchWithheldReasonList();
}
