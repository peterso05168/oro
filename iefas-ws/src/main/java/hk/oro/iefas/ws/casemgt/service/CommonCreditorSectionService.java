package hk.oro.iefas.ws.casemgt.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.dto.CommonCreditorSectionDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CommonCreditorSectionService {

    List<CommonCreditorSectionDTO> searchCommonCreditorSectionByName(String commonCreditorSectionName);

    List<CommonCreditorSectionDTO> searchCommonCreditorSectionBySeqNo(String commonCreditorSectionSeqNo);
}
