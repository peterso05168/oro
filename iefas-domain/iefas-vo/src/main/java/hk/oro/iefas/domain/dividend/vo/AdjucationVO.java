package hk.oro.iefas.domain.dividend.vo;

import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
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
public class AdjucationVO extends TxnVO {
    private Integer adjucationId;
    private CreditorVO creditor;
    private CreditorTypeVO creditorType;
    private String addressType;
    private String natureOfClaim;
    private String remark;
    private List<AdjucationAccountVO> adjucationAccounts;
    private String submittedBy;
    private String approvedBy;
    private Date approvedDate;
    private Date submittedDate;
    private Integer workFlowId;
    private String status;
    private SysApprovalWfVO sysApprovalWf;

    // =======================ORDINARY=================================
    private List<AdjucationApplyItemVO> adjucationApplyItems;
    private List<AdjucationItemVO> adjucationItems;
    private List<AdjucationGroundVO> groundNos;
    // =======================ORDINARY=================================

    // ========================PREFERENTIAL============================
    private List<PreAdjucationItemVO> preAdjucationItems;
    // ========================PREFERENTIAL============================

    private List<ApproveHistoryVO> approveHistories;
}
