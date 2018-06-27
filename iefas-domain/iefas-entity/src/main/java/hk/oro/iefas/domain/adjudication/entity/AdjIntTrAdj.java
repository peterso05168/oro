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

import hk.oro.iefas.domain.common.UpdateSubmitApproveInfoAble;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ADJ_INT_TR_ADJ")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AdjIntTrAdj extends UpdateSubmitApproveInfoAble {

    @Id
    @Column(name = "INT_TR_ADJ_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADJ_INT_TR_ADJ_SEQ")
    @TableGenerator(name = "ADJ_INT_TR_ADJ_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "INT_TR_ADJ_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer intTrAdjId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADJ_RESULT_ID")
    private AdjResult adjResult;

    @Column(name = "WORKFLOW_ID")
    private Integer workFlowId;

    @Column(name = "TOTAL_INT_AMOUNT")
    private BigDecimal totalIntAmount;

    @OneToMany(mappedBy = "adjIntTrAdjId", fetch = FetchType.LAZY)
    private List<AdjIntTrialAccItem> adjIntTrialAccItem;

    @Column(name = "STATUS")
    private String status;

}
