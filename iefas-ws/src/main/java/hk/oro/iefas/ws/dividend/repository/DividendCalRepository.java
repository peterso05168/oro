package hk.oro.iefas.ws.dividend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import hk.oro.iefas.domain.dividend.entity.DividendCal;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2945 $ $Date: 2018-06-06 15:38:06 +0800 (週三, 06 六月 2018) $
 * @author $Author: vicki.huang $
 */
public interface DividendCalRepository extends PagingAndSortingRepository<DividendCal, Integer>, BaseRepository<DividendCal, Integer> {

}
