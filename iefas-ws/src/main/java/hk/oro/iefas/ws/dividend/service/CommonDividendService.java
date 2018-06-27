package hk.oro.iefas.ws.dividend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.common.dto.CaseTypeDTO;
import hk.oro.iefas.domain.dividend.dto.CaseFeeTypeDTO;
import hk.oro.iefas.domain.dividend.dto.CreditorTypeDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CommonDividendService {

    public Page<CaseFeeTypeDTO> searchORFeeItemList(SearchDTO<CaseTypeDTO> caseTypeDTO);

    public List<CreditorTypeDTO> searchCreditorType();

}
