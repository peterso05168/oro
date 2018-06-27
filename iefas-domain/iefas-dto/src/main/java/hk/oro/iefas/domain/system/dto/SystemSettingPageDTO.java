/**
 * 
 */
package hk.oro.iefas.domain.system.dto;

import java.util.List;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2570 $ $Date: 2018-05-24 14:35:00 +0800 (週四, 24 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SystemSettingPageDTO extends TxnDTO {

    private List<SystemParameterDTO> systemParameterList;

    private OroInfoDTO oroInfo;

    private SysNotificationDTO sysNotification;
}
