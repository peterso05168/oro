/**
 * 
 */
package hk.oro.iefas.web.ledger.voucherhandling.common.service;

import java.util.List;

import hk.oro.iefas.domain.shroff.vo.JournalTypeVO;

/**
 * @version $Revision: 2831 $ $Date: 2018-06-02 14:14:30 +0800 (週六, 02 六月 2018) $
 * @author $Author: marvel.ma $
 */
public interface JournalTypeClientService {

    List<JournalTypeVO> findAll();

}
