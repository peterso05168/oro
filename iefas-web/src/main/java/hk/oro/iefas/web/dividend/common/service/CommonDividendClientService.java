package hk.oro.iefas.web.dividend.common.service;

import java.util.List;

import hk.oro.iefas.domain.dividend.vo.AdjudicationTypeVO;
import hk.oro.iefas.domain.dividend.vo.CreditorTypeVO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CommonDividendClientService {
    List<CreditorTypeVO> searchCreditorType();

    List<AdjudicationTypeVO> searchGroundCodeTypeList();
}
