package hk.oro.iefas.web.common.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import hk.oro.iefas.web.core.jsf.scope.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1468 $ $Date: 2018-03-08 19:45:13 +0800 (週四, 08 三月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ApplicationScoped
public class AppBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @PostConstruct
    private void init() {
        log.info("======ApplicationBean init======");
    }

}
