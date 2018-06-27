package hk.oro.iefas.domain.casemgt.entity;

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

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CASE_CASE_TYPE")
public class CaseType extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_TYPE_SEQ")
    @TableGenerator(name = "CASE_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer caseTypeId;

    @Column(name = "CASE_TYPE_CODE")
    private String caseTypeCode;

    @Column(name = "CASE_TYPE_DESC")
    private String caseTypeDesc;

    @Column(name = "DIV_REQUIRED")
    private String divRequired;

    @Column(name = "STATUS")
    private String status;
}
