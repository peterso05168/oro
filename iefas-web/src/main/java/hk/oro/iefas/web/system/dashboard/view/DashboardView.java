/**
 * 
 */
package hk.oro.iefas.web.system.dashboard.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.system.vo.DashboardInfoVO;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.dashboard.service.DashboardInfoClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class DashboardView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private DashboardInfoClientService dashboardInfoClientService;

    @Getter
    @Setter
    private List<DashboardInfoVO> dashboardInfos;

    @Getter
    @Setter
    private DashboardInfoVO selectedDashboardInfo;

    @PostConstruct
    private void init() {
        log.info("======DashboardView init======");
        dashboardInfos = dashboardInfoClientService.findByPostId(SecurityUtils.getCurrentPostId());
    }

    public String redirect() {
        String url = Faces.getRequestParameter("url");
        return url;
    }

}
