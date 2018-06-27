/**
 * 
 */
package hk.oro.iefas.ws.shroff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.dto.JournalTypeDTO;
import hk.oro.iefas.ws.shroff.service.JournalTypeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_JOURNAL_TYPE)
public class JournalTypeController {

    @Autowired
    private JournalTypeService journalTypeService;

    @PostMapping(value = RequestUriConstant.URI_FIND_ALL)
    public List<JournalTypeDTO> findAll() {
        log.info("findAll() start");
        List<JournalTypeDTO> result = journalTypeService.findAll();
        log.info("findAll() end - " + result);
        return result;
    }

}
