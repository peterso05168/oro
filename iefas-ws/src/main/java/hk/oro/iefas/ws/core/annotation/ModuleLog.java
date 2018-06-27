package hk.oro.iefas.ws.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version $Revision: 1088 $ $Date: 2018-02-08 11:15:43 +0800 (週四, 08 二月 2018) $
 * @author $Author: marvel.ma $
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ModuleLog {

    public String module() default "";

    public String operation() default "";

    public boolean needTransaction() default false;

}
