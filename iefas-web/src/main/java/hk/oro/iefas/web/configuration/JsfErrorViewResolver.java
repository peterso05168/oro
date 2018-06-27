package hk.oro.iefas.web.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProviders;
import org.springframework.boot.autoconfigure.web.DefaultErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class JsfErrorViewResolver implements ErrorViewResolver, Ordered {

    private static final Map<Series, String> SERIES_VIEWS;

    static {
        Map<Series, String> views = new HashMap<Series, String>();
        views.put(Series.CLIENT_ERROR, "4xx");
        views.put(Series.SERVER_ERROR, "5xx");
        SERIES_VIEWS = Collections.unmodifiableMap(views);
    }

    private ApplicationContext applicationContext;

    private final TemplateAvailabilityProviders templateAvailabilityProviders;

    private int order = Ordered.LOWEST_PRECEDENCE;

    /**
     * Create a new {@link DefaultErrorViewResolver} instance.
     * 
     * @param applicationContext the source application context
     * @param resourceProperties resource properties
     */
    public JsfErrorViewResolver(ApplicationContext applicationContext) {
        Assert.notNull(applicationContext, "ApplicationContext must not be null");
        this.applicationContext = applicationContext;
        this.templateAvailabilityProviders = new TemplateAvailabilityProviders(applicationContext);
    }

    JsfErrorViewResolver(ApplicationContext applicationContext,
        TemplateAvailabilityProviders templateAvailabilityProviders) {
        Assert.notNull(applicationContext, "ApplicationContext must not be null");
        this.applicationContext = applicationContext;
        this.templateAvailabilityProviders = templateAvailabilityProviders;
    }

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView modelAndView = resolve(String.valueOf(status), model);
        if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
            modelAndView = resolve(SERIES_VIEWS.get(status.series()), model);
        }
        return modelAndView;
    }

    private ModelAndView resolve(String viewName, Map<String, Object> model) {
        String errorViewName = "error/" + viewName;
        TemplateAvailabilityProvider provider
            = this.templateAvailabilityProviders.getProvider(errorViewName, this.applicationContext);
        if (provider != null) {
            return new ModelAndView(errorViewName, model);
        }
        return new ModelAndView(viewName, model);
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}
