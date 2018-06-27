package hk.oro.iefas.web.dividend.maintenance.commoncreditor.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CommonCreditorSectionVO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CommonCreditorSectionClientService {

    List<CommonCreditorSectionVO> searchCommonCreditorSectionByName(String commonCreditorSectionName);

    List<CommonCreditorSectionVO> searchCommonCreditorSectionBySeqNo(String commonCreditorSectionSeqNo);

    Integer saveCommonCreditorSection(CommonCreditorSectionVO commonCreditorSectionVO);

}
