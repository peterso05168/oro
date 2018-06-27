package hk.oro.iefas.domain.dividend.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.common.UpdateSubmitApproveInfoAble;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2945 $ $Date: 2018-06-06 15:38:06 +0800 (週三, 06 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "DIV_DIVIDEND_CAL")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DividendCal extends UpdateSubmitApproveInfoAble {

    @Id
    @Column(name = "DIV_CAL_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DIV_CAL_SEQ")
    @TableGenerator(name = "DIV_CAL_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "DIV_CAL_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer divCalId;

    @Temporal(TemporalType.DATE)
    @Column(name = "DIV_DATE")
    private Date divDate;

    @Column(name = "DIV_TYPE", length = 100)
    private String divType;

    @Column(name = "STATUS", length = 3)
    private String status;

    @OneToMany(mappedBy = "calOfDivFeeId", fetch = FetchType.LAZY)
    private List<DivCalOfDiv> divCalOfDivs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    private Case caseInfo;

    @OneToMany(mappedBy = "divCalId", fetch = FetchType.LAZY)
    private List<DivCalCred> divDivCalCreds;

    @OneToMany(mappedBy = "provsnId", fetch = FetchType.LAZY)
    private List<DivProvision> divProvisions;

    @OneToMany(mappedBy = "divCalId", fetch = FetchType.LAZY)
    private List<ChargedOrFee> chargedOrFees;

    @Column(name = "WORKFLOW_ID")
    private Integer workFlowId;

    @Column(name = "CASE_AC_ID")
    private Integer caseAcId;

}