package org.vaadin.tarek.select;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

@Route("SelectView")
@CssImport(value = "./styles/vaadin-select-text-field-styles.css", themeFor = "vaadin-select-text-field")
@CssImport(value = "./styles/vaadin-select-styles.css", themeFor = "vaadin-select")
public class SelectView extends VerticalLayout {

    public SelectView() {
        Select<String> styledSelect = new Select<>();
        styledSelect.setItems("Option one", "Option two");
        styledSelect.setLabel("Styled Select");
        styledSelect.setPlaceholder("Placeholder");
        styledSelect.addClassName("my-styled-select");

        Select<String> nonStyledSelect = new Select<>();
        nonStyledSelect.setItems("Option one", "Option two");
        nonStyledSelect.setPlaceholder("Placeholder");
        nonStyledSelect.setLabel("Non-styled Select");

        add(styledSelect, nonStyledSelect);
    }
}
