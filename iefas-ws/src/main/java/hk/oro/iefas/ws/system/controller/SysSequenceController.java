package hk.oro.iefas.ws.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.ws.system.service.SysSequenceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.ROOT_SYS_SEQ)
public class SysSequenceController {

    @Autowired
    private SysSequenceService sysSequenceService;

    @PostMapping(value = RequestUriConstant.URI_SYS_SEQ_GEN)
    public Long generateIncrSeq(@RequestBody String seqCode) throws Exception {
        log.info("generateIncrSeq start - seqCode =" + seqCode);
        Long result = sysSequenceService.generateIncrSeq(seqCode);
        log.info("generateIncrSeq end - return " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_SYS_WITHHELD_REASON_SEQ)
    public String generateWithheldReasonNo(@RequestBody String seqCode) throws Exception {
        log.info("generateWithheldReasonNo start - seqCode =" + seqCode);
        String result = sysSequenceService.generateWithheldReasonNo(seqCode);
        log.info("generateWithheldReasonNo end - return " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_SYS_COMMON_CREDITOR_SEQ)
    public String generateCommonCreditorSeqNo(@RequestBody String seqCode) throws Exception {
        log.info("generateCommonCreditorSeqNo start - seqCode =" + seqCode);
        String result = sysSequenceService.generateCommonCreditorSeqNo(seqCode);
        log.info("generateCommonCreditorSeqNo end - return " + result);
        return result;
    }

}
