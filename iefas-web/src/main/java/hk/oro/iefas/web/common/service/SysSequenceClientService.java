package hk.oro.iefas.web.common.service;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface SysSequenceClientService {

    Long generateIncrSeq(String seqCode);

    String generateWithheldReason(String seqCode);

    String generateCommonCreditorSeqNo(String seqCode);

}
