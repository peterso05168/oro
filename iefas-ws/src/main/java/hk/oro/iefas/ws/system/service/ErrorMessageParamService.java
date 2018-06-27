/**
 * 
 */
package hk.oro.iefas.ws.system.service;

import java.util.List;

import hk.oro.iefas.domain.system.dto.ErrorMessageParamDTO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface ErrorMessageParamService {

    List<ErrorMessageParamDTO> findAll();
}
