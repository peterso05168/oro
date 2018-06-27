package hk.oro.iefas.domain.adjudication.entity;

import java.math.BigDecimal;
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

import hk.oro.iefas.domain.casemgt.entity.CaseCred;
import hk.oro.iefas.domain.common.UpdateSubmitApproveInfoAble;
import hk.oro.iefas.domain.dividend.entity.DivCalCredDist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2743 $ $Date: 2018-05-30 19:14:53 +0800 (週三, 30 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "ADJ_RESULT")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AdjResult extends UpdateSubmitApproveInfoAble {

    @Id
    @Column(name = "ADJ_RESULT_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADJ_RESULT_SEQ")
    @TableGenerator(name = "ADJ_RESULT_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ADJ_RESULT_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer adjResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDITOR_TYPE_ID")
    private CredType creditorType;

    @Column(name = "LOCAL", length = 3)
    private String local;

    @Column(name = "NATURE_OF_CLAIM", length = 3)
    private String natureOfClaim;

    @Column(length = 300)
    private String remark;

    // bi-directional many-to-one association to CaseCred
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDITOR_ID")
    private CaseCred caseCred;

    @Column(name = "WORKFLOW_ID")
    private Integer workFlowId;

    @Column(name = "TOTAL_CLAIMED_AMOUNT", precision = 16, scale = 2)
    private BigDecimal totalClaimedAmount;

    @Column(name = "TOTAL_ADMITTED_AMOUNT", precision = 16, scale = 2)
    private BigDecimal totalAdmittedAmount;

    @Column(name = "TOTAL_REJECT_AMOUNT", precision = 16, scale = 2)
    private BigDecimal totalRejectAmount;

    // bi-directional many-to-one association to AdjAdjAccNumber
    @OneToMany(mappedBy = "adjResultId", fetch = FetchType.LAZY)
    private List<AdjAccNumber> adjAdjAccNumbers;

    @OneToMany(mappedBy = "adjResultId", fetch = FetchType.LAZY)
    private List<AdjApplyItem> adjApplyItems;

    // bi-directional many-to-one association to AdjResultIntItem
    @OneToMany(mappedBy = "adjResultId", fetch = FetchType.LAZY)
    private List<AdjResultItem> adjResultItems;

    @OneToMany(mappedBy = "adjResultId", fetch = FetchType.LAZY)
    private List<AdjResultGro> adjResultGros;

    @OneToMany(mappedBy = "adjResultId", fetch = FetchType.LAZY)
    private List<PreAdjResultItem> preAdjResultItems;

    // bi-directional many-to-one association to DivCalCredDist
    @OneToMany(mappedBy = "calCredDisId", fetch = FetchType.LAZY)
    private List<DivCalCredDist> divCalCredDists;

}