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
@Table(name = "CASE_OUTSIDER_TYPE")
public class OutsiderType extends UpdateTxnKeyable {

    @Id
    @Column(name = "OUTSIDER_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "OUTSIDER_TYPE_SEQ")
    @TableGenerator(name = "OUTSIDER_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "OUTSIDER_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer outsiderTypeId;

    @Column(name = "OUTSIDER_TYPE_NAME")
    private String outsiderTypeName;

    @Column(name = "OUTSIDER_TYPE_CODE")
    private String outsiderTypeCode;

    @Column(name = "OUTSIDER_TYPE_DESC")
    private String outsiderTypeDesc;

    @Column(name = "STATUS")
    private String status;
}
