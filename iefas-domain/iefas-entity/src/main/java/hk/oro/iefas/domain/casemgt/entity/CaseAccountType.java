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
@Table(name = "CASE_ACCOUNT_TYPE")
public class CaseAccountType extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_AC_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_AC_TYPE_SEQ")
    @TableGenerator(name = "CASE_AC_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_AC_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer caseAcTypeId;

    @Column(name = "CASE_AC_TYPE_NAME", length = 10)
    private String caseAcTypeName;

    @Column(name = "CASE_AC_TYPE_CODE", length = 200)
    private String caseAcTypeCode;

    @Column(name = "STATUS", length = 3)
    private String status;

}
