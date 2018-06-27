package hk.oro.iefas.domain.funds.entity;

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

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FUND_PARAMETER")
public class FundsParameter extends UpdateTxnKeyable {
    @Id
    @Column(name = "FUNDS_PARAMETER_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Funds_Parameter_SEQ")
    @TableGenerator(name = "Funds_Parameter_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "FUNDS_PARAMETER_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer fundsParameterId;

    @Column(name = "FUNDS_PARAMETER_CODE", length = 50)
    private String fundsParameterCode;

    @Column(name = "FUNDS_PARAMETER_TYPE", length = 50)
    private String fundsParameterType;

    @Column(name = "FUNDS_PARAMETER_VALUE", length = 50)
    private String fundsParameterValue;

    @Column(name = "STATUS", length = 3)
    private String status;
}
