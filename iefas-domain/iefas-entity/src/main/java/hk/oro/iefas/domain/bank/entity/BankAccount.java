package hk.oro.iefas.domain.bank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BANK_BANK_ACCOUNT")
public class BankAccount extends UpdateTxnKeyable {

    @Id
    @Column(name = "BANK_AC_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_AC_SEQ")
    @TableGenerator(name = "BANK_AC_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "BANK_AC_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer bankAcId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BANK_INFO_ID", referencedColumnName = "BANK_INFO_ID", nullable = false)
    private BankInfo bankInfo;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "BANK_CODE")
    private String bankCode;

    @Column(name = "BANK_DESC")
    private String bankDesc;

    @Column(name = "STATUS")
    private String status;

}
