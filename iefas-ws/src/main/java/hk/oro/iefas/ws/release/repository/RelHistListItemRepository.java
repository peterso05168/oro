package hk.oro.iefas.ws.release.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hk.oro.iefas.domain.release.entity.RelHistListItem;
import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (Mon, 09 Apr 2018) $
 * @author $Author: marvel.ma $
 */
public interface RelHistListItemRepository extends BaseRepository<RelHistListItem, Integer> {
	
	List<RelHistListItem> findByHistCaseListId(Integer histCaseListId);
	
	@Modifying
    @Query("update RelHistListItem set status = :status where histCaseListItemId = :itemId")
    int deleteItem(@Param("itemId") Integer histCaseListItemId, @Param("status") String status);

}
