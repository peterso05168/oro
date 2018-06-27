package hk.oro.iefas.domain.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SYS_SEQUENCE database table.
 * 
 */
@Entity
@Table(name = "SYS_SEQUENCE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SysSequence {

    @Id
    @Column(name = "SEQ_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_SEQ")
    @TableGenerator(name = "SEQ_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "SEQ_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer seqId;

    @Column(name = "SEQ_CODE", length = 100)
    private String seqCode;

    @Column(name = "SEQ_VALUE", length = 100)
    private String seqValue;

    @Column(name = "SEQ_YEAR", length = 4)
    private String seqYear;

}