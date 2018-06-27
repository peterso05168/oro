package hk.oro.iefas.core.aop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.AUTODETECT)
@EnableAspectJAutoProxy
@Configuration
public class AspectJConfig {

}
