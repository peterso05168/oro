/**
 * 
 */
package hk.oro.iefas.ws.system.service;

import java.util.List;

import hk.oro.iefas.domain.system.dto.SystemParameterDTO;

/**
 * @version $Revision: 2570 $ $Date: 2018-05-24 14:35:00 +0800 (週四, 24 五月 2018) $
 * @author $Author: marvel.ma $
 */
public interface SystemParameterService {

    List<SystemParameterDTO> findAllSystemParameters();

    String getDateFormat();
}
