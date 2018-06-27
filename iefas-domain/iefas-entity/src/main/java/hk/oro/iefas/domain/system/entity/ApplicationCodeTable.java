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

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "APPLICATION_CODE_TABLE")
public class ApplicationCodeTable {

    @Id
    @Column(name = "APPLICATION_CODE_TABLE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "APPLICATION_CODE_TABLE_SEQ")
    @TableGenerator(name = "APPLICATION_CODE_TABLE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "APPLICATION_CODE_TABLE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer applicationCodeTableId;

    @Column(name = "GROUPING_CODE")
    private String groupingCode;

    @Column(name = "CODE_VALUE")
    private String codeValue;

    @Column(name = "CODE_DESC")
    private String codeDesc;
}
