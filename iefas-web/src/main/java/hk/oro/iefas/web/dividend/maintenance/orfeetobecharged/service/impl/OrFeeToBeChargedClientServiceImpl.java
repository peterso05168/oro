package hk.oro.iefas.web.dividend.maintenance.orfeetobecharged.service.impl;

import javax.inject.Named;

import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.CaseFeeTypeVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.maintenance.orfeetobecharged.service.OrFeeToBeChargedClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
public class OrFeeToBeChargedClientServiceImpl extends BaseClientService implements OrFeeToBeChargedClientService {

    @Override
    public boolean saveORFeeItemWithAnalysisCodeCharged(CaseFeeTypeVO caseFeeTypeVO) {
        boolean result = false;
        log.info("saveORFeeItemWithAnalysisCodeCharged - start and caseFeeTypeVO=" + caseFeeTypeVO);
        if (caseFeeTypeVO != null) {
            ResponseEntity<Boolean> response = this.postForEntity(
                RequestUriConstant.CLIENT_URI_ORFEE_SAVE_ORFEEITEM_WITH_ANALYSIS, caseFeeTypeVO, Boolean.class);
            result = response.getBody();
        }
        log.info("saveORFeeItemWithAnalysisCodeCharged - end and result=" + result);
        return result;
    }

}
