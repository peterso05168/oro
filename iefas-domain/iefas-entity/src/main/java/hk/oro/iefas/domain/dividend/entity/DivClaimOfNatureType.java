package hk.oro.iefas.domain.dividend.entity;

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
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Entity
@Table(name = "DIV_CLAIM_OF_NATURE_TYPE")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivClaimOfNatureType extends UpdateTxnKeyable {

    @Id
    @Column(name = "CLAIM_OF_NATURE_TYPE_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CLAIM_OF_NATURE_TYPE_SEQ")
    @TableGenerator(name = "CLAIM_OF_NATURE_TYPE_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "CLAIM_OF_NATURE_TYPE_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer claimOfNatureTypeId;

    @Column(name = "CLAIM_OF_NATURE_TYPE_NAME", length = 100)
    private String claimOfNatureTypeName;

    @Column(name = "STATUS", length = 3)
    private String status;

    @OneToMany(mappedBy = "wilonAndSevrnPayId", fetch = FetchType.LAZY)
    private List<DivWilonAndSever> divWilonAndSevers;

}