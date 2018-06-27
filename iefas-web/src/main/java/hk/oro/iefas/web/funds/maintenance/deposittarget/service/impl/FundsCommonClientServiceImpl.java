package hk.oro.iefas.web.funds.maintenance.deposittarget.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.funds.vo.InvestmentStatusVO;
import hk.oro.iefas.domain.funds.vo.InvestmentTypeVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.funds.maintenance.deposittarget.service.FundsCommonClientService;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Named
public class FundsCommonClientServiceImpl extends BaseClientService implements FundsCommonClientService {

    public List<InvestmentTypeVO> searchInvestmentType() {
        List<InvestmentTypeVO> body = null;
        ResponseEntity<List<InvestmentTypeVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_INVESTMENT_TYPE_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<InvestmentTypeVO>>() {});
        body = responseEntity.getBody();
        return body;
    }

    @Override
    public List<InvestmentStatusVO> searchInvestmentStatus() {
        List<InvestmentStatusVO> body = null;
        ResponseEntity<List<InvestmentStatusVO>> responseEntity
            = this.exchange(RequestUriConstant.CLIENT_URI_INVESTMENT_STATUS_LIST, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<InvestmentStatusVO>>() {});
        body = responseEntity.getBody();
        return body;
    }
}
