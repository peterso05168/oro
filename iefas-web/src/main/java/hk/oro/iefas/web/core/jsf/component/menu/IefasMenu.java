package hk.oro.iefas.web.core.jsf.component.menu;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;

import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.util.ComponentUtils;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@ResourceDependencies({@ResourceDependency(library = "primefaces", name = "components.css"),
    @ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
    @ResourceDependency(library = "primefaces", name = "jquery/jquery-plugins.js"),
    @ResourceDependency(library = "primefaces", name = "core.js"),
    @ResourceDependency(library = "primefaces", name = "components.js")})
public class IefasMenu extends AbstractMenu implements org.primefaces.component.api.Widget {

    public static final String COMPONENT_TYPE = "org.primefaces.component.IefasMenu";
    public static final String COMPONENT_FAMILY = "org.primefaces.component";
    public static final String DEFAULT_RENDERER = "org.primefaces.component.IefasMenuRenderer";

    public enum PropertyKeys {

        /**
         * widgetVar
         */
        widgetVar,
        /**
         * model
         */
        model,
        /**
         * style
         */
        style,
        /**
         * styleClass
         */
        styleClass,
        /**
         * autoDisplay
         */
        autoDisplay,
        /**
         * activeIndex
         */
        activeIndex,
        /**
         * orientation
         */
        orientation;

        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {}

        @Override
        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }
    }

    public IefasMenu() {
        setRendererType(DEFAULT_RENDERER);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public java.lang.String getWidgetVar() {
        return (java.lang.String)getStateHelper().eval(PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(java.lang.String widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, widgetVar);
    }

    @Override
    public org.primefaces.model.menu.MenuModel getModel() {
        return (org.primefaces.model.menu.MenuModel)getStateHelper().eval(PropertyKeys.model, null);
    }

    public void setModel(org.primefaces.model.menu.MenuModel model) {
        getStateHelper().put(PropertyKeys.model, model);
    }

    public java.lang.String getStyle() {
        return (java.lang.String)getStateHelper().eval(PropertyKeys.style, null);
    }

    public void setStyle(java.lang.String style) {
        getStateHelper().put(PropertyKeys.style, style);
    }

    public java.lang.String getStyleClass() {
        return (java.lang.String)getStateHelper().eval(PropertyKeys.styleClass, null);
    }

    public void setStyleClass(java.lang.String styleClass) {
        getStateHelper().put(PropertyKeys.styleClass, styleClass);
    }

    public boolean isAutoDisplay() {
        return (java.lang.Boolean)getStateHelper().eval(PropertyKeys.autoDisplay, true);
    }

    public void setAutoDisplay(boolean autoDisplay) {
        getStateHelper().put(PropertyKeys.autoDisplay, autoDisplay);
    }

    public int getActiveIndex() {
        return (java.lang.Integer)getStateHelper().eval(PropertyKeys.activeIndex, java.lang.Integer.MIN_VALUE);
    }

    public void setActiveIndex(int activeIndex) {
        getStateHelper().put(PropertyKeys.activeIndex, activeIndex);
    }

    public java.lang.String getOrientation() {
        return (java.lang.String)getStateHelper().eval(PropertyKeys.orientation, "horizontal");
    }

    public void setOrientation(java.lang.String orientation) {
        getStateHelper().put(PropertyKeys.orientation, orientation);
    }

    public static final String CONTAINER_CLASS = "tx-menu accordion lv1 rwd";
    public static final String VERTICAL_CLASS = "accordion";

    @Override
    public String resolveWidgetVar() {
        return ComponentUtils.resolveWidgetVar(getFacesContext(), this);
    }

}
