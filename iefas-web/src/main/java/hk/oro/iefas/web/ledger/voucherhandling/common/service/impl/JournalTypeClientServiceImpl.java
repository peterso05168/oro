/**
 * 
 */
package hk.oro.iefas.web.ledger.voucherhandling.common.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.vo.JournalTypeVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.JournalTypeClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2831 $ $Date: 2018-06-02 14:14:30 +0800 (週六, 02 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class JournalTypeClientServiceImpl extends BaseClientService implements JournalTypeClientService {

    @Override
    public List<JournalTypeVO> findAll() {
        log.info("findAll() start");
        ResponseEntity<List<JournalTypeVO>> responseEntity
            = exchange(RequestUriConstant.CLIENT_URI_JOURNAL_TYPE_FINDALL, HttpMethod.POST, null,
                new ParameterizedTypeReference<List<JournalTypeVO>>() {});
        List<JournalTypeVO> body = responseEntity.getBody();
        log.info("findAll() end - " + body);
        return body;
    }

}
