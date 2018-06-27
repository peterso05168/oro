package hk.oro.iefas.web.core.jsf.scope.view;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
public class FacesViewScope implements Scope {
    public static final String NAME = "view";

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            throw new IllegalStateException("FacesContext.getCurrentInstance() returned null");
        }

        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

        if (viewMap.containsKey(name)) {
            return viewMap.get(name);
        } else {
            Object object = objectFactory.getObject();
            viewMap.put(name, object);

            return object;
        }
    }

    @Override
    public Object remove(String name) {
        log.debug("Remove view scoped bean {}", name);
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
    }

    @Override
    public String getConversationId() {
        return Faces.getSessionId() + '-' + Faces.getViewId();
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // Not supported by JSF for view scope
    }

    @Override
    public Object resolveContextualObject(String key) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesRequestAttributes reqAttr = new FacesRequestAttributes(fc);
        return reqAttr.resolveReference(key);
    }
}
