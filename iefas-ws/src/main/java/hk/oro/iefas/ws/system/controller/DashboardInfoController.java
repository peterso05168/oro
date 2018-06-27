/**
 * 
 */
package hk.oro.iefas.ws.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.dto.DashboardInfoDTO;
import hk.oro.iefas.ws.system.service.DashboardInfoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.ROOT_DASHBOARD_INFO)
public class DashboardInfoController {

    @Autowired
    private DashboardInfoService dashboardInfoService;

    @PostMapping(value = RequestUriConstant.URI_DASHBOARD_INFO_FINDBY_POSTID)
    List<DashboardInfoDTO> findByPostId(@RequestBody Integer postId) {
        log.info("findByPostId() start - PostId: " + postId);
        List<DashboardInfoDTO> result = dashboardInfoService.findByPostId(postId);
        log.info("findByPostId() end - " + result);
        return result;
    }

}
