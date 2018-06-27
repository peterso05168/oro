package hk.oro.iefas.web.casemgt.maintenance.outsider.service.impl;

import javax.inject.Named;

import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.OutsiderVO;
import hk.oro.iefas.domain.casemgt.vo.SearchOutsiderDetailCriteriaVO;
import hk.oro.iefas.web.casemgt.maintenance.outsider.service.OutsiderClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class OutsiderClientServiceImpl extends BaseClientService implements OutsiderClientService {

    @Override
    public OutsiderVO getOutsiderDetail(Integer outsiderId) {

        log.info("getOutsiderDetail() start - outsiderId: " + outsiderId);
        ResponseEntity<OutsiderVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_GET_OUTSIDER_DETAIL, outsiderId, OutsiderVO.class);
        OutsiderVO body = responseEntity.getBody();
        log.info("getOutsiderDetail() end - " + body);
        return body;
    }

    @Override
    public Integer saveOutsider(OutsiderVO outsiderVO) {
        log.info("saveOutsider() start - " + outsiderVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_OUTSIDER, outsiderVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveOutsider() end - " + body);
        return body;
    }

    @Override
    public Boolean existsByOutsiderName(SearchOutsiderDetailCriteriaVO criteriaVO) {
        log.info("existsByOutsiderName() start - " + criteriaVO);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_VALIDATE_OUTSIDER, criteriaVO, Boolean.class);
        Boolean result = responseEntity.getBody();
        log.info("existsByOutsiderName() end - result = " + result);
        return result;
    }

}
