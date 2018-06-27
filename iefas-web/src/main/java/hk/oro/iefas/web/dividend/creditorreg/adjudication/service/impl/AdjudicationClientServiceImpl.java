package hk.oro.iefas.web.dividend.creditorreg.adjudication.service.impl;

import javax.inject.Named;

import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.AdjucationVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.creditorreg.adjudication.service.AdjudicationClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (週日, 10 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
public class AdjudicationClientServiceImpl extends BaseClientService implements AdjudicationClientService {
    @Override
    public Integer saveAdjudication(AdjucationVO adjucationVO) {
        log.info("saveAdjudication() start - adjucationVO: " + adjucationVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_ADJUDICATION_SAVE, adjucationVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveAdjudication() end return : " + body);
        return body;
    }

    @Override
    public AdjucationVO searchAdjudication(Integer adjucationId) {
        log.info("searchAdjudication() start - adjucationId: " + adjucationId);
        ResponseEntity<AdjucationVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_ADJUDICATION_DETAIL, adjucationId, AdjucationVO.class);
        AdjucationVO body = responseEntity.getBody();
        log.info("searchAdjudication() end - " + body);
        return body;
    }

}
