/**
 * 
 */
package hk.oro.iefas.web.common.service;

import java.util.List;

import hk.oro.iefas.domain.system.vo.ErrorMessageParamVO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface ErrorMessageParamClientService {

    List<ErrorMessageParamVO> findAll();
}
