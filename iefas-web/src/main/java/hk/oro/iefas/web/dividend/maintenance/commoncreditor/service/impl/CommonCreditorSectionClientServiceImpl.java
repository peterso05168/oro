package hk.oro.iefas.web.dividend.maintenance.commoncreditor.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorSectionVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.maintenance.commoncreditor.service.CommonCreditorSectionClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class CommonCreditorSectionClientServiceImpl extends BaseClientService
    implements CommonCreditorSectionClientService {

    @Override
    public List<CommonCreditorSectionVO> searchCommonCreditorSectionByName(String commonCreditorSectionName) {
        log.info("searchCommonCreditorSectionByName() start - commonCreditorSectionName: " + commonCreditorSectionName);
        ResponseEntity<List<CommonCreditorSectionVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_COMMON_CREDITOR_SECTION_SEARCH_BY_NAME, HttpMethod.POST,
                new HttpEntity<String>(commonCreditorSectionName),
                new ParameterizedTypeReference<List<CommonCreditorSectionVO>>() {});
        List<CommonCreditorSectionVO> body = responseEntity.getBody();
        log.info("searchCommonCreditorSectionByName() end - " + body);
        return body;
    }

    @Override
    public Integer saveCommonCreditorSection(CommonCreditorSectionVO commonCreditorSectionVO) {
        log.info("saveCommonCreditorSection() start - commonCreditorSectionVO: " + commonCreditorSectionVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_COMMON_CREDITOR_SAVE, commonCreditorSectionVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveCommonCreditor() end - " + body);
        return body;
    }

    @Override
    public List<CommonCreditorSectionVO> searchCommonCreditorSectionBySeqNo(String commonCreditorSectionSeqNo) {
        log.info(
            "searchCommonCreditorSectionBySeqNo() start - commonCreditorSectionSeqNo: " + commonCreditorSectionSeqNo);
        ResponseEntity<List<CommonCreditorSectionVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_COMMON_CREDITOR_SECTION_SEARCH_BY_SEQ_NO, HttpMethod.POST,
                new HttpEntity<String>(commonCreditorSectionSeqNo),
                new ParameterizedTypeReference<List<CommonCreditorSectionVO>>() {});
        List<CommonCreditorSectionVO> body = responseEntity.getBody();
        log.info("searchCommonCreditorSectionBySeqNo() end - " + body);
        return body;
    }
}
