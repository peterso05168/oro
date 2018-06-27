package hk.oro.iefas.ws.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.security.entity.Menu;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision $ $Date: 2018-04-14 15:49:09 +0800 (週六, 14 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface MenuReposiroty extends BaseRepository<Menu, Integer> {

    <T> List<T> findByParentMenuIdIsNullOrderByRecordSeq(Class<T> type);

    @Query("select mp.menu from PostRole pr, RolePrivilege rp, MenuPrivilege mp "
        + "where pr.post.postId = ?1 and pr.role = rp.role and mp.privilege = rp.privilege "
        + "and pr.status = 'ACT' and rp.status = 'ACT'")
    List<Menu> findByPostId(Integer postId);

}
