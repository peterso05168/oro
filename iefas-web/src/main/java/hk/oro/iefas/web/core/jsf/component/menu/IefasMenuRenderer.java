package hk.oro.iefas.web.core.jsf.component.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.api.AjaxSource;
import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.BaseMenuRenderer;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.Separator;
import org.primefaces.model.menu.Submenu;
import org.primefaces.util.ComponentTraversalUtils;
import org.primefaces.util.WidgetBuilder;

import hk.oro.iefas.core.util.CommonUtils;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class IefasMenuRenderer extends BaseMenuRenderer {

    private final static String VERTICAL_ORIENTATION = "vertical";

    @Override
    protected void encodeScript(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        IefasMenu menu = (IefasMenu)abstractMenu;
        String clientId = menu.getClientId(context);

        WidgetBuilder wb = getWidgetBuilder(context);
        wb.init("IefasMenu", menu.resolveWidgetVar(), clientId).attr("autoDisplay", menu.isAutoDisplay())
            .attr("activeIndex", menu.getActiveIndex(), Integer.MIN_VALUE);

        wb.finish();
    }

    @Override
    protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        IefasMenu menu = (IefasMenu)abstractMenu;
        boolean vertical = VERTICAL_ORIENTATION.equals(menu.getOrientation());
        String clientId = menu.getClientId(context);
        String style = menu.getStyle();
        String styleClass = menu.getStyleClass();
        styleClass = styleClass == null ? IefasMenu.CONTAINER_CLASS : IefasMenu.CONTAINER_CLASS + " " + styleClass;

        if (vertical) {
            styleClass = styleClass + " " + IefasMenu.VERTICAL_CLASS;
        }

        writer.startElement("div", menu);
        writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("class", styleClass, "styleClass");
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }
        writer.writeAttribute("data-menu-id", "mainMenu", null);
        writer.writeAttribute("data-menu-collasped", "true", null);

        writer.startElement("ul", null);
        if (menu.getElementsCount() > 0) {
            encodeRootItems(context, menu);
        }
        writer.endElement("ul");

        writer.startElement("div", menu);
        writer.writeAttribute("class", "extra-top", null);
        writer.startElement("div", menu);
        writer.writeAttribute("class", "logo", null);
        writer.endElement("div");
        writer.endElement("div");

        writer.endElement("div");

    }

    @SuppressWarnings("unchecked")
    protected void encodeRootItems(FacesContext context, IefasMenu menu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        List<MenuElement> elements = (List<MenuElement>)menu.getElements();

        for (MenuElement element : elements) {
            if (element.isRendered()) {
                if (element instanceof MenuItem) {
                    writer.startElement("li", null);
                    encodeMenuItem(context, menu, (MenuItem)element);
                    writer.endElement("li");
                } else if (element instanceof Submenu) {
                    encodeRootSubmenu(context, menu, (Submenu)element);
                } else if (element instanceof Separator) {
                    encodeSeparator(context, (Separator)element);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void encodeRootSubmenu(FacesContext context, IefasMenu menu, Submenu submenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String icon = submenu.getIcon();
        String label = submenu.getLabel();
        String style = submenu.getStyle();
        String styleClass = submenu.getStyleClass();

        writer.startElement("li", null);
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, null);
        }
        if (style != null) {
            writer.writeAttribute("style", style, null);
        }

        // title
        writer.startElement("a", null);
        writer.writeAttribute("href", "#", null);
        writer.writeAttribute("tabindex", "-1", null);

        if (icon != null) {
            writer.startElement("i", null);
            writer.writeAttribute("class", icon, null);
            writer.endElement("i");
        }

        if (label != null) {
            writer.startElement("span", null);
            writer.writeText(submenu.getLabel(), "value");
            writer.endElement("span");
        }

        writer.endElement("a");

        // submenus
        if (submenu.getElementsCount() > 0) {
            List<MenuElement> submenuElements = (List<MenuElement>)submenu.getElements();
            writer.startElement("ul", null);

            for (MenuElement submenuElement : submenuElements) {
                if (submenuElement.isRendered()) {
                    if (submenuElement.isRendered() && submenuElement instanceof Submenu) {
                        encodeRootSubmenu(context, menu, (Submenu)submenuElement);
                    } else if (submenuElement instanceof MenuItem) {
                        writer.startElement("li", null);
                        encodeMenuItem(context, menu, (MenuItem)submenuElement);
                        writer.endElement("li");
                    }
                }
            }

            writer.endElement("ul");
        }

        writer.endElement("li");
    }

    @Override
    protected void encodeMenuItem(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String title = menuitem.getTitle();
        String style = menuitem.getStyle();
        boolean disabled = menuitem.isDisabled();
        String rel = menuitem.getRel();

        writer.startElement("a", null);
        writer.writeAttribute("tabindex", "-1", null);
        if (shouldRenderId(menuitem)) {
            writer.writeAttribute("id", menuitem.getClientId(), null);
        }
        if (title != null) {
            writer.writeAttribute("title", title, null);
        }

        String styleClass = menuitem.getStyleClass();
        if (CommonUtils.isNotEmpty(styleClass)) {
            if (disabled) {
                styleClass = styleClass + " ui-state-disabled";
            }

            writer.writeAttribute("class", styleClass, null);
        }

        if (style != null) {
            writer.writeAttribute("style", style, null);
        }

        if (rel != null) {
            writer.writeAttribute("rel", rel, null);
        }

        if (disabled) {
            writer.writeAttribute("href", "#", null);
            writer.writeAttribute("onclick", "return false;", null);
        } else {
            setConfirmationScript(context, menuitem);
            String onclick = menuitem.getOnclick();

            // GET
            if (menuitem.getUrl() != null || menuitem.getOutcome() != null) {
                String targetURL = getTargetURL(context, (UIOutcomeTarget)menuitem);
                writer.writeAttribute("href", targetURL, null);

                if (menuitem.getTarget() != null) {
                    writer.writeAttribute("target", menuitem.getTarget(), null);
                }
            }
            // POST
            else {
                writer.writeAttribute("href", "#", null);

                UIComponent form = ComponentTraversalUtils.closestForm(context, menu);
                if (form == null) {
                    throw new FacesException("MenuItem must be inside a form element");
                }

                String command;
                if (menuitem.isDynamic()) {
                    String menuClientId = menu.getClientId(context);
                    Map<String, List<String>> params = menuitem.getParams();
                    if (params == null) {
                        params = new LinkedHashMap<String, List<String>>();
                    }
                    List<String> idParams = new ArrayList<String>();
                    idParams.add(menuitem.getId());
                    params.put(menuClientId + "_menuid", idParams);

                    command = menuitem.isAjax() ? buildAjaxRequest(context, menu, (AjaxSource)menuitem, form, params)
                        : buildNonAjaxRequest(context, menu, form, menuClientId, params, true);
                } else {
                    command = menuitem.isAjax() ? buildAjaxRequest(context, (AjaxSource)menuitem, form)
                        : buildNonAjaxRequest(context, ((UIComponent)menuitem), form,
                            ((UIComponent)menuitem).getClientId(context), true);
                }

                onclick = (onclick == null) ? command : onclick + ";" + command;
            }

            if (onclick != null) {
                if (menuitem.requiresConfirmation()) {
                    writer.writeAttribute("data-pfconfirmcommand", onclick, null);
                    writer.writeAttribute("onclick", menuitem.getConfirmationScript(), "onclick");
                } else {
                    writer.writeAttribute("onclick", onclick, null);
                }
            }
        }

        encodeMenuItemContent(context, menu, menuitem);

        writer.endElement("a");
    }

    @Override
    protected void encodeMenuItemContent(FacesContext context, AbstractMenu menu, MenuItem menuitem)
        throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String icon = menuitem.getIcon();
        Object value = menuitem.getValue();

        if (icon != null) {
            writer.startElement("i", null);
            writer.writeAttribute("class", icon, null);
            writer.endElement("i");
        }

        writer.startElement("span", null);

        if (value != null) {
            if (menuitem.isEscape()) {
                writer.writeText(value, "value");
            } else {
                writer.write(value.toString());
            }
        } else if (menuitem.shouldRenderChildren()) {
            renderChildren(context, (UIComponent)menuitem);
        }

        writer.endElement("span");
    }

}
