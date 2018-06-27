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
 * The persistent class for the REL_HIST_LIST_ITEM database table.
 * 
 */
@Entity
@Table(name = "REL_HIST_LIST_ITEM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RelHistListItem extends UpdateTxnKeyable {

    @Id
    @Column(name = "HIST_CASE_LIST_ITEM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HIST_CASE_LIST_ITEM_SEQ")
    @TableGenerator(name = "HIST_CASE_LIST_ITEM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "HIST_CASE_LIST_ITEM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer histCaseListItemId;

    @Column(name = "CASE_NAME", length = 100)
    private String caseName;

    @Column(name = "CASE_NO", length = 5)
    private String caseNo;

    @Column(name = "CASE_OFFICER", length = 100)
    private String caseOfficer;

    @Column(name = "CASE_OFFICER_TEAM", length = 100)
    private String caseOfficerTeam;

    @Column(name = "CASE_TYPE", length = 3)
    private String caseType;

    @Column(name = "CASE_YEAR", length = 4)
    private String caseYear;

    @Column(name = "CASH_POSITION_AMOUNT", precision = 16, scale = 2)
    private BigDecimal cashPositionAmount;

    @Column(name = "CASH_POSITION_AMOUNT2", precision = 16, scale = 2)
    private BigDecimal cashPositionAmount2;

    @Temporal(TemporalType.DATE)
    @Column(name = "CASH_POSITION_DATE")
    private Date cashPositionDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "CASH_POSITION_DATE2")
    private Date cashPositionDate2;

    @Column(name = "ENCL_NO", precision = 15)
    private Integer enclNo;

    @Column(name = "FILE_NAME", length = 100)
    private String fileName;

    @Column(name = "FILE_NO", precision = 15)
    private Integer fileNo;

    @Column(name = "FROM_IO", length = 100)
    private String fromIo;

    @Column(name = "HIST_CASE_LIST_ID", precision = 15)
    private Integer histCaseListId;

    @Column(name = "HIST_CASE_LIST_ITEM_NO", precision = 15)
    private Integer histCaseListItemNo;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_TRANSACTION_DATE")
    private Date lastTransactionDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "MEMO_DATE")
    private Date memoDate;

    @Column(name = "MOMO_AMOUNT", precision = 16, scale = 2)
    private BigDecimal momoAmount;

    @Column(name = "OL_AGENT", length = 100)
    private String olAgent;

    @Temporal(TemporalType.DATE)
    @Column(name = "PROCESS_DATE")
    private Date processDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_ORDER_DATE")
    private Date releaseOrderDate;

    @Column(length = 300)
    private String remarks;

    @Column(name = "REMARKS_2", length = 100)
    private String remarks2;

    @Column(name = "REMARKS_3", length = 100)
    private String remarks3;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "TO_GR")
    private Date toGr;

    @Temporal(TemporalType.DATE)
    @Column(name = "WINDING_UP_ORDER_DATE")
    private Date windingUpOrderDate;

}