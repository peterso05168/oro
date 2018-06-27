package hk.oro.iefas.ws.casemgt.service;

import java.util.List;

import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface CreditorService {

    List<CreditorDTO> findCreditorByCaseNumber(String caseNumber);

    CreditorDTO findCreditorById(Integer creditorId);
}
