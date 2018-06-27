package hk.oro.iefas.domain.shroff.entity;

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

import hk.oro.iefas.domain.common.UpdateTxnKeyable;
import hk.oro.iefas.domain.organization.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the SHR_ROSTER database table.
 * 
 */
@Entity
@Table(name = "SHR_ROSTER")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShrRoster extends UpdateTxnKeyable {

    @Id
    @Column(name = "ROSTER_ID", unique = true, nullable = false, precision = 15)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ROSTER_SEQ")
    @TableGenerator(name = "ROSTER_SEQ", table = "TABLE_SEQ", pkColumnName = "SEQ_NAME", pkColumnValue = "ROSTER_ID",
        valueColumnName = "SEQ_CNT", allocationSize = 1)
    private Integer rosterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_A_POST_ID")
    private Post groupAPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_B_POST_ID")
    private Post groupBPost;

    @Column(name = "ON_DUTY_DATE")
    private Date onDutyDate;

    @Column(name = "STATUS", length = 3)
    private String status;

}