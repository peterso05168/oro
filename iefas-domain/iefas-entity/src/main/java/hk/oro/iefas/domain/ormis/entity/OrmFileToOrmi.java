package hk.oro.iefas.domain.ormis.entity;

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
 * The persistent class for the ORM_FILE_TO_ORMIS database table.
 * 
 */
@Entity
@Table(name = "ORM_FILE_TO_ORMIS")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class OrmFileToOrmi extends UpdateTxnKeyable {

    @Id
    @Column(name = "ORMIS_FILE_SEQ", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ORMIS_FILE_SEQ")
    @TableGenerator(name = "ORMIS_FILE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ORMIS_FILE_SEQ", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long ormisFileSeq;

    @Column(name = "FILE_ID", precision = 15)
    private BigDecimal fileId;

    @Temporal(TemporalType.DATE)
    @Column(name = "RECORD_END_DATE")
    private Date recordEndDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "RECORD_START_DATE")
    private Date recordStartDate;

    @Column(name = "STATUS", length = 3)
    private String status;

}