/**
 * 
 */
package hk.oro.iefas.ws.system.repository.predicate;

import com.querydsl.core.types.Predicate;

import hk.oro.iefas.domain.system.entity.QSysWorkFlowRule;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
public class SysWorkFlowRulePredicate {

    public static Predicate findByPrivilegeCodeAndBeforeStatus(String privilegeCode, String beforeStatus) {
        QSysWorkFlowRule sysworkflowrule = QSysWorkFlowRule.sysWorkFlowRule;
        return sysworkflowrule.privilege.privilegeCode.eq(privilegeCode)
            .and(sysworkflowrule.beforeStatus.codeValue.eq(beforeStatus));
    }

}
