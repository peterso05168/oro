package hk.oro.iefas.ws.dividend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.dto.GazetteDTO;
import hk.oro.iefas.domain.dividend.dto.SearchGazetteDescriptionDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.GazetteService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */

@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_GAZETTES_DESCRIPTION)
public class GazetteController {

    @Autowired
    private GazetteService gazetteService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_LOAD_GAZETTE_DESCRIPTION)
    public Page<GazetteDTO> loadGazette(@RequestBody SearchDTO<SearchGazetteDescriptionDTO> criteria) {
        log.info("loadGazette() - start and criteria=" + criteria);
        Page<GazetteDTO> page = gazetteService.searchGazetteList(criteria);
        log.info("loadGazette() - end and page=" + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_SAVE_GAZETTE_DESCRIPTION)
    public Integer saveGazette(@RequestBody GazetteDTO gazetteDTO) {
        log.info("saveGazette - start and gazetteDTO = " + gazetteDTO);
        Integer result = gazetteService.saveGazette(gazetteDTO);
        log.info("saveGazette - end and result = " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_SEARCH_GAZETTE_DESCRIPTION)
    public GazetteDTO searchGazetteById(@RequestBody Integer gazettaId) {
        log.info("searchGazetteById start and gazettaId=" + gazettaId);
        GazetteDTO gazetteDTO = gazetteService.searchByGazetteId(gazettaId);
        log.info("searchGazetteById end and gazetteDTO =" + gazetteDTO);
        return gazetteDTO;
    }

    @GetMapping(value = RequestUriConstant.URI_FIND_ALL)
    public List<GazetteDTO> findAll() {
        log.info("findAll - start ");
        List<GazetteDTO> list = gazetteService.findAll();
        log.info("findAll - end and list" + list);
        return list;
    }

}
