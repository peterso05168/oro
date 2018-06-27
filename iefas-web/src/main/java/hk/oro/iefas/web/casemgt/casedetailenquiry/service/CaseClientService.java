package hk.oro.iefas.web.casemgt.casedetailenquiry.service;

import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;

/**
 * @version $Revision: 2768 $ $Date: 2018-05-31 16:03:24 +0800 (週四, 31 五月 2018) $
 * @author $Author: garrett.han $
 */
public interface CaseClientService {

    public Boolean validateExistsByCaseNo(String caseNo);

    public CaseVO findOne(Integer caseId);

    public CaseVO findByCaseNumber(CaseNumberVO caseNumberVO);

    public CaseVO findByWholeCaseNumber(String wholeCaseNumber);

}
