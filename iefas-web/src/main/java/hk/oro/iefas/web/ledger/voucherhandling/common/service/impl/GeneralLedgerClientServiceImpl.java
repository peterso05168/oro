package hk.oro.iefas.web.ledger.voucherhandling.common.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.GeneralLedgerVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.GeneralLedgerClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3050 $ $Date: 2018-06-11 21:15:35 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
@Slf4j
@Service
public class GeneralLedgerClientServiceImpl extends BaseClientService implements GeneralLedgerClientService {

    @Override
    public GeneralLedgerVO findGeneralLedgerByControlAcId(Integer controlAccountId) {
        log.info("saveGenenalLedger start - controlAccountId: " + controlAccountId);
        ResponseEntity<GeneralLedgerVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_FIND_GENERAL_LEDGER_BY_CONTROL_ACCOUNT_ID, controlAccountId, GeneralLedgerVO.class);
        GeneralLedgerVO body = responseEntity.getBody();
        log.info("saveGenenalLedger end - Genenal Ledger Id: " + body);
        return body;
    }

    @Override
    public GeneralLedgerVO findGeneralLedger(Integer generalLedgerId) {
        log.info("saveGenenalLedger start - generalLedgerId: " + generalLedgerId);
        ResponseEntity<GeneralLedgerVO> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_FIND_GENERAL_LEDGER, generalLedgerId, GeneralLedgerVO.class);
        GeneralLedgerVO body = responseEntity.getBody();
        log.info("saveGenenalLedger end - Genenal Ledger Id: " + body);
        return body;
    }

    @Override
    public Integer saveGeneralLedger(GeneralLedgerVO genenalLedger) {
        log.info("saveGenenalLedger start - genenalLedger: " + genenalLedger);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_SAVE_GENERAL_LEDGER, genenalLedger, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveGenenalLedger end - Genenal Ledger Id: " + body);
        return body;
    }

}
