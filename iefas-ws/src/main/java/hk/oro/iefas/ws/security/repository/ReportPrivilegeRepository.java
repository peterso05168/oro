package hk.oro.iefas.ws.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.security.entity.ReportPrivilege;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface ReportPrivilegeRepository extends BaseRepository<ReportPrivilege, Integer> {

    @Query("select r from ReportPrivilege r, Privilege p where r.privilege = p and p.privilegeId = ?1")
    public List<ReportPrivilege> findByPrivilegeId(Integer privilegeId);
}
