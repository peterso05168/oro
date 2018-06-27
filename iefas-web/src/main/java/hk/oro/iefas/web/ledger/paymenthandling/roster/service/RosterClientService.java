package hk.oro.iefas.web.ledger.paymenthandling.roster.service;

import hk.oro.iefas.domain.shroff.vo.RosterVO;
import hk.oro.iefas.domain.shroff.vo.SearchRosterVO;

/**
 * @version $Revision: 2745 $ $Date: 2018-05-30 20:19:44 +0800 (週三, 30 五月 2018) $
 * @author $Author: garrett.han $
 */
public interface RosterClientService {

    Integer saveRoster(RosterVO createRosterVO);

    RosterVO getRosterDetail(Integer rosterId);

    Boolean existByOnDutyDateAndIdNot(SearchRosterVO criteria);

}
