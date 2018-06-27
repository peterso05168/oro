package hk.oro.iefas.web.dividend.maintenance.commoncreditor.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorVO;
import hk.oro.iefas.domain.casemgt.vo.SearchCommonCreditorCriteriaVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.maintenance.commoncreditor.service.DividendCommonCreditorClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class DividendCommonCreditorClientServiceImpl extends BaseClientService
    implements DividendCommonCreditorClientService {
    @Override
    public CommonCreditorVO searchCommonCreditor(Integer commonCreditorId) {
        log.info("searchCommonCreditor() start - commonCreditorId: " + commonCreditorId);
        ResponseEntity<CommonCreditorVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_COMMON_CREDITOR_DETAIL, commonCreditorId, CommonCreditorVO.class);
        CommonCreditorVO body = responseEntity.getBody();
        log.info("searchCommonCreditor() end - " + body);
        return body;
    }

    @Override
    public Integer saveCommonCreditor(CommonCreditorVO commonCreditorVO) {
        log.info("saveCommonCreditor() start - commonCreditorVO: " + commonCreditorVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_COMMON_CREDITOR_SAVE, commonCreditorVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveCommonCreditor() end - " + body);
        return body;
    }

    @Override
    public boolean existsByCommonCreditorName(SearchCommonCreditorCriteriaVO criteriaVO) {
        log.info("existsByCommonCreditorName() start - criteriaVO: " + criteriaVO);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_COMMON_CREDITOR_EXISTS, criteriaVO, Boolean.class);
        Boolean bl = responseEntity.getBody();
        log.info("existsByCommonCreditorName() end - " + bl);
        return bl;
    }

    @Override
    public List<CommonCreditorVO> searchAllActCommonCreditors() {
        ResponseEntity<List<CommonCreditorVO>> postForEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_COMMON_CREDITOR_ALL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CommonCreditorVO>>() {});
        List<CommonCreditorVO> body = postForEntity.getBody();
        log.info("searchAllActCommonCreditors end - return: " + body);
        return body;
    }

    @Override
    public List<CommonCreditorVO> searchCommonCreditorByName(String commonCreditorName) {
        log.info("searchCommonCreditorByName() start - commonCreditorName: " + commonCreditorName);
        ResponseEntity<List<CommonCreditorVO>> responseEntity = exchange(
            RequestUriConstant.CLIENT_URI_COMMON_CREDITOR_DETAIL_BY_NAME, HttpMethod.POST,
            new HttpEntity<String>(commonCreditorName), new ParameterizedTypeReference<List<CommonCreditorVO>>() {});
        List<CommonCreditorVO> body = responseEntity.getBody();
        log.info("searchCommonCreditorByName() end - " + body);
        return body;
    }

    @Override
    public List<CommonCreditorVO> searchCommonCreditorBySeqNo(String commonCreditorSeqNo) {
        log.info("searchCommonCreditorBySeqNo() start - commonCreditorName: " + commonCreditorSeqNo);
        ResponseEntity<List<CommonCreditorVO>> responseEntity = exchange(
            RequestUriConstant.CLIENT_URI_COMMON_CREDITOR_DETAIL_BY_SEQ_NO, HttpMethod.POST,
            new HttpEntity<String>(commonCreditorSeqNo), new ParameterizedTypeReference<List<CommonCreditorVO>>() {});
        List<CommonCreditorVO> body = responseEntity.getBody();
        log.info("searchCommonCreditorBySeqNo() end - " + body);
        return body;
    }
}
