package hk.oro.iefas.web.dividend.creditorreg.wilon.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.dividend.vo.CreateWilonAndSeveranceVO;
import hk.oro.iefas.domain.dividend.vo.WilonAndSeverancePayVO;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
public interface WilonAndSeveranceClientService {

    /**
     * Search Creditor By Case Number
     * 
     * @param CaseNumberVO caseNumber
     * @return List<CreditorVO>
     */
    public List<CreditorVO> searchCreditorByCaseNumber(CaseNumberVO caseNumber);

    /**
     * Save WILON And Severance Pay
     * 
     * @param WILONAndSeverancePayDTO
     * @return N/A
     */
    public Integer saveWILONAndSeverancePay(WilonAndSeverancePayVO wilonAndSeverancePay);

    public WilonAndSeverancePayVO searchWILONAndSeverancePay(Integer wilonAndSeverancePayId);

    public CreditorVO searchCreditorById(Integer creditorId);

    public boolean createWILONAndSeveranceValidate(CreateWilonAndSeveranceVO createWILONAndSeverance);

}
