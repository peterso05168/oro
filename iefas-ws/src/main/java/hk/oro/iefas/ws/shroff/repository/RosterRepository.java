package hk.oro.iefas.ws.shroff.repository;

import hk.oro.iefas.domain.shroff.entity.ShrRoster;
import hk.oro.iefas.ws.core.repository.BaseRepository;

import java.util.Date;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public interface RosterRepository extends BaseRepository<ShrRoster, Integer> {
    public boolean existsByOnDutyDateAndRosterIdNot(Date date, Integer rosterId);
}
