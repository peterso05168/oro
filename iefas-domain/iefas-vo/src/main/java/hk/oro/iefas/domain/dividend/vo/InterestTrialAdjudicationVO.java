package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3163 $ $Date: 2018-06-15 16:09:30 +0800 (週五, 15 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InterestTrialAdjudicationVO extends TxnVO {
    private Integer intTrAdjId;

    private Integer adjResultId;
    private CreditorVO creditor;
    private String addressType;
    private String natureOfClaim;
    private BigDecimal claimAmount;
    private Date applicationDate;
    private String remark;
    private List<AdjucationAccountVO> adjucationAccountList;
    private List<ApproveHistoryVO> approveHistories;
    private CreditorTypeVO creditorType;
    private List<AdjucationApplyItemVO> adjucationApplyItems;
    // sum of app_adj_rslt_item
    private BigDecimal sumOfAppAdjRsltAmount;

    private Integer workFlowId;
    // db value of adj_int_trial_adj
    private BigDecimal totalIntAmount;
    private SysApprovalWfVO sysApprovalWf;
    private List<AdjIntTrialAccItemVO> adjIntTrialAccItemList;
    private List<AdjucationResultIntItemVO> adjucationResultIntItemList;
    private String status;
}
