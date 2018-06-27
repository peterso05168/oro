package hk.oro.iefas.ws.casemgt.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.casemgt.dto.CommonCreditorBasicDTO;
import hk.oro.iefas.domain.casemgt.dto.CommonCreditorDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchCommonCreditorCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface DividendCommonCreditorService {

    Page<CommonCreditorBasicDTO> searchCommonCreditorList(SearchDTO<SearchCommonCreditorCriteriaDTO> criteriaDTO);

    CommonCreditorDTO searchCommonCreditor(Integer commonCreditorId);

    Integer saveCommonCreditor(CommonCreditorDTO commonCreditorDTO);

    boolean existsByCommonCreditorName(String commonCreditorName, Integer commonCreditorId);

    List<CommonCreditorDTO> searchAllActCommonCreditors();

    List<CommonCreditorDTO> searchCommonCreditorByName(String commonCreditorName);

    List<CommonCreditorDTO> searchCommonCreditorBySeqNo(String commonCreditorSeqNo);
}
