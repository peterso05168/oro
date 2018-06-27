package hk.oro.iefas.domain.shroff.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_CTL_AC_TYPE database table.
 * 
 */
@Entity
@Table(name = "SHR_CTL_AC_TYPE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrCtlAcType extends UpdateTxnKeyable {

    @Id
    @Column(name = "CTL_AC_TYPE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CTL_AC_TYPE_SEQ")
    @TableGenerator(name = "CTL_AC_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CTL_AC_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer ctlAcTypeId;

    @Column(name = "CTL_AC_TYPE_DESC", length = 200)
    private String ctlAcTypeDesc;

    @Column(name = "CTL_AC_TYPE_NAME", length = 100)
    private String ctlAcTypeName;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to ShrCtlAc
    @OneToMany(mappedBy = "ctlAcId", fetch = FetchType.LAZY)
    private List<ShrCtlAc> shrCtlAcs;

}