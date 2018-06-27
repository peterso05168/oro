package hk.oro.iefas.domain.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the ORO_INFO database table.
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORO_INFO")
public class OroInfo extends UpdateTxnKeyable {

    @Id
    @Column(name = "ORO_INFO_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ORO_INFO_SEQ")
    @TableGenerator(name = "ORO_INFO_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "ORO_INFO_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer oroInfoId;

    @Column(name = "NAME_OF_IN_CHARGE", length = 100)
    private String nameOfInCharge;

    @Column(name = "ORG_NAME", length = 100)
    private String orgName;

    @Temporal(TemporalType.DATE)
    @Column(name = "PERIOD_FROM")
    private Date periodFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "PERIOD_TO")
    private Date periodTo;

    @Column(name = "STATUS", length = 3)
    private String status;

    @Column(name = "TITLE_OF_IN_CHARGE", length = 100)
    private String titleOfInCharge;

    // bi-directional many-to-one association to CaseAddress
    // @ManyToOne(fetch=FetchType.LAZY)
    // @JoinColumn(name="ADDRESS_ID")
    // private CaseAddress caseAddress;

    public Integer getOroInfoId() {
        return this.oroInfoId;
    }

    public void setOroInfoId(Integer oroInfoId) {
        this.oroInfoId = oroInfoId;
    }

    public String getNameOfInCharge() {
        return this.nameOfInCharge;
    }

    public void setNameOfInCharge(String nameOfInCharge) {
        this.nameOfInCharge = nameOfInCharge;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getPeriodFrom() {
        return this.periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Date getPeriodTo() {
        return this.periodTo;
    }

    public void setPeriodTo(Date periodTo) {
        this.periodTo = periodTo;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitleOfInCharge() {
        return this.titleOfInCharge;
    }

    public void setTitleOfInCharge(String titleOfInCharge) {
        this.titleOfInCharge = titleOfInCharge;
    }

    // public CaseAddress getCaseAddress() {
    // return this.caseAddress;
    // }
    //
    // public void setCaseAddress(CaseAddress caseAddress) {
    // this.caseAddress = caseAddress;
    // }

}
