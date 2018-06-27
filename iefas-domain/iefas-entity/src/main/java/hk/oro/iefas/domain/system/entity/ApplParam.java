package hk.oro.iefas.domain.system.entity;

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
 * The persistent class for the APPL_PARAM database table.
 * 
 */
@Entity
@Table(name = "APPL_PARAM")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ApplParam extends UpdateTxnKeyable {

    @Id
    @Column(name = "APPL_PARAM_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "APPL_PARAM_SEQ")
    @TableGenerator(name = "APPL_PARAM_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "APPL_PARAM_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private long applParamId;

    @Column(name = "APPL_PARAM_DESC", length = 200)
    private String applParamDesc;

    @Column(name = "APPL_PARAM_VALUE", length = 100)
    private String applParamValue;

    @Column(name = "PARAM_CODE", length = 100)
    private String paramCode;

    @Column(name = "PARAM_NAME", length = 100)
    private String paramName;

    @Column(name = "STATUS", length = 3)
    private String status;

}