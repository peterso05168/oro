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
@Table(name = "CASE_ACCOUNT_SUB_TYPE")
public class CaseAccountSubType extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_ACT_TYPE_SUB_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_ACT_TYPE_SUB_SEQ")
    @TableGenerator(name = "CASE_ACT_TYPE_SUB_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_ACT_TYPE_SUB_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long caseActTypeSubId;

    @Column(name = "CASE_AC_TYPE_SUB_CODE", length = 100)
    private String caseAcTypeSubCode;

    @Column(name = "CASE_AC_TYPE_SUB_NAME", length = 100)
    private String caseAcTypeSubName;

    @Column(name = "STATUS", length = 3)
    private String status;

}