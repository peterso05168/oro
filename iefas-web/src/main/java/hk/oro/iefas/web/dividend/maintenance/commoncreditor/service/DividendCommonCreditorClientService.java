package hk.oro.iefas.web.dividend.maintenance.commoncreditor.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.vo.CommonCreditorVO;
import hk.oro.iefas.domain.casemgt.vo.SearchCommonCreditorCriteriaVO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface DividendCommonCreditorClientService {
    CommonCreditorVO searchCommonCreditor(Integer commonCreditorId);

    Integer saveCommonCreditor(CommonCreditorVO commonCreditorVO);

    boolean existsByCommonCreditorName(SearchCommonCreditorCriteriaVO criteriaVO);

    List<CommonCreditorVO> searchAllActCommonCreditors();

    List<CommonCreditorVO> searchCommonCreditorByName(String commonCreditorName);

    List<CommonCreditorVO> searchCommonCreditorBySeqNo(String commonCreditorSeqNo);
}
