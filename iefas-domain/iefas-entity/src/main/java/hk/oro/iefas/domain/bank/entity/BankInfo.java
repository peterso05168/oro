package hk.oro.iefas.domain.bank.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "BANK_BANK_INFO")
public class BankInfo extends UpdateTxnKeyable {

    @Id
    @Column(name = "BANK_INFO_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_INFO_SEQ")
    @TableGenerator(name = "BANK_INFO_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "BANK_INFO_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer bankInfoId;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "BANK_SHORT_NAME")
    private String bankShortName;

    @Column(name = "BANK_CODE")
    private String bankCode;

    @Column(name = "BANK_DEPOSIT_LIMIT")
    private Integer bankDepositlimit;

    @OneToOne(optional = false, mappedBy = "bankInfo")
    private BankDeposit bankDeposit;

    @Column(name = "ROUNDING_DECIMAL")
    private Integer decimalRounding;

    @Column(name = "SPECIAL_DEPOSIT_ALLOWED")
    private String specialDepositAllowed;

    @Column(name = "BANK_OF_CHINA_GROUP")
    private String bankOfChinaGroup;

    @OneToMany(mappedBy = "bankInfoId", fetch = FetchType.LAZY)
    private List<LeapYearParameter> leapYearParameters;

    @OneToMany(mappedBy = "bankInfoId1", fetch = FetchType.LAZY)
    private List<FreeBankTransfer> freeBankTransfers;

    @Column(name = "STATUS")
    private String status;

}
