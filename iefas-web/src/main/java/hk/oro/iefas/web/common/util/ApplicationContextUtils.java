/**
 * 
 */
package hk.oro.iefas.web.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import hk.oro.iefas.web.core.service.BaseClientService;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static AppResourceUtils getAppResourceUtils() {
        return (AppResourceUtils)getApplicationContext().getBean(AppResourceUtils.BEAN_NAME);
    }

    public static BaseClientService getBaseClientService() {
        return (BaseClientService)getApplicationContext().getBean(BaseClientService.BEAN_NAME);
    }

}
