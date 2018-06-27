package hk.oro.iefas.domain.dividend.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
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
public class InterestTrialAdjudicationDTO extends TxnDTO {
    private Integer intTrAdjId;

    private Integer adjResultId;
    private CreditorDTO creditor;
    private String addressType;
    private String natureOfClaim;
    private BigDecimal claimAmount;
    private Date applicationDate;
    private String remark;
    private List<AdjucationAccountDTO> adjucationAccountList;
    private List<ApproveHistoryDTO> approveHistories;
    private CreditorTypeDTO creditorType;
    private List<AdjucationApplyItemDTO> adjucationApplyItems;
    private BigDecimal sumOfAppAdjRsltAmount;

    private Integer workFlowId;
    private BigDecimal totalIntAmount;
    private SysApprovalWfDTO sysApprovalWf;
    private List<AdjIntTrialAccItemDTO> adjIntTrialAccItemList;
    private List<AdjucationResultIntItemDTO> adjucationResultIntItemList;
    private String status;
}
