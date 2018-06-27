/**
 * 
 */
package hk.oro.iefas.ws.system.repository.assembler;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.DslUtils;
import hk.oro.iefas.domain.system.dto.SysNotificationDTO;
import hk.oro.iefas.domain.system.entity.QSysNotification;

/**
 * @version $Revision $Date $
 * @author Author $
 */
public class SysNotificationDTOAssembler {

    public static QBean<SysNotificationDTO> toDTO() {
        return Projections.bean(SysNotificationDTO.class, DslUtils.getAllExpression(QSysNotification.sysNotification));
    }
}
