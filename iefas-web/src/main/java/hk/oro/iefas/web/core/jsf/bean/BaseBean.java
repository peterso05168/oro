package hk.oro.iefas.web.core.jsf.bean;

import java.io.Serializable;

import javax.faces.component.UIInput;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

import lombok.Getter;
import lombok.Setter;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
public abstract class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private boolean editable = false;

    @Getter
    @Setter
    private boolean clikable = false;

    public static final String SEARCH_PAGE = "search?faces-redirect=true";
    public static final String EDIT_PAGE = "edit?faces-redirect=true";

    public static void hideComponent(String widgetVar) {
        RequestContext.getCurrentInstance().execute("PF('" + widgetVar + "').hide();");
    }

    public static void showComponent(String widgetVar) {
        RequestContext.getCurrentInstance().execute("PF('" + widgetVar + "').show();");
    }

    public static void inValidComponent(String componentId) {
        ((UIInput)Faces.getViewRoot().findComponent(componentId)).setValid(false);
    }

    public void changeToEdit() {
        this.clikable = false;
        this.editable = true;
    }

    public void checkPrivilege() {
        this.clikable = true;
    }
}
