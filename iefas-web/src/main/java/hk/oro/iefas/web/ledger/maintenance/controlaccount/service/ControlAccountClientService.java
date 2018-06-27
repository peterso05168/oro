package hk.oro.iefas.web.ledger.maintenance.controlaccount.service;

import java.util.List;

import hk.oro.iefas.domain.shroff.vo.ControlAccountVO;
import hk.oro.iefas.domain.shroff.vo.SearchControlAccountVO;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface ControlAccountClientService {

    List<ControlAccountVO> findAllControlAccounts();

    ControlAccountVO getControlAccountDetail(Integer ctlAcId);

    Integer saveControlAccount(ControlAccountVO controlAccountVO);

    Boolean existsByControlAccountName(SearchControlAccountVO criteria);

    Boolean existsByControlAccountCode(SearchControlAccountVO criteria);

}
