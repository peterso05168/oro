package hk.oro.iefas.domain.counter.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProofOfDebtVO extends TxnVO {

    private Integer proofOfDebtId;
    private String commonCreditorName;
    private String commonCreditorNameChi;
    private String commonCreditorSeqNo;
    private String contactNo;
    private String contactPerson;
    private String creditorAddress1;
    private String creditorAddress2;
    private String creditorAddress3;
    private String creditorAddressChi1;
    private String creditorAddressChi2;
    private String creditorAddressChi3;
    private String proofNo;
    private Date receiptDate;
    private String refNo;
    private String sectionAddress1;
    private String sectionAddress2;
    private String sectionAddress3;
    private String sectionAddressChi1;
    private String sectionAddressChi2;
    private String sectionAddressChi3;
    private String sectionContactNoNo;
    private String sectionContactPerson;
    private String sectionName;
    private String sectionNameChi;
    private String sectionSeqNo;
    private String status;
    private BigDecimal uncapInt;
    private CaseVO caseInfo;
    private List<ProofOfDebtItemVO> proofDebtItems;
}
