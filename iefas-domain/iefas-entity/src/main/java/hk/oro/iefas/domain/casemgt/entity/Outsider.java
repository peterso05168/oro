package hk.oro.iefas.domain.casemgt.entity;

import java.util.Date;

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

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CASE_OUTSIDER")
public class Outsider extends UpdateTxnKeyable {

    @Id
    @Column(name = "OUTSIDER_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "OUTSIDER_SEQ")
    @TableGenerator(name = "OUTSIDER_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME",
        pkColumnValue = "OUTSIDER_ID", valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer outsiderId;

    @Column(name = "OUTSIDER_NAME")
    private String outsiderName;

    @Column(name = "OUTSIDER_NAME_SHORT")
    private String outsiderNameShort;

    @Column(name = "OUTSIDER_CHI_NAME")
    private String outsiderChiName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    @Column(name = "FAX")
    private String fax;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUTSIDER_TYPE_ID")
    private OutsiderType outsiderType;

    @Column(name = "CONTACT_NO")
    private String contactNo;

    @Column(name = "ENCRYPTED_PASSWORD")
    private String encryptedPassword;

    @Column(name = "LAST_PASSWORD_CHANGE")
    private Date lastPasswordChange;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "STATUS")
    private String status;
}
