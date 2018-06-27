package hk.oro.iefas.web.ledger.chequehandling.incomingcheque.service;

import java.util.List;
import java.util.Set;

import hk.oro.iefas.domain.shroff.vo.ChequeVO;
import hk.oro.iefas.domain.shroff.vo.SearchIncomingChequeVO;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface ChequeClientService {
    ChequeVO getIncomingChequeDetail(Integer chequeId);

    ChequeVO getOutgoingChequeDetail(Integer outgoingChequeId);

    Integer saveIncomingCheque(ChequeVO chequeVO);

    Integer saveOutgoingCheque(ChequeVO chequeVO);

    Boolean existsByChequeNumber(SearchIncomingChequeVO searchIncomingChequeVO);

    ChequeVO getChequeDetailByChequeNo(String chequeNo);

    List<ChequeVO> getOutgoingChequeByParentId(Integer parentId);

    Integer combineOutgoingCheque(Set<Integer> chequeIds);
}
