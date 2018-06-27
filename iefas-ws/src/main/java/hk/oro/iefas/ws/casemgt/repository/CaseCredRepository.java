package hk.oro.iefas.ws.casemgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.casemgt.entity.CaseCred;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CaseCredRepository extends BaseRepository<CaseCred, Integer> {

    @Query("select c from CaseCred c where c.caseInfo.wholeCaseNo = ?1")
    List<CaseCred> findCreditorByCaseNumber(String caseNumber);

}
