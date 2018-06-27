package hk.oro.iefas.web.common.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.web.common.service.SysSequenceClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class SysSequenceClientServiceImpl extends BaseClientService implements SysSequenceClientService {

    @Override
    public Long generateIncrSeq(String seqCode) {
        log.info("generateIncrSeq - start seqCode =" + seqCode);
        ResponseEntity<Long> response = postForEntity(RequestUriConstant.CLIENT_URI_SYS_SEQ_GEN, seqCode, Long.class);
        Long result = response.getBody();
        log.info("generateIncrSeq - end return =" + result);
        return result;
    }

    @Override
    public String generateWithheldReason(String seqCode) {
        log.info("generateWithheldReason - start and seqCode =" + seqCode);
        ResponseEntity<String> response
            = postForEntity(RequestUriConstant.CLIENT_URI_WITHHELD_REASON_SEQ, seqCode, String.class);
        String result = response.getBody();
        log.info("generateWithheldReason - end and result =" + result);
        return result;
    }

    @Override
    public String generateCommonCreditorSeqNo(String seqCode) {
        log.info("generateCommonCreditorSeqNo - start and seqCode =" + seqCode);
        ResponseEntity<String> response
            = postForEntity(RequestUriConstant.CLIENT_URI_SYS_COMMON_CREDITOR_SEQ, seqCode, String.class);
        String result = response.getBody();
        log.info("generateCommonCreditorSeqNo - end and result =" + result);
        return result;
    }

}
