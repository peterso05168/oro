package hk.oro.iefas.domain.release.entity;

import java.io.Serializable;
import javax.persistence.*;
import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the REL_INST_LIST_ITEM database table.
 * 
 */
@Entity
@Table(name = "REL_INST_LIST_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RelInstListItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_LIST_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_LIST_ITEM_SEQ")
    @TableGenerator(name = "CASE_LIST_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_LIST_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long caseListItemId;

    @Column(name = "CASE_ID", precision = 15)
    private BigDecimal caseId;

    @Column(name = "EMAIL_CONFIRMATION", length = 2)
    private String emailConfirmation;

    @Column(name = "FROM_IO", length = 100)
    private String fromIo;

    @Column(name = "MEMO_AMOUNT", precision = 16, scale = 2)
    private BigDecimal memoAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "MEMO_DATE")
    private Date memoDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_ORDER_DATE")
    private Date releaseOrderDate;

    @Column(length = 100)
    private String remarks;

    @Column(name = "REMARKS_2", length = 100)
    private String remarks2;

    @Column(name = "REMARKS_3", length = 100)
    private String remarks3;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "TO_GR_DATE")
    private Date toGrDate;

    // bi-directional many-to-one association to RelInstList
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_LIST_ID")
    private RelInstList relInstList;

}