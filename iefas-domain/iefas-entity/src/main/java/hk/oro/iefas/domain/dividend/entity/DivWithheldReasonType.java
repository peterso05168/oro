package hk.oro.iefas.domain.dividend.entity;

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
@Table(name = "DIV_WITHHELD_REASON_TYPE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivWithheldReasonType extends UpdateTxnKeyable {

    @Id
    @Column(name = "WITHHELD_RSN_TYPE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "WITHHELD_RSN_TYPE_SEQ")
    @TableGenerator(name = "WITHHELD_RSN_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "WITHHELD_RSN_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer withheldRsnTypeId;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "WITHHELD_RSN_CODE", length = 10)
    private String withheldRsnCode;

    @Column(name = "WITHHELD_RSN_DESC", length = 100)
    private String withheldRsnDesc;

    @Column(name = "WITHHELD_RSN_DESC_CHI", length = 300)
    private String withheldRsnDescChi;

}