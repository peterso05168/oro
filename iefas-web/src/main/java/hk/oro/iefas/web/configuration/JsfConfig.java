package hk.oro.iefas.web.configuration;

import java.util.Collections;

import javax.faces.application.ProjectStage;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.primefaces.util.Constants;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;

import hk.oro.iefas.web.core.jsf.scope.view.FacesViewScope;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@Configuration
public class JsfConfig {

    @Value(value = "${jsf.project-stage:Development}")
    private String projectStage;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
    }

    // @Bean
    // public ServletListenerRegistrationBean<ConfigureListener>
    // configureListener()
    // {
    // return new ServletListenerRegistrationBean<>(new ConfigureListener());
    // }

    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListener() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }

    @Bean
    public ServletListenerRegistrationBean<FileCleanerCleanup> fileCleanerCleanup() {
        return new ServletListenerRegistrationBean<>(new FileCleanerCleanup());
    }

    @Bean
    public FilterRegistrationBean characterEncodingFilter() {
        return new FilterRegistrationBean(new CharacterEncodingFilter(), servletRegistrationBean());
    }

    @Bean
    public FilterRegistrationBean fileUploadFilter() {
        return new FilterRegistrationBean(new FileUploadFilter(), servletRegistrationBean());
    }

    @Bean
    public ServletContextInitializer initializer() {
        return new ServletContextInitializer() {

            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, projectStage);
                servletContext.setInitParameter("com.sun.faces.numberOfViewsInSession", "5");
                servletContext.setInitParameter("com.sun.faces.serializeServerState", "false");
                servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
                servletContext.setInitParameter("javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE",
                    "true");
                servletContext.setInitParameter("javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL", "true");
                servletContext.setInitParameter(Constants.ContextParams.THEME, "oro");
                servletContext.setInitParameter(Constants.ContextParams.FONT_AWESOME, "true");
            }
        };
    }

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.setScopes(Collections.<String, Object>singletonMap(FacesViewScope.NAME, new FacesViewScope()));
        return configurer;
    }

    @Bean
    public static JsfErrorViewResolver jsfErrorViewResolver(ApplicationContext applicationContext) {
        return new JsfErrorViewResolver(applicationContext);
    }

}
