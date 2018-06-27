package hk.oro.iefas.web.dividend.maintenance.withheldreasons.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.SearchWithheldReasonVO;
import hk.oro.iefas.domain.dividend.vo.WithheldReasonVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.maintenance.withheldreasons.service.WithheldReasonsClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3087 $ $Date: 2018-06-12 18:02:20 +0800 (週二, 12 六月 2018) $
 * @author $Author: noah.liang $
 */

@Slf4j
@Named
public class WithheldReasonsClientServiceImpl extends BaseClientService implements WithheldReasonsClientService {

    @Override
    public Integer saveWithheldReason(WithheldReasonVO withheldReasonVO) {
        log.info(" saveWithheldReason - start and withheldReasonVO =" + withheldReasonVO);
        ResponseEntity<Integer> response
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_WITHHELD_REASON, withheldReasonVO, Integer.class);
        Integer result = response.getBody();
        log.info(" saveWithheldReason - end and result=" + result);
        return result;
    }

    @Override
    public Page<WithheldReasonVO> searchWithheldReason(SearchVO<SearchWithheldReasonVO> searchCriteria) {
        log.info(" searchWithheldReason - start and searchCriteria =" + searchCriteria);
        HttpEntity<SearchVO<SearchWithheldReasonVO>> entity = new HttpEntity<>(searchCriteria);
        ResponseEntity<Page<WithheldReasonVO>> response
            = this.exchange(RequestUriConstant.CLIENT_URI_SEARCH_WITHHELD_REASON, HttpMethod.POST, entity,
                new ParameterizedTypeReference<Page<WithheldReasonVO>>() {});
        Page<WithheldReasonVO> result = response.getBody();
        log.info(" searchWithheldReason - end and result =" + result);
        return result;
    }

    @Override
    public WithheldReasonVO searchByWithheldReasonId(Integer withheldReasonId) {
        log.info(" searchByWithheldReasonId - start and withheldReasonId =" + withheldReasonId);
        ResponseEntity<WithheldReasonVO> response = this.postForEntity(
            RequestUriConstant.CLIENT_URI_SEARCH_WITHHELD_REASON_BY_ID, withheldReasonId, WithheldReasonVO.class);
        WithheldReasonVO result = response.getBody();
        log.info(" searchByWithheldReasonId - end and result =" + result);
        return result;
    }

    @Override
    public List<WithheldReasonVO> searchWithheldReasonList() {
        log.info(" searchByWithheldReasonId - start ");
        ResponseEntity<List<WithheldReasonVO>> response
            = this.exchange(RequestUriConstant.CLIENT_URI_SEARCH_WITHHELD_REASON_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<WithheldReasonVO>>() {});
        List<WithheldReasonVO> result = response.getBody();
        log.info(" searchByWithheldReasonId - end and result =" + result);
        return result;
    }
}
