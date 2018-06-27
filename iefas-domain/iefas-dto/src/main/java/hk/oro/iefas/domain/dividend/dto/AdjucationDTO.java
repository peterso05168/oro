package hk.oro.iefas.domain.dividend.dto;

import java.util.Date;
import java.util.List;

import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.core.dto.TxnDTO;
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
public class AdjucationDTO extends TxnDTO {
    private Integer adjucationId;
    private CreditorDTO creditor;
    private CreditorTypeDTO creditorType;
    private String addressType;
    private String natureOfClaim;
    private String remark;
    private List<AdjucationAccountDTO> adjucationAccounts;
    private String submittedBy;
    private String approvedBy;
    private Date approvedDate;
    private Date submittedDate;
    private Integer workFlowId;
    private String status;
    private SysApprovalWfDTO sysApprovalWf;

    private List<AdjucationApplyItemDTO> adjucationApplyItems;
    private List<AdjucationItemDTO> adjucationItems;
    private List<AdjucationGroundDTO> groundNos;

    private List<PreAdjucationItemDTO> preAdjucationItems;

    private List<ApproveHistoryDTO> approveHistories;
}
