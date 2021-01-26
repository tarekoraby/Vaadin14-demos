package org.vaadin.tarek.checkbox;

import java.util.Collections;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("CheckBoxView")
@CssImport(value = "./styles/vaadin-checkbox-styles.css", themeFor = "vaadin-checkbox")
public class CheckBoxView extends VerticalLayout {

    public CheckBoxView() {
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Label");
        checkboxGroup.setItems("Option one", "Option two");
        checkboxGroup.setValue(Collections.singleton("Option one"));

        Checkbox checkboxSwapped = new Checkbox("Swapped label");
        checkboxSwapped.addClassName("swapped");

        CheckboxGroup<String> checkboxGroupRendered = new CheckboxGroup<>();
        checkboxGroupRendered.setLabel("Label");
        checkboxGroupRendered.setItems("Option one", "Option two");
        checkboxGroupRendered.getChildren().forEach(item -> addRenderedLabel((Checkbox) item));

        add(checkboxGroup, checkboxSwapped, checkboxGroupRendered);
    }

    private void addRenderedLabel(Checkbox item) {
        item.setLabelAsHtml(VaadinIcon.ACADEMY_CAP.create().getElement().getOuterHTML() + "<br>" + item.getLabel());
    }
}