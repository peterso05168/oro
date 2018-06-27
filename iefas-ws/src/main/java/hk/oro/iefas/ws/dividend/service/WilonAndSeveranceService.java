package hk.oro.iefas.ws.dividend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.dividend.dto.CreateWilonAndSeveranceDTO;
import hk.oro.iefas.domain.dividend.dto.SearchWilonAndSeverancePayCriteriaDTO;
import hk.oro.iefas.domain.dividend.dto.WilonAndSeverancePayDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2961 $ $Date: 2018-06-06 20:14:53 +0800 (週三, 06 六月 2018) $
 * @author $Author: noah.liang $
 */
public interface WilonAndSeveranceService {

    public Page<WilonAndSeverancePayDTO> searchWILONAndSeveranceList(
        SearchDTO<SearchWilonAndSeverancePayCriteriaDTO> searchWILONAndSeverancePayCriteria);

    public Integer saveWILONAndSeverancePay(WilonAndSeverancePayDTO wilonAndSeverancePay);

    public List<CreditorDTO> searchCreditorByCaseNumber(CaseNumberDTO caseNumberDTO);

    public WilonAndSeverancePayDTO searchWILONAndSeverancePayById(Integer wilonAndSeverancePayId);

    public boolean createWILONAndSeveranceValidate(CreateWilonAndSeveranceDTO createWILONAndSeverance);
}
