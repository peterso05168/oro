package hk.oro.iefas.domain.ormis.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORM_ORMIS_FILE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class OrmOrmisFile extends UpdateTxnKeyable {

    @Id
    @Column(name = "FILE_TO_ORMIS_SEQ")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FILE_TO_ORMIS_SEQ")
    @TableGenerator(name = "FILE_TO_ORMIS_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "FILE_TO_ORMIS_SEQ", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long fileToOrmisSeq;

    @Column(name = "FILE_ID")
    private Long fileId;

    @Column(name = "RECORD_START_DATE")
    private Date recordStartDate;

    @Column(name = "RECORD_END_DATE")
    private Date recordEndDate;

    @Column(name = "STATUS", length = 3)
    private String status;
}
