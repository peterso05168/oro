package hk.oro.iefas.domain.shroff.entity;

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

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SHR_JOURNAL_TYPE")
public class JournalType {

    @Id
    @Column(name = "JOURNAL_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "JOURNAL_TYPE_SEQ")
    @TableGenerator(name = "JOURNAL_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "JOURNAL_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer journalTypeId;

    @Column(name = "JOURNAL_TYPE_CODE")
    private String journalTypeCode;

    @Column(name = "JOURNAL_TYPE_NAME")
    private String journalTypeName;

    @Column(name = "JOURNAL_TYPE_DESC")
    private String journalTypeDesc;

}
