package hk.oro.iefas.domain.adjudication.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Entity
@Table(name = "ADJ_GRO_CODE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class GroCode extends UpdateTxnKeyable {

    @Id
    @Column(name = "GROUND_CODE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GROUND_CODE_SEQ")
    @TableGenerator(name = "GROUND_CODE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "GROUND_CODE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer groundCodeId;

    @Column(name = "GROUND_CODE_CODE", length = 3)
    private String groundCodeCode;

    @Column(name = "GROUND_CODE_DESC", length = 400)
    private String groundCodeDesc;

    @Column(name = "GROUND_CODE_DESC_CHI", length = 600)
    private String groundCodeDescChi;

    @Column(name = "STATUS", length = 3)
    private String status;

    // bi-directional many-to-one association to CaseCaseType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_TYPE_ID")
    private CaseType caseType;

    // bi-directional many-to-one association to AdjIntResultGro
    @OneToMany(mappedBy = "adjIntResultGroId", fetch = FetchType.LAZY)
    private List<AdjIntResultGro> adjIntResultGros;

    @Column(name = "NATURE_OF_CLAIM")
    private String natureOfClaim;
}