package hk.oro.iefas.domain.dividend.entity;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.casemgt.entity.CaseCred;
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
@Table(name = "DIV_WILON_AND_SEVER")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DivWilonAndSever extends UpdateTxnKeyable {

    @Id
    @Column(name = "WILON_AND_SEVRN_PAY_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "WILON_AND_SEVRN_PAY_SEQ")
    @TableGenerator(name = "WILON_AND_SEVRN_PAY_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "WILON_AND_SEVRN_PAY_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer wilonAndSevrnPayId;

    @Temporal(TemporalType.DATE)
    @Column(name = "APPOINTMENT_DATE")
    private Date appointmentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    private Case caseInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDITOR_ID")
    private CaseCred caseCred;

    @Column(name = "EMPLOYEE_TYPE", length = 100)
    private String employeeType;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAID_OFF_DATE")
    private Date laidOffDate;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "TOTAL_SEVER", precision = 16, scale = 2)
    private BigDecimal totalSever;

    @Column(name = "TOTAL_WILON", precision = 16, scale = 2)
    private BigDecimal totalWilon;

    @Column(name = "WAGE_RATE", precision = 16, scale = 5)
    private BigDecimal wageRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLAIM_OF_NATURE_TYPE_ID")
    private DivClaimOfNatureType divClaimOfNatureType;

    @Column(name = "WILON_PREF", precision = 16)
    private BigDecimal wilonPref;

    @Column(name = "WILON_ORDI", precision = 16)
    private BigDecimal wilonOrdi;

    @Column(name = "SEVER_PREF", precision = 16)
    private BigDecimal severPref;

    @Column(name = "SEVER_ORDI", precision = 16)
    private BigDecimal severOrdi;

}