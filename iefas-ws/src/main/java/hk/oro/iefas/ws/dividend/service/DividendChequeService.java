package hk.oro.iefas.ws.dividend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.dividend.dto.DividendChequeDTO;
import hk.oro.iefas.domain.dividend.dto.SearchDividendChequeDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (Sun, 10 Jun 2018) $
 * @author $Author: vicki.huang $
 */
public interface DividendChequeService {

    public Page<DividendChequeDTO> searchDividendChequeList(SearchDTO<SearchDividendChequeDTO> criteriaDTO);

    public DividendChequeDTO searchDividendCheque(Integer dividendChequeId);

    public Integer saveDividendCheque(DividendChequeDTO dividendChequeDTO);

    public boolean saveDividendChequeList(List<DividendChequeDTO> dividendChequeList);
    
    public List<DividendChequeDTO> searchDividendChequeList(List<Integer> divScheItemIdList);
}
