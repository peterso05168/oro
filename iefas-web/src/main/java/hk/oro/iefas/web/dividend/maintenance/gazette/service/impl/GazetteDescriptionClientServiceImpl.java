package hk.oro.iefas.web.dividend.maintenance.gazette.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.GazetteVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.maintenance.gazette.service.GazetteDescriptionClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */

@Slf4j
@Named
public class GazetteDescriptionClientServiceImpl extends BaseClientService implements GazetteDescriptionClientService {

    @Override
    public Integer saveGazetteDescription(GazetteVO gazetteDescriptionVO) {
        log.info("saveGazetteDescription - start and gazetteDescriptionVO=" + gazetteDescriptionVO);
        Integer result = null;
        if (gazetteDescriptionVO != null) {
            ResponseEntity<Integer> response = this.postForEntity(
                RequestUriConstant.CLIENT_SAVE_URI_GAZETTE_DESCRIPTION, gazetteDescriptionVO, Integer.class);
            result = response.getBody();
        }
        log.info("saveGazetteDescription - end and result=" + result);
        return result;
    }

    @Override
    public GazetteVO searchGazetteById(Integer gazetteId) {
        log.info("searchGazetteById - start and gazetteId=" + gazetteId);
        GazetteVO result = null;
        if (gazetteId != null) {
            ResponseEntity<GazetteVO> response = this
                .postForEntity(RequestUriConstant.CLIENT_SEARCH_URI_GAZETTE_DESCRIPTION, gazetteId, GazetteVO.class);
            result = response.getBody();
        }
        log.info("searchGazetteById - end and result=" + result);
        return result;
    }

    @Override
    public List<GazetteVO> findAll() {
        log.info("findAll start -");
        ResponseEntity<List<GazetteVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_GAZETTES_DESCRIPTION_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<GazetteVO>>() {});

        List<GazetteVO> body = postForEntity.getBody();
        log.info("findAll end - return: " + body);
        return body;
    }
}
