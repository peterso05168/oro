/**
 * 
 */
package hk.oro.iefas.web.ledger.voucherhandling.common.service;

import hk.oro.iefas.domain.system.vo.SysWfInitialStatusVO;

/**
 * @version $Revision: 2831 $ $Date: 2018-06-02 14:14:30 +0800 (週六, 02 六月 2018) $
 * @author $Author: marvel.ma $
 */
public interface SysWfInitialStatusClientService {

    SysWfInitialStatusVO findByPrivilegeCode(String privilegeCode);
}
