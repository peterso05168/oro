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
 * @version $Revision: 3169 $ $Date: 2018-06-15 19:00:14 +0800 (週五, 15 六月 2018) $
 * @author $Author: noah.liang $
 */
@Entity
@Table(name = "DIV_SCHEDULE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivSchedule extends UpdateSubmitApproveInfoAble {

    @Id
    @Column(name = "DIV_SCHD_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DIV_SCHD_SEQ")
    @TableGenerator(name = "DIV_SCHD_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "DIV_SCHD_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer divSchdId;

    @Column(name = "CONFIRMED_BY", length = 100)
    private String confirmedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "CONFIRMED_DATE")
    private Date confirmedDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Column(name = "PLEGE_TYPE", length = 3)
    private String plegeType;

    @Column(name = "REVIEWED_BY", length = 100)
    private String reviewedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "REVIEWED_DATE")
    private Date reviewedDate;

    @Column(name = "SCHEDULE_TYPE", length = 3)
    private String scheduleType;

    @OneToMany(mappedBy = "caseFeeId", fetch = FetchType.LAZY)
    private List<CaseFee> divCaseFees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    private Case caseInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAL_OF_DIV_FEE_ID")
    private DividendCal divDividendCal;

    @OneToMany(mappedBy = "divScheduleItemId", fetch = FetchType.LAZY)
    private List<DivScheduleItem> divScheduleItems;
    
    @Column(name = "WORKFLOW_ID")
    private Integer workFlowId;
}