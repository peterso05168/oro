package hk.oro.iefas.ws.organization.controlller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.dto.RankDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.organization.service.RankService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2174 $ $Date: 2018-04-23 17:52:58 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_RANK)
public class RankController {

    @Autowired
    private RankService rankService;

    @GetMapping(value = RequestUriConstant.URI_FIND_ALL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public List<RankDTO> findAll() {
        log.info("findAll() start");
        List<RankDTO> result = rankService.findAll();
        log.info("findAll() end - " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_RANK_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public RankDTO findOne(@RequestBody Integer rankId) {
        log.info("findOne() start");
        RankDTO result = rankService.findOne(rankId);
        log.info("findOne() end - " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_FIND_BY_RANK_NAME)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public RankDTO findByRankName(@RequestBody String rankName) {
        log.info("findByRankName() start");
        RankDTO result = rankService.findByRankName(rankName);
        log.info("findByRankName() end - " + result);
        return result;
    }
}
