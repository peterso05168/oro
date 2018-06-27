package hk.oro.iefas.web.system.divisionadministration.post.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.vo.RankVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.divisionadministration.post.service.RankClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class RankClientServiceImpl extends BaseClientService implements RankClientService {

    @Override
    public List<RankVO> findAll() {
        log.info("findAll() start");
        ResponseEntity<List<RankVO>> responseEntity = exchange(RequestUriConstant.CLIENT_URI_RANK_LIST, HttpMethod.GET,
            null, new ParameterizedTypeReference<List<RankVO>>() {});
        List<RankVO> body = responseEntity.getBody();
        log.info("findAll() end - result = " + body);
        return body;
    }

    @Override
    public RankVO findOne(Integer rankId) {
        log.info("findOne() start - and rankId: " + rankId);
        ResponseEntity<RankVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_RANK_DETAIL, rankId, RankVO.class);
        RankVO body = responseEntity.getBody();
        log.info("findOne() end - result = " + body);
        return body;
    }

    @Override
    public RankVO findByRankName(String rankName) {
        log.info("findByRankName() start - and rankName = " + rankName);
        ResponseEntity<RankVO> responseEntity = exchange(RequestUriConstant.CLIENT_FIND_BY_RANK_NAME, HttpMethod.POST,
            new HttpEntity<String>(rankName), new ParameterizedTypeReference<RankVO>() {});
        RankVO result = responseEntity.getBody();
        log.info("findByRankName() end - " + result);
        return result;
    }

}
