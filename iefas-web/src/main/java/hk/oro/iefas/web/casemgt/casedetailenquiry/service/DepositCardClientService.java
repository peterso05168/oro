package hk.oro.iefas.web.casemgt.casedetailenquiry.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.DepositCardVO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface DepositCardClientService {

    public List<DepositCardVO> findDepositCardByCase(Integer caseId);

    public DepositCardVO findOne(Integer depositCardId);

    public Integer save(DepositCardVO depositCard);
}
