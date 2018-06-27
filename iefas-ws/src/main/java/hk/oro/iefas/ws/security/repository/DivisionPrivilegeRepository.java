package hk.oro.iefas.ws.security.repository;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.security.entity.DivisionPrivilege;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface DivisionPrivilegeRepository extends BaseRepository<DivisionPrivilege, Integer> {

    @Query("select count(d) from DivisionPrivilege d where d.division.divisionId = ?1")
    public Integer findByDivisionId(Integer divisionId);

}
