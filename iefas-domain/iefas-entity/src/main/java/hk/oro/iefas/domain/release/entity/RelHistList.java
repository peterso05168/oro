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
 * The persistent class for the REL_HIST_LIST database table.
 * 
 */
@Entity
@Table(name = "REL_HIST_LIST")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RelHistList extends UpdateTxnKeyable {

    @Id
    @Column(name = "HIST_CASE_LIST_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HIST_CASE_LIST_SEQ")
    @TableGenerator(name = "HIST_CASE_LIST_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "HIST_CASE_LIST_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer histCaseListId;

    @Temporal(TemporalType.DATE)
    @Column(name = "HIST_CASE_LIST_DATE")
    private Date histCaseListDate;

    @Column(name = "HIST_CASE_LIST_DESC", length = 200)
    private String histCaseListDesc;

    @Column(name = "STATUS", length = 3)
    private String status;

}