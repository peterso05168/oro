package hk.oro.iefas.web.casemgt.casedetailenquiry.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CaseAccountInfoVO;
import hk.oro.iefas.domain.shroff.vo.SearchOldCaseAccountCriteriaVO;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
public interface CaseAccountClientService {

    public List<CaseAccountInfoVO> findCaseAccountByCaseId(Integer caseId);

    public CaseAccountInfoVO findOne(Integer caseAccountId);

    public Integer save(CaseAccountInfoVO caseAccount);

    CaseAccountInfoVO findByAccountNumber(String accountNumber);
    
    List<CaseAccountInfoVO> findOldCaseAccountByAccountType(SearchOldCaseAccountCriteriaVO criteria);
}
