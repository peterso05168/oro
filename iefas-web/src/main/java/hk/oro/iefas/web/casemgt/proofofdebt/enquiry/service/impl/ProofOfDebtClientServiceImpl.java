package hk.oro.iefas.web.casemgt.proofofdebt.enquiry.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.counter.vo.ProofOfDebtVO;
import hk.oro.iefas.web.casemgt.proofofdebt.enquiry.service.ProofOfDebtClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class ProofOfDebtClientServiceImpl extends BaseClientService implements ProofOfDebtClientService {

    @Override
    public ProofOfDebtVO findOne(Integer proofOfDebtId) {
        log.info("findOne() start - proofOfDebtId: " + proofOfDebtId);
        ResponseEntity<ProofOfDebtVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_GET_PROOF_OF_DEBT_DETAIL, proofOfDebtId, ProofOfDebtVO.class);
        ProofOfDebtVO body = responseEntity.getBody();
        log.info("findOne() end - result = " + body);
        return body;
    }

    @Override
    public Integer save(ProofOfDebtVO proofOfDebtVO) {
        log.info("create() start - " + proofOfDebtVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_PROOF_OF_DEBT, proofOfDebtVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("create() end -  result = " + body);
        return body;
    }

}
