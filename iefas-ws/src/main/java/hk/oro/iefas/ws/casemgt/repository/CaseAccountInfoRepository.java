package hk.oro.iefas.ws.casemgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface CaseAccountInfoRepository extends BaseRepository<CaseAccountInfo, Integer> {

    @Query("select a from CaseAccountInfo a where a.caseInfo.caseId = ?1")
    public List<CaseAccountInfo> findCaseAccountByCaseId(Integer caseId);

}
