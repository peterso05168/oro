package hk.oro.iefas.domain.dividend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.casemgt.entity.CaseType;
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
@Table(name = "DIV_CASE_FEE_TYPE")
public class CaseFeeType extends UpdateTxnKeyable {

    @Id
    @Column(name = "CASE_FEE_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CASE_FEE_TYPE_SEQ")
    @TableGenerator(name = "CASE_FEE_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CASE_FEE_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer caseFeeTypeId;

    @Column(name = "CASE_FEE_TYPE_ITEM")
    private String caseFeeTypeItem;

    @Column(name = "CASE_FEE_TYPE_DESC")
    private String caseFeeTypeDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_TYPE_ID")
    private CaseType caseType;

    @Column(name = "STATUS")
    private String status;
}
