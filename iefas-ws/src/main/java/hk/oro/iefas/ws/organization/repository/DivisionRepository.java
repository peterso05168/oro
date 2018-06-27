package hk.oro.iefas.ws.organization.repository;

import java.util.List;

import hk.oro.iefas.domain.organization.entity.Division;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface DivisionRepository extends BaseRepository<Division, Integer> {

    List<Division> findAll();

    List<Division> findDivisionByParentDivisionId(Integer parentDivisionId);

    Division findOne(Integer divisionId);
}
