package hk.oro.iefas.ws.casemgt.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.dto.CaseAccountInfoDTO;
import hk.oro.iefas.domain.shroff.dto.SearchOldCaseAccountCriteriaDTO;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface CaseAccountService {

    CaseAccountInfoDTO findOne(Integer caseAccountId);

    Integer save(CaseAccountInfoDTO caseAccount);

    List<CaseAccountInfoDTO> findCaseAccountByCaseId(Integer caseId);

    CaseAccountInfoDTO findByAccountNumber(String accountNumber);

    List<CaseAccountInfoDTO> findOldCaseAccountByAccountType(SearchOldCaseAccountCriteriaDTO criteria);
}
