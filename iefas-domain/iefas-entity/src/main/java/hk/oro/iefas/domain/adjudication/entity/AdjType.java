package hk.oro.iefas.domain.adjudication.entity;

import java.math.BigDecimal;

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
@Entity
@Table(name = "ADJ_TYPE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AdjType extends UpdateTxnKeyable {

    @Id
    @Column(name = "ADJ_TYPE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADJ_TYPE_SEQ")
    @TableGenerator(name = "ADJ_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ADJ_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer adjTypeId;

    @Column(name = "ADJ_TYPE_DESC", length = 30)
    private String adjTypeDesc;

    @Column(name = "ORDERING", length = 15)
    private BigDecimal ordering;
}