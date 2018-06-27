package hk.oro.iefas.web.dividend.dividendprocess.dividendcheque.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.vo.DividendChequeVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.dividend.dividendprocess.dividendcheque.service.impl.DividendChequeClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3021 $ $Date: 2018-06-10 16:06:34 +0800 (Sun, 10 Jun 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
public class DividendChequeClientServiceImpl extends BaseClientService implements DividendChequeClientService {

    @Override
    public DividendChequeVO searchDividendCheque(Integer dividendChequeId) {
        log.info("searchDividendCheque() start - dividendChequeId: " + dividendChequeId);
        ResponseEntity<DividendChequeVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_DIVIDEND_CHEQUE_DETAIL, dividendChequeId, DividendChequeVO.class);
        DividendChequeVO body = responseEntity.getBody();
        log.info("searchDividendCheque() end - " + body);
        return body;
    }

    @Override
    public Integer saveDividendCheque(DividendChequeVO dividendChequeVO) {
        log.info("saveDividendCheque() start - dividendChequeVO: " + dividendChequeVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_DIVIDEND_CHEQUE_CREATE, dividendChequeVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveDividendCheque() end - " + body);
        return body;
    }

    @Override
    public boolean saveDividendChequeList(List<DividendChequeVO> dividendChequeVOList) {
        log.info("saveDividendChequeList() start - dividendChequeVOList: " + dividendChequeVOList);
        ResponseEntity<Boolean> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_SAVE_DIVIDEND_CHEQUELIST, dividendChequeVOList, Boolean.class);
        boolean result = responseEntity.getBody();
        log.info("saveDividendChequeList() end - " + result);
        return result;
    }

    @Override
    public List<DividendChequeVO> searchDivChequeListByDivScheId(List<Integer> divScheItemIdList) {
        log.info("searchDivChequeListByDivScheId() start - divScheItemIdList: " + divScheItemIdList);
        HttpEntity<List<Integer>> entity = new HttpEntity<List<Integer>>(divScheItemIdList);
        ResponseEntity<List<DividendChequeVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_DIVIDEND_CHEQUE_LIST_BY_DIVSCHEID, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<DividendChequeVO>>() {});
        List<DividendChequeVO> result = responseEntity.getBody();
        log.info("searchDivChequeListByDivScheId() end - " + result);
        return result;
    }

}
