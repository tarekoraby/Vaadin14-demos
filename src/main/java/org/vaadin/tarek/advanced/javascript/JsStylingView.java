package org.vaadin.tarek.advanced.javascript;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

@Route("JsStylingView")
public class JsStylingView extends VerticalLayout {

    public JsStylingView() {
        Select<String> styledSelect = new Select<>();
        styledSelect.setItems("Option one", "Option two");
        styledSelect.setLabel("Styled Select");
        styledSelect.getElement().setAttribute("theme", "my-styled-select");
        UI.getCurrent().getPage().executeJs("$0.$server.setSelectStyle()",
                getElement());

        Select<String> nonStyledSelect = new Select<>();
        nonStyledSelect.setItems("Option one", "Option two");
        nonStyledSelect.setLabel("Non-styled Select");

        add(styledSelect, nonStyledSelect);
    }

    @ClientCallable
    public void setSelectStyle() {
        String minHeight = "0";
        String padding = ".1em .25em";

        UI.getCurrent().getPage().executeJs(""
                + "vaadinSelect = document.querySelectorAll('vaadin-select[theme=\"my-styled-select\"]')[0];\r\n"
                + "vaadinSelectOverlay = vaadinSelect.shadowRoot.querySelectorAll('vaadin-select-overlay[theme=\"my-styled-select\"]')[0];\r\n"
                + "vaadinItems = vaadinSelectOverlay.getElementsByTagName('vaadin-item');\r\n"
                + "for (var i = 0; i < vaadinItems.length; i++) {\r\n"
                + "  vaadinItems[i].style.minHeight='" + minHeight + "';\r\n"
                + "  vaadinItems[i].style.padding= '" + padding + "';\r\n"
                + "}");
    }
}
