package org.vaadin.tarek.checkbox;

import java.util.Collections;

import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("CheckBoxView")
public class CheckBoxView extends VerticalLayout {

    public CheckBoxView() {
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Label");
        checkboxGroup.setItems("Option one", "Option two");
        checkboxGroup.setValue(Collections.singleton("Option one"));

        add(checkboxGroup);
    }
}