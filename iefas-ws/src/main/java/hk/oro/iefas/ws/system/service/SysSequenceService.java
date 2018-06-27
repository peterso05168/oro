package hk.oro.iefas.ws.system.service;

public interface SysSequenceService {
    Long generateIncrSeq(String seqCode);

    Long generateTxnKeyRefNo();

    String generateWithheldReasonNo(String seqCode);

    String generateCommonCreditorSeqNo(String seqCode);
    
    String generateVoucherSeqNo(String seqCode);

    String generateTransferNo(String seqCode);

}
