package hk.oro.iefas.ws.adjudication.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.dividend.dto.GroundCodeDTO;
import hk.oro.iefas.domain.dividend.dto.SearchGroundCodeCriteriaDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2066 $ $Date: 2018-04-14 19:02:16 +0800 (週六, 14 四月 2018) $
 * @author $Author: vicki.huang $
 */
public interface GroundCodeService {

    Page<GroundCodeDTO> searchGroundCodeList(SearchDTO<SearchGroundCodeCriteriaDTO> criteriaDTO);

    Integer saveGroundCode(GroundCodeDTO groundCodeDTO);

    GroundCodeDTO searchGroundCodeById(Integer groundCodeId);

    List<GroundCodeDTO> findAll();
}
