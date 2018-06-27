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
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DIV_GAZETTE_DESC")
public class Gazette extends UpdateTxnKeyable {
    @Id
    @Column(name = "GAZETTE_DESC_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GAZETTE_DESC_SEQ")
    @TableGenerator(name = "GAZETTE_DESC_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "GAZETTE_DESC_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer gazetteId;

    @Column(name = "GAZETTE_DESC_NAME", length = 100)
    private String gazetteName;

    @Column(name = "GAZETTE_DESC_NAME_CHI", length = 300)
    private String gazetteNameChi;

    @Column(name = "GAZETTE_DESC_CODE", length = 10)
    private String gazetteCode;

    @Column(name = "STATUS", length = 3)
    private String status;
}
