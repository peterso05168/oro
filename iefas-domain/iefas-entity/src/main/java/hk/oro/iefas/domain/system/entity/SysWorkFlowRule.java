/**
 * 
 */
package hk.oro.iefas.domain.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hk.oro.iefas.domain.security.entity.Privilege;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYS_WORKFLOW_RULE")
public class SysWorkFlowRule {

    @Id
    @Column(name = "RULE_ID")
    private Integer ruleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIVILEGE_ID")
    private Privilege privilege;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEFORE_STATUS")
    private ApplicationCodeTable beforeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTION")
    private ApplicationCodeTable action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AFTER_STATUS")
    private ApplicationCodeTable afterStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTION_DONE")
    private ApplicationCodeTable actionDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTION_TO_BE_TAKEN")
    private ApplicationCodeTable actionToBeTaken;

}
