package hk.oro.iefas.domain.casemgt.entity;

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

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CASE_ADDRESS")
public class Address extends UpdateTxnKeyable {

    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADDRESS_SEQ")
    @TableGenerator(name = "ADDRESS_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "ADDRESS_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer addressId;

    @Column(name = "ADDRESS_1")
    private String address1;

    @Column(name = "ADDRESS_2")
    private String address2;

    @Column(name = "ADDRESS_3")
    private String address3;

    @Column(name = "CHI_ADDRESS_1")
    private String chiAddress1;

    @Column(name = "CHI_ADDRESS_2")
    private String chiAddress2;

    @Column(name = "CHI_ADDRESS_3")
    private String chiAddress3;

    @Column(name = "STATUS")
    private String status;

}
