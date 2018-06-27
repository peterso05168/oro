/**
 * 
 */
package hk.oro.iefas.domain.system.vo;

import java.util.List;

import hk.oro.iefas.domain.core.vo.TxnVO;
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
public class SystemSettingPageVO {

    private List<SystemParameterVO> systemParameterList;

    private OroInfoVO oroInfo;

    private SysNotificationVO sysNotification;
}
