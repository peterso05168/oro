package hk.oro.iefas.domain.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version $Revision: 1006 $ $Date: 2018-02-02 14:35:20 +0800 (Fri, 02 Feb 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class UpdateSubmitApproveInfoAble extends UpdateTxnKeyable {
    @Column(name = "SUBMITTED_BY", length = 100)
    private String submittedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "SUBMITTED_DATE")
    private Date submittedDate;

    @Column(name = "APPROVED_BY", length = 100)
    private String approvedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "APPROVED_DATE")
    private Date approvedDate;

    @Column(name = "STATUS", length = 3)
    private String status;
}
